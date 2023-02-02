package com.algaworks.algafood;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.algaworks.di.notificacao.Notificador;
import com.algaworks.di.notificacao.NotificadorEmail;
import com.algaworks.di.service.AtivacaoClienteService;

@Configuration
public class ServiceConfig {
	
	@Bean
	AtivacaoClienteService ativacaoClienteService(Notificador notificador) {
		
		return new AtivacaoClienteService(notificador);
	}

}
