package com.algaworks.di.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algaworks.di.modelo.Cliente;
import com.algaworks.di.notificacao.Notificador;

@Component
public class AtivacaoClienteService {
	
	@Autowired(required = false)
	private List<Notificador> notificadores;

	public void ativar (Cliente cliente) {
		cliente.ativar();
		
		for(Notificador n : notificadores) {
			n.notificar(cliente, "Seu cadastro no sistema está ativo");
		}
	}

}
