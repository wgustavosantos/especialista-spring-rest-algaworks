package com.algaworks.algafood.api.v1.model.inputDto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

//@ApiModel(value = "Cidade input", description = "Representa uma cidade")
@Getter
@Setter
public class CidadeInputDTO {

    @Schema(example = "Uberlândia")
    @NotBlank(message = "Não é permitido um campo vazio")
    private String nome;

    @Valid
    @NotNull
    private EstadoIdInputDTO estado;
}
