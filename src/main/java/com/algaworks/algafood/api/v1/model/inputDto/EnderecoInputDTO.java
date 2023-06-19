package com.algaworks.algafood.api.v1.model.inputDto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class EnderecoInputDTO {

    @Schema(example = "38400-000")
    @NotBlank
    private String cep;

    @Schema(example = "Rua Floriano Peixoto")
    @NotBlank
    private String logradouro;

    @Schema(example = "\"1500\"")
    @NotBlank
    private String numero;

    @Schema(example = "Apto 901")
    private String complemento;
    @NotBlank

    @Schema(example = "Centro")
    private String bairro;

    @Valid
    @NotNull
    private CidadeIdInput cidade;
}
