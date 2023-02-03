package com.algaworks.algafood.di.notificacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.di.modelo.Cliente;

@Component
@TipoDoNotificador(NivelUrgencia.NORMAL)
@Profile("dev")
public class NotificadorEmailMock implements Notificador {
	
	@Autowired
	private NotificacaoProperties notificacaoProperties;
	
	@Override
	public void notificar(Cliente c, String m) {
		System.out.printf("Notificando %s por EmailFake %s : %s\n", c.getNome(), c.getEmail(), m);
		System.out.println("Host: " + notificacaoProperties.getHostServidor());
		System.out.println("Porta: " + notificacaoProperties.getPortaServidor());
	}
	
}
