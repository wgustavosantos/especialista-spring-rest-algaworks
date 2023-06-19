package com.algaworks.algafood.api.v1.model.inputDto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Getter
@Setter
public class UsuarioInputDTO {

    @Schema(example = "João da Silva")
    @NotBlank
    private String nome;

    @Schema(example = "joao.ger@algafood.com.br")
    @NotBlank
    @Email
    private String email;
}