package com.algaworks.algafood.api.v1.model.dto;

import com.algaworks.algafood.domain.model.enums.StatusPedido;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Relation(collectionRelation = "pedidos")
@Getter
@Setter
public class PedidoDTO extends RepresentationModel<PedidoDTO> {

    private String codigo;

    private BigDecimal subTotal;

    private BigDecimal taxaFrete;

    private BigDecimal valorTotal;

    private StatusPedido status;

    private OffsetDateTime dataCriacao;

    private OffsetDateTime dataConfirmacao;

    private OffsetDateTime dataEntrega;

    private OffsetDateTime dataCancelamento;

    private FormaPagamentoDTO formaPagamento;
    private RestauranteApenasNomeDTO restaurante;
    private UsuarioDTO cliente;
    private EnderecoDTO enderecoEntrega;
    private List<ItemPedidoDTO> itensPedido = new ArrayList<>();
}
