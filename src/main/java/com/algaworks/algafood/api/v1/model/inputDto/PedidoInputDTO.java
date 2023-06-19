package com.algaworks.algafood.api.v1.model.inputDto;

import lombok.Getter;
import lombok.Setter;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class PedidoInputDTO {

    @Valid
    @NotNull
    private RestauranteIdInputDTO restaurante;

    @Valid
    @NotNull
    private FormaPagamentoIdDTO formaPagamento;

    @Valid
    @NotNull
    private EnderecoInputDTO enderecoEntrega;

    @Valid
    @NotNull
    @Size(min = 1)
    private List<ItemPedidoInputDTO> itensPedido = new ArrayList<>();

}
