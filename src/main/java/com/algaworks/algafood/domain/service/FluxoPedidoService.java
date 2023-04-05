package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.model.Pedido;
import com.algaworks.algafood.domain.service.EnvioEmailSerivce.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FluxoPedidoService {

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private EnvioEmailSerivce envioEmailSerivce;

    @Transactional
    public void confirmar(String codigoPedido){
        final Pedido pedido = pedidoService.buscar(codigoPedido);
       pedido.confirmar();

        Mensagem mensagem = Mensagem.builder()
                .assunto(pedido.getRestaurante().getNome() + " - Pedido confirmado.")
                .corpo("O pedido de código <strong>" + pedido.getId() + "<strong/> foi confirmado!")
                .destinatario(pedido.getCliente().getEmail())
                .build();

       envioEmailSerivce.enviar(mensagem);
    }

    @Transactional
    public void entregar(String codigoPedido){
        final Pedido pedido = pedidoService.buscar(codigoPedido);
        pedido.entregar();
    }

    @Transactional
    public void cancelar(String codigoPedido) {
        final Pedido pedido = pedidoService.buscar(codigoPedido);
        pedido.cancelar();
    }
}
