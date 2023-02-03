package com.algaworks.algafood.di.notificacao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.di.modelo.Cliente;

@Component
@TipoDoNotificador(NivelUrgencia.URGENTE)
public class NotificadorSMS implements Notificador {
	
	
	@Value("${notificador.email.smtp}")
	private String hostsmtp;
	
	
	@Override
	public void notificar(Cliente c, String m) {
		System.out.printf("Notificando %s através do email %s : %s\n", c.getNome(), c.getEmail(), m);
		System.out.println(hostsmtp);
	}
	
}
