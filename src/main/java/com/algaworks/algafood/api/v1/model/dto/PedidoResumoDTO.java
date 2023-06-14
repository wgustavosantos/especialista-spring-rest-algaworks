package com.algaworks.algafood.api.v1.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

//@JsonFilter("pedidosFilter")
@Relation(collectionRelation = "pedidos")
@Getter
@Setter
public class PedidoResumoDTO extends RepresentationModel<PedidoResumoDTO> {

    @Schema(example = "04813f77-79b5-11ec-9a17-0242ac1b0002")
    private String codigo;

    @Schema(example = "298.90")
    private BigDecimal subTotal;

    @Schema(example = "10.00")
    private BigDecimal taxaFrete;

    @Schema(example = "308.90")
    private BigDecimal valorTotal;

    @Schema(example = "2019-12-01T20:34:04Z")
    private OffsetDateTime dataCriacao;

    @Schema(example = "CRIADO")
    private String status;


    private UsuarioDTO cliente;

    private RestauranteApenasNomeDTO restaurante;
}
