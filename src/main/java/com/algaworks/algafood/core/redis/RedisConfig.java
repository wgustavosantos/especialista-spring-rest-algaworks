package com.algaworks.algafood.core.redis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.session.MapSessionRepository;
import org.springframework.session.Session;
import org.springframework.session.SessionRepository;

import java.util.HashMap;
/*#spring.session.store-type=none não é suportada no SpringBoot 3, logo, se faz necessario a definicao do bean*/
@Configuration
public class RedisConfig {

    @Bean
    @Profile("development")
    public SessionRepository<?> sessionRepository()
    {
        return null;
    }
}
