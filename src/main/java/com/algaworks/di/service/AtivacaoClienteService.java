package com.algaworks.di.service;

import com.algaworks.di.modelo.Cliente;
import com.algaworks.di.notificacao.NotificadorEmail;

public class AtivacaoClienteService {
	
	private NotificadorEmail notificadorEmail;;
	
	public void ativar (Cliente cliente) {
		cliente.ativar();
		notificadorEmail.notificar(cliente, "Seu cadastro no sistema está ativo");
	}
}
