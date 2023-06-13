package com.algaworks.algafood.api.v1.openapi.controller;

import com.algaworks.algafood.api.v1.model.dto.UsuarioDTO;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

public interface RestauranteUsuarioResponsavelControllerOpenApi {

    CollectionModel<UsuarioDTO> listarResponsaveis(Long restauranteId);

    ResponseEntity<Void> desassociarResponsavel(Long restauranteId, Long usuarioId);

    ResponseEntity<Void> associarResponsavel(Long restauranteId, Long usuarioId);
}
