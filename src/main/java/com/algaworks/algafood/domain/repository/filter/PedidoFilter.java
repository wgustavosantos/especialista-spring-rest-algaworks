package com.algaworks.algafood.domain.repository.filter;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.OffsetDateTime;

/**
 * Classe representa as prorpriedades para fazer a filtragem de requisições complexas. <br>
 * O consumidor da API especifica as propriedades desejadas baseados
 * em operadores lógicos e de igualdade. Todas as propriedades são opcionais e podem ser combinadas.
 */
@Setter
@Getter
public class PedidoFilter {

    private Long clienteId;
    private Long restauranteId;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private OffsetDateTime dataCriacaoInicio;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private OffsetDateTime dataCriacaoFim;
}
