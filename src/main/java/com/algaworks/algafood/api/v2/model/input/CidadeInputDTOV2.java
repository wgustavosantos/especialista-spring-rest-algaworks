package com.algaworks.algafood.api.v2.model.input;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@ApiModel(value = "CidadeInput", description = "Representa uma cidade")
@Getter
@Setter
public class CidadeInputDTOV2 {

    @ApiModelProperty(example = "Uberlândia", required = true   )
    @NotBlank(message = "Não é permitido um campo vazio")
    private String nomeCidade;

    @ApiModelProperty(example = "1", required = true)
    @Valid
    @NotNull
    private Long idEstado;
}
