package com.algaworks.algafood.domain.model.enums;

import lombok.Getter;

@Getter
public enum StatusPedido {

    CRIADO(1, "Criado"),
    CONFIRMADO(2, "Confirmado"),
    ENTREGUE(3, "Entregue"),
    CANCELADO(4, "Cancelado");

    private final String status;
    private final int valor;

    private StatusPedido(int valor, String status){
        this.valor = valor;
        this.status = status;
    }
}
