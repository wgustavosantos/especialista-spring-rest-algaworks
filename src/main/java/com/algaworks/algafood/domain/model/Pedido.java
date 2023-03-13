package com.algaworks.algafood.domain.model;

import com.algaworks.algafood.domain.model.enums.StatusPedido;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Pedido {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "subTotal")
    private BigDecimal subTotal;

    @Column(nullable = false)
    private BigDecimal taxaFrete;

    @Column(nullable = false)
    private BigDecimal valorTotal;

    @CreationTimestamp
    @Column(columnDefinition = "DateTime", nullable = false)
    private OffsetDateTime dataCriacao;

    @Column(columnDefinition = "DateTime")
    private OffsetDateTime dataConfirmacao;

    @Column(columnDefinition = "DateTime")
    private OffsetDateTime dataCancelamento;

    @Column(columnDefinition = "DateTime")
    private OffsetDateTime dataEntrega;

    @ManyToOne
    @JoinColumn(nullable = false)
    private FormaPagamento formaPagamento;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Restaurante restaurante;

    @ManyToOne
    @JoinColumn(name = "usuario_cliente_id", nullable = false)
    private Usuario cliente;

    @Embedded
    private Endereco enderecoEntrega;

    @Column(nullable = false, columnDefinition = "varchar(10)")
    private StatusPedido status;

    @OneToMany(mappedBy = "pedido")
    private List<ItemPedido> itensPedido = new ArrayList<>();
}
