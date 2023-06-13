package com.algaworks.algafood.api.v1.openapi.model;

import com.algaworks.algafood.api.v1.model.dto.PedidoResumoDTO;
import lombok.Data;
import org.springframework.hateoas.Links;

import java.util.List;

public class PedidosResumoModelOpenApi {

    private PedidosResumoEmbeddedModelOpenApi _embedded;
    private Links _links;
    private PageModelOpenApi page;

    @Data
    public class PedidosResumoEmbeddedModelOpenApi {

        private List<PedidoResumoDTO> pedidos;

    }
}
