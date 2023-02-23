package com.algaworks.algafood.domain.model.enums;

import lombok.Getter;

@Getter
public enum StatusPedido {

    CRIADO(1, "Pedido criado"),
    CONFIRMADO(2, "Pedido confirmado"),
    ENTREGUE(3, "Pedido entregue"),
    CANCELADO(4, "Pedido cancelado");

    private final String status;
    private final int valor;

    private StatusPedido(int valor, String status){
        this.valor = valor;
        this.status = status;
    }
}
