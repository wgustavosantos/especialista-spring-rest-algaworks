package com.algaworks.algafood.api.model.dto;

import com.fasterxml.jackson.annotation.JsonFilter;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@JsonFilter("pedidosFilter")
@Getter
@Setter
public class PedidoResumoDTO {
    private String codigo;
    private BigDecimal subTotal;
    private BigDecimal taxaFrete;
    private BigDecimal valorTotal;
    private OffsetDateTime dataCriacao;
    private String status;
    private RestauranteResumoDTO restaurante;
    private UsuarioDTO cliente;
}
