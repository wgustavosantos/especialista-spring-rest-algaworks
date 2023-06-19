package com.algaworks.algafood.api.v1.model.inputDto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.NotBlank;

@Getter
@Setter
public class FormaPagamentoInputDTO {

    @Schema(example = "Cartão de crédito")
    @NotBlank
    private String descricao;
}
