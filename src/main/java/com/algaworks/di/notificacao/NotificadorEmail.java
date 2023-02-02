package com.algaworks.di.notificacao;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.algaworks.di.modelo.Cliente;

@Component
@Primary
public class NotificadorEmail implements Notificador {
	
	@Override
	public void notificar(Cliente c, String m) {
		System.out.printf("Notificando %s por SMS através do telefone %s : %s\n", c.getNome(), c.getTelefone(), m);
	}
	
}
