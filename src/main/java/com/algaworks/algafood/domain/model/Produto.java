package com.algaworks.algafood.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Produto {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable=false)
    private String nome;

    private String descricao;

    @Column(nullable=false)
    private BigDecimal preco;

    @Column(nullable=false)
    private Boolean ativo;

    @ManyToOne
    private Restaurante restaurante;
}
