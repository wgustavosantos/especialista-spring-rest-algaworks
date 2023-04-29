package com.algaworks.algafood.core.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /*restrição e autorização de acesso aos endpoints*/
        http.httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers("/v1/cozinhas/**").permitAll()
                .anyRequest().authenticated()

                /*armazenamento de sessão*/
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                /*desabilitação de csrf*/
                .and()
                .csrf().disable();
    }
}
