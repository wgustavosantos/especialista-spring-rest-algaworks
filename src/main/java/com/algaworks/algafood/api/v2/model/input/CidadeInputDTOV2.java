package com.algaworks.algafood.api.v2.model.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CidadeInputDTOV2 {

    @NotBlank(message = "Não é permitido um campo vazio")
    private String nomeCidade;

    @Valid
    @NotNull
    private Long idEstado;
}
