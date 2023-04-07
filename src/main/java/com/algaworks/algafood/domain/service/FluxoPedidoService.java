package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.model.Pedido;
import com.algaworks.algafood.domain.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FluxoPedidoService {

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Transactional
    public void confirmar(String codigoPedido) {
        final Pedido pedido = pedidoService.buscar(codigoPedido);
        pedido.confirmar();
        pedidoRepository.save(pedido);

       /*Chamada para envio de email retirada para publicação
        de eventos no aggregate root da entidade*/
    }

    @Transactional
    public void entregar(String codigoPedido) {
        final Pedido pedido = pedidoService.buscar(codigoPedido);
        pedido.entregar();
    }

    @Transactional
    public void cancelar(String codigoPedido) {
        final Pedido pedido = pedidoService.buscar(codigoPedido);
        pedido.cancelar();
        pedidoRepository.save(pedido);
    }
}
