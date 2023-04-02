package com.algaworks.algafood.api.model.inputDto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ItemPedidoInputDTO {

    @NotNull
    private Long produtoId;

    @NotNull
    private Integer quantidade;

    private String observacao;
}
