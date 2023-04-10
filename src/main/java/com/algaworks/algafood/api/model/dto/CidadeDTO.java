package com.algaworks.algafood.api.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(value = "Cidade", description = "Representa uma cidade")
@Getter
@Setter
public class CidadeDTO {

    @ApiModelProperty(example = "1")
    private Long id;

    private String nome;
    private EstadoDTO estado;
}
