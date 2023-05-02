package com.algaworks.algafood.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /*Lista de usuários em memória para autenticação*/
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("Gustavo")
                    .password(passwordEncoder().encode("123"))
                    .roles("ADMIN")
                .and()
                .withUser("Joao")
                .password(passwordEncoder().encode("123"))
                .roles("ADMIN");
    }

    /*@Override estamos em um "Authorization Server" e essas config são para "Resource Server"
    protected void configure(HttpSecurity http) throws Exception {
        /*restrição e autorização de acesso aos endpoints
        http.httpBasic()
                .and()
                    .authorizeRequests()
                    .antMatchers("/v1/cozinhas/**").permitAll()
                    .anyRequest().authenticated()

                /*armazenamento de sessão
                .and()
                    .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                /*desabilitação de csrf
                .and()
                    .csrf().disable();
    }*/

    /*Necessário para criptografar senha*/
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /*AuthenticationManager para o Authorization Server, configure (AuthorizationServerEndpointsConfigurer) */
    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    /*User Details Service para o Refresh Token*/
    @Bean
    @Override
    protected UserDetailsService userDetailsService() {
        return super.userDetailsService();
    }
}
