package com.algaworks.algafood.api.v1.model.inputDto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
public class UsuarioComSenhaInputDTO extends UsuarioInputDTO {

    @ApiModelProperty(example = "123", required = true)
    @NotBlank
    private String senha;
}