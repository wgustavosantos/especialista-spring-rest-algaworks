package com.algaworks.di.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.algaworks.di.modelo.Cliente;
import com.algaworks.di.notificacao.Notificador;

@Component
public class AtivacaoClienteService {

	@Autowired
	@Qualifier("Email")
	private Notificador notificador;

	public void ativar(Cliente cliente) {
		cliente.ativar();

		notificador.notificar(cliente, "Seu cadastro no sistema está ativo");

	}

}
