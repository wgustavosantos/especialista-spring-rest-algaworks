package com.algaworks.algafood.api.model.inputDto;

import com.algaworks.algafood.api.model.dto.ReferenciaIdDto.CidadeRefDTO;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class EnderecoInputDTO {

    @NotBlank
    private String cep;
    @NotBlank
    private String logradouro;
    @NotBlank
    private String numero;
    private String complemento;
    @NotBlank
    private String bairro;

    @Valid
    @NotNull
    private CidadeRefDTO cidade;
}
