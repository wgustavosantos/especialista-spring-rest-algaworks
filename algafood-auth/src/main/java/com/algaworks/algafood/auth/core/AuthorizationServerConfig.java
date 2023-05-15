package com.algaworks.algafood.auth.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
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

import java.security.KeyPair;
import java.util.Arrays;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtKeyStoreProperties jwtKeyStoreProperties;

    /*configuração de clientes*/
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory() //autorização de clientes em memória
                .withClient("algafood-web")//cliente consumidor da api
                .secret(passwordEncoder.encode("web123"))//password do client
                .authorizedGrantTypes("password", "refresh_token")//tipo de fluxo Resource Owner Passoword Credentials GrantType
                .scopes("WRITE", "READ")//escopo de leitura e alteração
                .accessTokenValiditySeconds(60 * 60 * 60)//equivale a 6 horas
                .refreshTokenValiditySeconds(60 * 24 * 60 * 60)//60 dias * 24 horas * 60m * 60s
                .and()
                .withClient("foodanalitycs")
                .secret(passwordEncoder.encode("food123"))
                .authorizedGrantTypes("authorization_code")
                .scopes("WRITE", "READ")
                .redirectUris("http://127.0.0.1:5500")
            .and()/*client credentials grant type*/
                .withClient("faturamento")
                .secret(passwordEncoder.encode("faturamento123"))//password do client
                .authorizedGrantTypes("client_credentials")//tipo de fluxo Resource Owner Passoword Credentials GrantType
                .scopes("WRITE", "READ")//escopo de leitura e alteração
            .and()
                .withClient("checktoken")/*Acesso somente para verificar o token no ResourceServer*/
                .secret(passwordEncoder.encode("check123"))
            .and()
                .withClient("webadmin")
                .authorizedGrantTypes("implicit")
                .scopes("WRITE", "READ")
                .redirectUris("http://127.0.0.1:5500")
            ;
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

    //@Bean/*Converte informações de user logado para JWT * pode ser usado como Bean*/
    public JwtAccessTokenConverter jwtAccessTokenConverter (){
        /*utiliza hmacsha-256 simétrico*/
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        //jwtAccessTokenConverter.setSigningKey("ansbchd978234dkfjhsd98sd42nlkj2094kwejs0d8g");

        final ClassPathResource jksResource = new ClassPathResource(jwtKeyStoreProperties.getPath());
        String keyStorePass = jwtKeyStoreProperties.getPassword();
        String keyPairAlias = jwtKeyStoreProperties.getKeypairAlias();

        final KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(jksResource, keyStorePass.toCharArray());
        final KeyPair keyPair = keyStoreKeyFactory.getKeyPair(keyPairAlias);
        jwtAccessTokenConverter.setKeyPair(keyPair);
        return jwtAccessTokenConverter;
    }
}
