package com.algaworks.algafood.api.v2.model.input;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.NotBlank;

@Getter
@Setter
public class CozinhaInputDTOV2 {

    @Schema(example = "Brasileira")
    @NotBlank
    private String nomeCozinha;
}
