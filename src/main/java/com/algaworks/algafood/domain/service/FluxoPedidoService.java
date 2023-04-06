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
        System.out.println("Buscando pedidos no banco de dados");
        final Pedido pedido = pedidoService.buscar(codigoPedido);
        pedido.confirmar();
        System.out.println("Antes do método save do Spring Data");
        pedidoRepository.save(pedido);
        System.out.println("Depois do método save do Spring Data");

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
    }
}
