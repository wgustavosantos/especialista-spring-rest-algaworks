package com.algaworks.algafood.core.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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
                .antMatchers(HttpMethod.POST, "/v1/cozinhas/**").hasAuthority("EDITAR_COZINHAS")
                .antMatchers(HttpMethod.PUT, "/v1/cozinhas/**").hasAuthority("EDITAR_COZINHAS")
                .antMatchers(HttpMethod.GET, "/v1/cozinhas/**").authenticated()
                .anyRequest().denyAll()
                .and()
                .cors()
                .and()
                .oauth2ResourceServer()
//                .opaqueToken();
                .jwt()
                .jwtAuthenticationConverter(jwtAuthenticationConverter());

    }

    private JwtAuthenticationConverter jwtAuthenticationConverter(){
        final JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();

        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwt -> {
            List<String> authorities = jwt.getClaimAsStringList("authorities");

            if(authorities == null)
                authorities = Collections.emptyList();

            return authorities.stream()
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());
        });

        return jwtAuthenticationConverter;
    }
}
