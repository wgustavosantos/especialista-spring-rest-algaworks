package com.algaworks.di.service;

import com.algaworks.di.modelo.Cliente;
import com.algaworks.di.notificacao.Notificador;

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
