package com.algaworks.algafood.auth;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.token.TokenService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.CompositeTokenGranter;
import org.springframework.security.oauth2.provider.TokenGranter;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

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
    private RedisConnectionFactory redisConnectionFactory;

    /*configuração de clientes*/
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory() //autorização de clientes em memória
                .withClient("algafood-web")//cliente consumidor da api
                .secret(passwordEncoder.encode("web123"))//password do client
                .authorizedGrantTypes("password", "refresh_token")//tipo de fluxo Resource Owner Passoword Credentials GrantType
                .scopes("write", "read")//escopo de leitura e alteração
                .accessTokenValiditySeconds(60 * 60 * 60)//equivale a 6 horas
                .refreshTokenValiditySeconds(60 * 24 * 60 * 60)//60 dias * 24 horas * 60m * 60s
                .and()
                .withClient("foodanalitycs")
                .secret(passwordEncoder.encode(""))
                .authorizedGrantTypes("authorization_code")
                .scopes("write", "read")
                .redirectUris("http://aplicacao-clientes")
            .and()/*client credentials grant type*/
                .withClient("faturamento")
                .secret(passwordEncoder.encode("faturamento123"))//password do client
                .authorizedGrantTypes("client_credentials")//tipo de fluxo Resource Owner Passoword Credentials GrantType
                .scopes("write", "read")//escopo de leitura e alteração
            .and()
                .withClient("checktoken")/*Acesso somente para verificar o token no ResourceServer*/
                .secret(passwordEncoder.encode("check123"))
            .and()
                .withClient("webadmin")
                .authorizedGrantTypes("implicit")
                .scopes("write", "read")
                .redirectUris("http://127.0.0.1:5500")
            ;
    }

    /*Para configurar o acesso ao endpoint de checagem de token ou check token, instrospecção de token */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        //ecurity.checkTokenAccess("isAuthenticated()");//Spring Security Expression.
        security.checkTokenAccess("permitAll()")//permitindo qualquer cliente
                .allowFormAuthenticationForClients();//permitindo autenticação de client em um form
        // P/ acessar o check token, precisa de autenticaçao do cliente
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager); //somente o fluxo "passoword" precisa do authecticationManager, pois é assim que funciona o seu fluxo
        endpoints.userDetailsService(userDetailsService);/*para refresh_token*/
        endpoints.reuseRefreshTokens(false);
        endpoints.tokenGranter(tokenGranter(endpoints));
        endpoints.tokenStore(tokenStore());
    }

    public TokenStore tokenStore() {
        return new RedisTokenStore(redisConnectionFactory);
    }

    private TokenGranter tokenGranter(AuthorizationServerEndpointsConfigurer endpoints) {
        var pkceAuthorizationCodeTokenGranter = new PkceAuthorizationCodeTokenGranter(endpoints.getTokenServices(),
                endpoints.getAuthorizationCodeServices(), endpoints.getClientDetailsService(),
                endpoints.getOAuth2RequestFactory());

        var granters = Arrays.asList(
                pkceAuthorizationCodeTokenGranter, endpoints.getTokenGranter());

        return new CompositeTokenGranter(granters);
    }
}
