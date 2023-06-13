package com.algaworks.algafood.api.v1.model.inputDto;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/*DTO criado para referência de um objeto alinhado a Restaurante*/
@Getter
@Setter
public class CozinhaIdInput {

    @NotNull
    private Long id;
}
