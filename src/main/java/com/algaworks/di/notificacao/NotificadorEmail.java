package com.algaworks.di.notificacao;

import org.springframework.stereotype.Component;

import com.algaworks.di.modelo.Cliente;

@Component
public class NotificadorEmail {
	
	public NotificadorEmail() {
		System.out.println("Bean gerenciado pelo Srping");
	}

	public void notificar(Cliente c, String m) {
		System.out.printf("Notificando %s através do email %s : %s\n", c.getNome(), c.getEmail(), m);
	}

}
