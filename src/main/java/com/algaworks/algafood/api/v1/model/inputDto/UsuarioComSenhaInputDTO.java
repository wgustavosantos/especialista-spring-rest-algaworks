package com.algaworks.algafood.api.v1.model.inputDto;

import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.NotBlank;

@Setter
@Getter
public class UsuarioComSenhaInputDTO extends UsuarioInputDTO {

    @NotBlank
    private String senha;
}