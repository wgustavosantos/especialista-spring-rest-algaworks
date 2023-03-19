package com.algaworks.algafood.api.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class RestauranteDTO {
    private Long id;
    private String nome;
    private BigDecimal taxaFrete;
    private CozinhaDTO cozinha;
    private Boolean ativo = Boolean.TRUE;
    private EnderecoDTO endereco;
}
