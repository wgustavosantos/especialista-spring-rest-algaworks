package com.algaworks.algafood.di.notificacao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.di.modelo.Cliente;

@Component
@TipoDoNotificador(NivelUrgencia.NORMAL)
@Profile("prod")
public class NotificadorEmail implements Notificador {

	@Value("${notificador.email.smtp}")
	private String hostSmtp;
	
	@Value("${server.port}")
	private Integer portaSmtp;
	
	
	@Override
	public void notificar(Cliente c, String m) {
		System.out.printf("Notificando %s através do email %s : %s\n", c.getNome(), c.getEmail(), m);
		System.out.println("Host do Servidor " + hostSmtp);
		System.out.println("POrta do servidor " + portaSmtp);
	}
	
}
