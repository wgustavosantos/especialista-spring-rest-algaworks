package com.algaworks.di.notificacao;

import com.algaworks.di.modelo.Cliente;

public class NotificadorEmail implements Notificador {
	
	private boolean caixaAlta;
	@SuppressWarnings("unused")
	private String hostServidorSmtp;
	
	public NotificadorEmail(String hostServidorSmtp) {
	this.hostServidorSmtp = hostServidorSmtp;
	System.out.println("Notificador email");
	}
	
	@Override
	public void notificar(Cliente c, String m) {
		if(this.caixaAlta)
			m = m.toUpperCase();
		System.out.printf("Notificando %s através do email %s : %s\n", c.getNome(), c.getEmail(), m);
	}
	
	public void setCaixaAlta(boolean caixaAlta) {
		this.caixaAlta = caixaAlta;
	}
}
