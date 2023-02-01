package com.algaworks.di;

import com.algaworks.di.modelo.Cliente;
import com.algaworks.di.notificacao.Notificador;
import com.algaworks.di.notificacao.NotificadorEmail;
import com.algaworks.di.service.AtivacaoClienteService;

public class Main {
	public static void main(String[] args) {
		Cliente joao = new Cliente("João", "joao@xyz.com", "3499998888");
		Cliente maria = new Cliente("Maria", "maria@xyz.com", "1177772222");
		
		Notificador notificador = new NotificadorEmail();
		
		AtivacaoClienteService ativacaoClienteService = new AtivacaoClienteService(notificador);
		
		ativacaoClienteService.ativar(joao);
		ativacaoClienteService.ativar(maria);
		
	}
}  
