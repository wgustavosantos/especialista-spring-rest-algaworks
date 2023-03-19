package com.algaworks.algafood.api.model.dto.inputDto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class usuarioInputSenhaDTO {

    @NotBlank
    private String senhaAtual;
    @NotBlank
    private String novaSenha;
}
