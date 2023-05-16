package com.algaworks.algafood.core.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
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
                .formLogin().and()
            .authorizeRequests()
                .antMatchers("/oauth/*").authenticated()//jogar para página de login cap 23.41
                .and()
            .cors().and()
            .csrf().disable()
            .oauth2ResourceServer()
//                .opaqueToken();
            .jwt()
            .jwtAuthenticationConverter(jwtAuthenticationConverter());

    }

    private JwtAuthenticationConverter jwtAuthenticationConverter() {
        final JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();

        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwt -> {
            List<String> authorities = jwt.getClaimAsStringList("authorities");

            if (authorities == null)
                authorities = Collections.emptyList();

            final JwtGrantedAuthoritiesConverter scopesJwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
            final Collection<GrantedAuthority> grantedAuthorities = scopesJwtGrantedAuthoritiesConverter.convert(jwt);

            grantedAuthorities.addAll(authorities.stream()
                    .map(SimpleGrantedAuthority::new).toList());
            return grantedAuthorities;
        });

        return jwtAuthenticationConverter;
    }

    /*AuthenticationManager para o Authorization Server, configure (AuthorizationServerEndpointsConfigurer) */
    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }
}
