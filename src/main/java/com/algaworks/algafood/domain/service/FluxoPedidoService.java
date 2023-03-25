package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.exception.enums.ErrorMessage;
import com.algaworks.algafood.domain.model.Pedido;
import com.algaworks.algafood.domain.model.enums.StatusPedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;

@Service
public class FluxoPedidoService {

    @Autowired
    private PedidoService pedidoService;

    @Transactional
    public void confirmar(Long pedidoId){
        final Pedido pedido = pedidoService.buscar(pedidoId);
        if(!pedido.getStatus().equals(StatusPedido.CRIADO)){
            throw new NegocioException(String.format(
                    ErrorMessage.STATUS_PEDIDO.get(),
                    pedido.getId(),
                    pedido.getStatus().getStatus(),
                    StatusPedido.CONFIRMADO.getStatus()));
        }
        pedido.setStatus(StatusPedido.CONFIRMADO);
        pedido.setDataConfirmacao(OffsetDateTime.now());
    }

    @Transactional
    public void entregar(Long pedidoId){
        final Pedido pedido = pedidoService.buscar(pedidoId);
        if(!pedido.getStatus().equals(StatusPedido.CONFIRMADO)){
            throw new NegocioException(String.format(
                    ErrorMessage.STATUS_PEDIDO.get(),
                    pedido.getId(),
                    pedido.getStatus().getStatus(),
                    StatusPedido.ENTREGUE.getStatus()));
        }
        pedido.setStatus(StatusPedido.ENTREGUE);
        pedido.setDataEntrega(OffsetDateTime.now());
    }

    @Transactional
    public void cancelar(Long pedidoId) {
        final Pedido pedido = pedidoService.buscar(pedidoId);
        if(!pedido.getStatus().equals(StatusPedido.CRIADO)){
            throw new NegocioException(String.format(
                    ErrorMessage.STATUS_PEDIDO.get(),
                    pedido.getId(),
                    pedido.getStatus().getStatus(),
                    StatusPedido.CANCELADO.getStatus()));
        }
        pedido.setStatus(StatusPedido.CANCELADO);
        pedido.setDataCancelamento(OffsetDateTime.now());
    }
}
