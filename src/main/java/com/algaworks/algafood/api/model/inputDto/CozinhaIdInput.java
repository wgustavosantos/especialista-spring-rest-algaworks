package com.algaworks.algafood.api.model.inputDto;


import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/*DTO criado para referência de um objeto alinhado a Restaurante*/
@Getter
@Setter
public class CozinhaIdInput {

    @ApiModelProperty(example = "1", required = true)
    @NotNull
    private Long id;
}
