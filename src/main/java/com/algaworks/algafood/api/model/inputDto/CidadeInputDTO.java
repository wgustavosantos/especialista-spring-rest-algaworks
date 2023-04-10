package com.algaworks.algafood.api.model.inputDto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@ApiModel(value = "Representa uma cidade")
@Getter
@Setter
public class CidadeInputDTO {

    @ApiModelProperty(example = "Uberlândia")
    @NotBlank(message = "Não é permitido um campo vazio")
    private String nome;

    @ApiModelProperty(example = "1")
    @NotNull
    @Valid
    private EstadoIdInputDTO estado;
}
