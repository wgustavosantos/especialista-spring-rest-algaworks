package com.algaworks.algafood.core.email;

import com.algaworks.algafood.domain.service.EnvioEmailSerivce;
import com.algaworks.algafood.infrastructure.service.email.MockEmailService;
import com.algaworks.algafood.infrastructure.service.email.SmtpEnvioEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmailConfig {

    @Autowired
    private EmailProperties emailProperties;

    @Bean
    public EnvioEmailSerivce envioEmailSerivce(){

        switch(emailProperties.getImplementacao()){
            case MOCK -> new MockEmailService();
            case SENDGRID -> new SmtpEnvioEmailService();
        }
        return new MockEmailService();
    }
}
