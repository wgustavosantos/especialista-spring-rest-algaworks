package com.algaworks.di.service;

import com.algaworks.di.modelo.Cliente;
import com.algaworks.di.modelo.Produto;
import com.algaworks.di.notificacao.Notificador;

public class EmissaoNotaFiscalService {
	
	private Notificador notificador;

	public EmissaoNotaFiscalService(Notificador notificador) {
		this.notificador = notificador;
	}
	
	public void emitir(Cliente c, Produto p) {
		// TODO Auto-generated method stub
		notificador.notificar(c,"Nota fiscal do produto " +
		p.getNome() + " foi emitida! ");
	}
}
