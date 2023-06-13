package com.algaworks.algafood.api.v2.model.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class CozinhaInputDTOV2 {

    @NotBlank
    private String nomeCozinha;
}
