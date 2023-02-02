package com.algaworks.di.notificacao;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.algaworks.di.modelo.Cliente;

@Component
@Profile(value = "dev")
@TipoDoNotificador(NivelUrgencia.NORMAL)
public class NotificadorEmailMock implements Notificador {
	
	@Override
	public void notificar(Cliente c, String m) {
		System.out.printf("Notificando %s por EmailFake %s : %s\n", c.getNome(), c.getEmail(), m);
	}
	
}
