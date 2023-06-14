package com.algaworks.algafood.api.v1.openapi.controller;

import com.algaworks.algafood.api.v1.model.dto.GrupoDTO;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;


public interface UsuarioGrupoControllerOpenApi {

    CollectionModel<GrupoDTO> listar(Long usuarioId);

    ResponseEntity<Void> removerGrupo(Long usuarioId, Long grupoId);

    ResponseEntity<Void> adicionarGrupo(Long usuarioId, Long grupoId);
}