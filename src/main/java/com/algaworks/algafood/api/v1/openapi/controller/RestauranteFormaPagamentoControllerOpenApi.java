package com.algaworks.algafood.api.v1.openapi.controller;

import com.algaworks.algafood.api.v1.model.dto.FormaPagamentoDTO;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

@SecurityRequirement(name = "security_auth")
public interface RestauranteFormaPagamentoControllerOpenApi {

    CollectionModel<FormaPagamentoDTO> listar(Long restauranteId);

    ResponseEntity<Void> desassociar(Long restauranteId, Long formaPagamentoId);

    ResponseEntity<Void> associar(Long restauranteId, Long formaPagamentoId);
}