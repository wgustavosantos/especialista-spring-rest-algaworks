package com.algaworks.algafood.api.v1.openapi.controller;

import com.algaworks.algafood.api.v1.model.dto.PermissaoDTO;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

@SecurityRequirement(name = "security_auth")
public interface GrupoPermissaoControllerOpenApi {

    ResponseEntity<Void> desassociarPermissao(Long grupoId, Long permissaoId);

    CollectionModel<PermissaoDTO> listarPermissoes(Long grupoId);

    ResponseEntity<Void> associarPermissao(Long grupoId, Long permissaoId);
}