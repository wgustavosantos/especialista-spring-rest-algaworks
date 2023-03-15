package com.algaworks.algafood.api.model.dto.inputDto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CidadeInputDTO {

    @NotBlank(message = "Não é permitido um campo vazio")
    private String nome;

    @NotNull
    @Valid
    private EstadoIdInputDTO estado;
}
