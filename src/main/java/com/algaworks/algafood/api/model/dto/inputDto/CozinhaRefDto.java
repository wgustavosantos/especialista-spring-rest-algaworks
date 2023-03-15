package com.algaworks.algafood.api.model.dto.inputDto;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/*DTO criado para referência de um objeto alinhado a Restaurante*/
@Getter
@Setter
public class CozinhaRefDto {

    @NotNull
    private Long id;
}
