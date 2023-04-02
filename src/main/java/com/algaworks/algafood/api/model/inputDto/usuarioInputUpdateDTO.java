package com.algaworks.algafood.api.model.inputDto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class usuarioInputUpdateDTO {

    @NotBlank
    private String nome;

    @NotBlank
    private String email;
}
