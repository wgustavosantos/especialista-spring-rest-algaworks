package com.algaworks.di.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.algaworks.di.modelo.Cliente;
import com.algaworks.di.notificacao.Notificador;

@Component
@Qualifier(value = "NotificadorZap")
public class AtivacaoClienteService {
	
	private Notificador notificadorEmail;
	
	public AtivacaoClienteService(Notificador notificadorEmail) {
		this.notificadorEmail = notificadorEmail;
		System.out.println("Bean AtivacaoClienteService gerenciado pelo Spring: " + this);
		System.out.println("Bean injetado em AtivacaoClienteService: " + notificadorEmail);
	}

	public void ativar (Cliente cliente) {
		cliente.ativar();
		notificadorEmail.notificar(cliente, "Seu cadastro no sistema está ativo");
	}
}
