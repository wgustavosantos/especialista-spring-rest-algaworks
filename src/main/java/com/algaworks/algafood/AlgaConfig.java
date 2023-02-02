package com.algaworks.algafood;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.algaworks.di.notificacao.NotificadorEmail;
import com.algaworks.di.service.AtivacaoClienteService;

//@Configuration
public class AlgaConfig {
	
	@Bean
	NotificadorEmail notificadorEmail() {
		NotificadorEmail notificadorEmail = new NotificadorEmail("smtp.algamail.com.br");
		notificadorEmail.setCaixaAlta(true);
		return notificadorEmail;
	}
	
	@Bean
	AtivacaoClienteService ativacaoClienteService() {
		
		return new AtivacaoClienteService(notificadorEmail());
	}

}
