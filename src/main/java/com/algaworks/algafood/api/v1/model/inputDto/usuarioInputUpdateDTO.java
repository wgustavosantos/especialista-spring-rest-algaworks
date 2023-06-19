package com.algaworks.algafood.api.v1.model.inputDto;

import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.NotBlank;

@Getter
@Setter
public class usuarioInputUpdateDTO {

    @NotBlank
    private String nome;

    @NotBlank
    private String email;
}
