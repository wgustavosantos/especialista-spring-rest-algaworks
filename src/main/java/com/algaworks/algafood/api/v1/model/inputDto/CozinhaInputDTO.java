package com.algaworks.algafood.api.v1.model.inputDto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class CozinhaInputDTO {

    @ApiModelProperty(example = "Brasileira", required = true)
    @NotBlank
    private String nome;
}