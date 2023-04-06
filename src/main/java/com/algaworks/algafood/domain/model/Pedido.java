package com.algaworks.algafood.domain.model;

import com.algaworks.algafood.domain.event.PedidoConfirmadoEvent;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.exception.enums.ErrorMessage;
import com.algaworks.algafood.domain.model.enums.StatusPedido;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.domain.AbstractAggregateRoot;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class Pedido extends AbstractAggregateRoot<Pedido> {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String codigo;
    @Column(nullable = false, name = "subtotal")
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

    @ManyToOne(fetch = FetchType.LAZY)
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
    @Enumerated(EnumType.STRING)
    private StatusPedido status = StatusPedido.CRIADO;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<ItemPedido> itensPedido = new ArrayList<>();

    public void calcularValorTotal(){
        this.subTotal = itensPedido.stream()
                .map(ItemPedido::getPrecoTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        this.valorTotal = this.subTotal.add(this.taxaFrete);
    }

    public void atribuirPedidoAosItens() {
        getItensPedido().forEach(item -> item.setPedido(this));
    }

    public void confirmar(){
        setStatus(StatusPedido.CONFIRMADO);
        setDataConfirmacao(OffsetDateTime.now());

        registerEvent(new PedidoConfirmadoEvent(this));
        System.out.println("Evento registrado");
    }

    public void cancelar(){
        setStatus(StatusPedido.CANCELADO);
        setDataCancelamento(OffsetDateTime.now());
    }
    public void entregar(){
        setStatus(StatusPedido.ENTREGUE);
        setDataEntrega(OffsetDateTime.now());
    }

    private void setStatus(StatusPedido novoStatus){
        if(getStatus().naoPodeAlterarPara(novoStatus)){
            throw new NegocioException(String.format(
                    ErrorMessage.STATUS_PEDIDO.get(),
                    getCodigo(),
                    this.getStatus().getStatus(),
                    novoStatus.getStatus()));
        }

        this.status = novoStatus;
    }

    @PrePersist
    private void SetCodigo(){
        this.codigo = UUID.randomUUID().toString();
    }

}
