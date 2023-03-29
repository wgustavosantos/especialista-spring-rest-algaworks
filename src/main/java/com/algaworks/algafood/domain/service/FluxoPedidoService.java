package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.model.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FluxoPedidoService {

    @Autowired
    private PedidoService pedidoService;

    @Transactional
    public void confirmar(String codigoPedido){
        final Pedido pedido = pedidoService.buscar(codigoPedido);
       pedido.confirmar();
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
