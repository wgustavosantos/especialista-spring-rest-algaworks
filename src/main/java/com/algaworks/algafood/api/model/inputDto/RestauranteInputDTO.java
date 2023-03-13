package com.algaworks.algafood.api.model.inputDto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

/*DTO criado para a serialização de objetos Restaurante vindos de uma requisição que só necessistam
* dos atriburos declarados abaixo*/
@Getter
@Setter
public class RestauranteInputDTO {

    @NotBlank
    private String nome;

    @NotNull
    @PositiveOrZero
    private BigDecimal taxaFrete;

    @NotNull
    @Valid
    private CozinhaRefDto cozinha;
}
