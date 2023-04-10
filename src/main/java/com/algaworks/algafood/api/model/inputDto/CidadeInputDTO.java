package com.algaworks.algafood.api.model.inputDto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

//@ApiModel(value = "Cidade input", description = "Representa uma cidade")
@Getter
@Setter
public class CidadeInputDTO {

    @ApiModelProperty(example = "Uberlândia", required = true   )
    @NotBlank(message = "Não é permitido um campo vazio")
    private String nome;

    @ApiModelProperty(example = "1", required = true)
    @Valid
    @NotNull
    private EstadoIdInputDTO estado;
}
