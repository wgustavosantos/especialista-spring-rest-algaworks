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
    public void confirmar(Long pedidoId){
        final Pedido pedido = pedidoService.buscar(pedidoId);
       pedido.confirmar();
    }

    @Transactional
    public void entregar(Long pedidoId){
        final Pedido pedido = pedidoService.buscar(pedidoId);
        pedido.entregar();
    }

    @Transactional
    public void cancelar(Long pedidoId) {
        final Pedido pedido = pedidoService.buscar(pedidoId);
        pedido.cancelar();
    }
}
