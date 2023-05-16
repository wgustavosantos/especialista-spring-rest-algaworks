package com.algaworks.algafood.core.security.authorizationserver;

import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.KeyUse;
import com.nimbusds.jose.jwk.RSAKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.CompositeTokenGranter;
import org.springframework.security.oauth2.provider.TokenGranter;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.approval.TokenApprovalStore;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import javax.sql.DataSource;
import java.security.KeyPair;
import java.security.interfaces.RSAPublicKey;
import java.util.Arrays;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtKeyStoreProperties jwtKeyStoreProperties;

    @Autowired
    private DataSource dataSource;

    /*configuração de clientes*/
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.jdbc(dataSource);
    }

    /*Para configurar o acesso ao endpoint de checagem de token ou check token, instrospecção de token */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        //ecurity.checkTokenAccess("isAuthenticated()");//Spring Security Expression.
        security.checkTokenAccess("permitAll()")//permitindo qualquer cliente
                .tokenKeyAccess("permitAll()")//permite todos os acessos ao endpoint de acesso ao tokenkey publico do jwt
                .allowFormAuthenticationForClients();//permitindo autenticação de client em um form
        // P/ acessar o check token, precisa de autenticaçao do cliente
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        final TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        tokenEnhancerChain.setTokenEnhancers(Arrays.asList(new JwtCustomClaimsTokenEnhancer(), jwtAccessTokenConverter()));

        endpoints.authenticationManager(authenticationManager). //somente o fluxo "passoword" precisa do authecticationManager, pois é assim que funciona o seu fluxo
        userDetailsService(userDetailsService)./*para refresh_token*/
        reuseRefreshTokens(false).
        tokenEnhancer(tokenEnhancerChain).
        accessTokenConverter(jwtAccessTokenConverter()).
        approvalStore(approvalStore(endpoints.getTokenStore())).
        tokenGranter(tokenGranter(endpoints));
    }

    private ApprovalStore approvalStore (TokenStore tokenStore){
        final TokenApprovalStore tokenApprovalStore = new TokenApprovalStore();
        tokenApprovalStore.setTokenStore(tokenStore);
        return tokenApprovalStore;
    }

    private TokenGranter tokenGranter(AuthorizationServerEndpointsConfigurer endpoints) {
        var pkceAuthorizationCodeTokenGranter = new PkceAuthorizationCodeTokenGranter(endpoints.getTokenServices(),
                endpoints.getAuthorizationCodeServices(), endpoints.getClientDetailsService(),
                endpoints.getOAuth2RequestFactory());

        var granters = Arrays.asList(
                pkceAuthorizationCodeTokenGranter, endpoints.getTokenGranter());

        return new CompositeTokenGranter(granters);
    }

    @Bean/*23.45. Implementando o endpoint do JSON Web Key Set (JWKS)*/
    public JWKSet jwkSet(){
        final KeyPair keyPair = getKeyPair();

        /*pegando a chave pública do keypar*/
        RSAKey rsaKey = new RSAKey.Builder((RSAPublicKey)keyPair.getPublic())
                .keyUse(KeyUse.SIGNATURE)//chave assinada por nós
                .algorithm(JWSAlgorithm.RS256)
                .keyID("algafood-key-id").build();//pode ser qualquer nome de iden. no json

        return new JWKSet(rsaKey);
    }

    @Bean/*Converte informações de user logado para JWT * pode ser usado como Bean*/
    public JwtAccessTokenConverter jwtAccessTokenConverter (){
        /*utiliza hmacsha-256 simétrico*/
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        KeyPair keyPair = getKeyPair();
        jwtAccessTokenConverter.setKeyPair(keyPair);
        return jwtAccessTokenConverter;
    }

    private KeyPair getKeyPair(){
        //jwtAccessTokenConverter.setSigningKey("ansbchd978234dkfjhsd98sd42nlkj2094kwejs0d8g");

        String keyStorePass = jwtKeyStoreProperties.getPassword();
        String keyPairAlias = jwtKeyStoreProperties.getKeypairAlias();

        final KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(jwtKeyStoreProperties.getJksLocation(), keyStorePass.toCharArray());
        final KeyPair keyPair = keyStoreKeyFactory.getKeyPair(keyPairAlias);

        return keyPair;
    }
}
