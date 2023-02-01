package com.algaworks.di.notificacao;

import com.algaworks.di.modelo.Cliente;

public class NotificadorEmail implements Notificador {
	
	@Override
	public void notificar(Cliente c, String m) {
		System.out.printf("Notificando %s através do email %s : %s\n", c.getNome(), c.getEmail(), m);
	}

}
