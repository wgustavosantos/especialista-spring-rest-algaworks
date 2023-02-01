package com.algaworks.di.notificacao;

import com.algaworks.di.modelo.Cliente;

public class NotificadorSms implements Notificador {
	
	@Override
	public void notificar(Cliente c, String m) {
		System.out.printf("Notificando %s por SMS através do telefone %s: %s",
					c.getNome(), c.getTelefone(), m);
	}
}
