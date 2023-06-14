package com.algaworks.algafood.api.v1.openapi.controller;

import com.algaworks.algafood.api.v1.model.dto.GrupoDTO;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

@SecurityRequirement(name = "security_auth")
public interface UsuarioGrupoControllerOpenApi {

    CollectionModel<GrupoDTO> listar(Long usuarioId);

    ResponseEntity<Void> removerGrupo(Long usuarioId, Long grupoId);

    ResponseEntity<Void> adicionarGrupo(Long usuarioId, Long grupoId);
}