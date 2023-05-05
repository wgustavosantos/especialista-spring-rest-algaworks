package com.algaworks.algafood.auth;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
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

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

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
                .withClient("checktoken")/*Acesso somente para verificar o token no ResourceServer*/
                .secret("check123");
    }

    /*Para configurar o acesso ao endpoint de checagem de token ou check token, instrospecção de token */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        //ecurity.checkTokenAccess("isAuthenticated()");//Spring Security Expression.
        security.checkTokenAccess("permitAll()");//permitindo qualquer cliente
        // P/ acessar o check token, precisa de autenticaçao do cliente
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager); //somente o fluxo "passoword" precisa do authecticationManager, pois é assim que funciona o seu fluxo
        endpoints.userDetailsService(userDetailsService);/*para refresh_token*/
        endpoints.reuseRefreshTokens(false);
    }
}
