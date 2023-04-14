package com.algaworks.algafood.domain.model.enums;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
public enum StatusPedido {

    CRIADO(1, "Criado"),
    CONFIRMADO(2, "Confirmado", CRIADO),
    ENTREGUE(3, "Entregue", CONFIRMADO),
    CANCELADO(4, "Cancelado", CRIADO);

    private final String status;
    private final int valor;

    private List<StatusPedido> statusAnteriores;

    private StatusPedido(int valor, String status, StatusPedido... statusAnteriores){
        this.valor = valor;
        this.status = status;
        this.statusAnteriores = Arrays.asList(statusAnteriores);
    }

    public boolean naoPodeAlterarPara(StatusPedido novoStatus){
        return !novoStatus.statusAnteriores.contains(this);
    }

    public boolean podeAlterarPara(StatusPedido novoStatus){
        return !naoPodeAlterarPara(novoStatus);
    }
}
