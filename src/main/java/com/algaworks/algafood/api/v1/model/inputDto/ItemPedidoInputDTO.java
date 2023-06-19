package com.algaworks.algafood.api.v1.model.inputDto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.NotNull;

@Getter
@Setter
public class ItemPedidoInputDTO {

    @Schema(example = "1")
    @NotNull
    private Long produtoId;

    @Schema(example = "2")
    @NotNull
    private Integer quantidade;

    @Schema(example = "Menos picante, por favor")
    private String observacao;
}
