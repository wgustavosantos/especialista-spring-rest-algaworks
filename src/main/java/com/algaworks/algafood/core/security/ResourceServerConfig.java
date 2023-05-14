package com.algaworks.algafood.core.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class ResourceServerConfig extends WebSecurityConfigurerAdapter {

    /*Métodos de configuração de usuários e BCryptPasswordEncoder foram deletados
     * pois ficam no Authorization Server*/


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /*restrição e autorização de acesso aos endpoints
        *.authorizeRequests(): todas as requisições (requests) ao Resource Server
        * requerem autenticação do usuário que está acessando o recurso.
        * anyRequest().authenticated()" indica que qualquer requisição deve ser autenticada,
        * e que apenas usuários autenticados devem ter acesso ao Resource Server.
        * oauth2ResourceServer().opaqueToken()" indica que o Resource Server deve usar um token opaco
        * (opaque token) para autenticação do usuário.*/
        http
            .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .cors()
                .and()
                .oauth2ResourceServer()
//                .opaqueToken();
                .jwt();

    }
}
