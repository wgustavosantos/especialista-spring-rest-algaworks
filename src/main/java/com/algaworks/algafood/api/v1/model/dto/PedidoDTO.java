package com.algaworks.algafood.api.v1.model.dto;

import com.algaworks.algafood.domain.model.enums.StatusPedido;
import io.swagger.v3.oas.annotations.media.Schema;
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

    @Schema(example = "f9981ca4-5a5e-4da3-af04-933861df3e55")
    private String codigo;

    @Schema(example = "298.90")
    private BigDecimal subTotal;

    @Schema(example = "10.00")
    private BigDecimal taxaFrete;

    @Schema(example = "308.90")
    private BigDecimal valorTotal;

    @Schema(example = "CRIADO")
    private StatusPedido status;

    @Schema(example = "2022-12-01T20:34:04Z")
    private OffsetDateTime dataCriacao;

    @Schema(example = "2022-12-01T20:35:10Z")
    private OffsetDateTime dataConfirmacao;

    @Schema(example = "2022-12-01T20:55:30Z")
    private OffsetDateTime dataEntrega;

    @Schema(example = "2022-12-01T20:35:00Z")
    private OffsetDateTime dataCancelamento;

    private FormaPagamentoDTO formaPagamento;
    private RestauranteApenasNomeDTO restaurante;
    private UsuarioDTO cliente;
    private EnderecoDTO enderecoEntrega;
    private List<ItemPedidoDTO> itensPedido = new ArrayList<>();
}
