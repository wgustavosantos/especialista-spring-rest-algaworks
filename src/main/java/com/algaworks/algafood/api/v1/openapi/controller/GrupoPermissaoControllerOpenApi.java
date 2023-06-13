package com.algaworks.algafood.api.v1.openapi.controller;

import com.algaworks.algafood.api.v1.model.dto.PermissaoDTO;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

public interface GrupoPermissaoControllerOpenApi {

    ResponseEntity<Void> desassociarPermissao(Long grupoId, Long permissaoId);

    CollectionModel<PermissaoDTO> listarPermissoes(Long grupoId);

    ResponseEntity<Void> associarPermissao(Long grupoId, Long permissaoId);
}