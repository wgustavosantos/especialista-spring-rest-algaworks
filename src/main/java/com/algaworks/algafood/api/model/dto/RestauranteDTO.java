package com.algaworks.algafood.api.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class RestauranteDTO {
    private Long id;
    private String nome;
    private BigDecimal precoFrete;
    private CozinhaDTO cozinha;
}
