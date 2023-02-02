package com.algaworks.di.notificacao;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.algaworks.di.modelo.Cliente;

@Component
@Qualifier("SMS")
public class NotificadorSMS implements Notificador {
	
	@Override
	public void notificar(Cliente c, String m) {
		System.out.printf("Notificando %s através do email %s : %s\n", c.getNome(), c.getEmail(), m);
	}
	
}
