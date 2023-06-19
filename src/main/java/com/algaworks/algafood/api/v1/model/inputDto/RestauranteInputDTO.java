package com.algaworks.algafood.api.v1.model.inputDto;

import com.algaworks.algafood.core.validation.ValorZeroIncluiDescricao;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

/*DTO criado para a serialização de objetos Restaurante vindos de uma requisição que só necessistam
* dos atriburos declarados abaixo*/
@Getter
@Setter
@ValorZeroIncluiDescricao(valorField = "taxaFrete", descricaoField = "nome", descricaoObrigatoria="Frete Grátis")
public class RestauranteInputDTO {

    @Schema(example = "Thai Gourmet")
    @NotBlank
    private String nome;

    @Schema(example = "12.00")
    @NotNull
    @PositiveOrZero
    private BigDecimal taxaFrete;

    @NotNull
    @Valid
    private CozinhaIdInput cozinha;

    @Valid
    @NotNull
    private EnderecoInputDTO endereco;
}
