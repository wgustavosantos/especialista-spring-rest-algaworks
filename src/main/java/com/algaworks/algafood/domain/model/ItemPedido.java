package com.algaworks.algafood.domain.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ItemPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer quantidade;

    @Column(nullable = false)
    private BigDecimal precoUnitario;

    @Column(nullable = false)
    private BigDecimal precoTotal;

    private String observacao;

    @JoinColumn(nullable = false)
    @ManyToOne
    private Produto produto;

    @JoinColumn(nullable = false)
    @ManyToOne
    private Pedido pedido;

    public void calcularPrecoTotal() {
        if(precoUnitario == null)
            precoUnitario = BigDecimal.ZERO;
        if(quantidade == null)
            quantidade = 0;

        this.precoTotal = precoUnitario.multiply(new BigDecimal(quantidade));
    }
}
