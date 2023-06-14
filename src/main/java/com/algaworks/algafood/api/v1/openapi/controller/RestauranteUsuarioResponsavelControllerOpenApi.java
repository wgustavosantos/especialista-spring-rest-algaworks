package com.algaworks.algafood.api.v1.openapi.controller;

import com.algaworks.algafood.api.v1.model.dto.UsuarioDTO;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

@SecurityRequirement(name = "security_auth")
public interface RestauranteUsuarioResponsavelControllerOpenApi {

    CollectionModel<UsuarioDTO> listarResponsaveis(Long restauranteId);

    ResponseEntity<Void> desassociarResponsavel(Long restauranteId, Long usuarioId);

    ResponseEntity<Void> associarResponsavel(Long restauranteId, Long usuarioId);
}
