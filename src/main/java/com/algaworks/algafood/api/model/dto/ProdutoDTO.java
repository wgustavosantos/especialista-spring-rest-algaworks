package com.algaworks.algafood.api.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProdutoDTO {

    private Integer id;

    private String nome;

    private String descricao;

    private BigDecimal preco;

    private Boolean ativo;

}
