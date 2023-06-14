package com.algaworks.algafood.api.v1.openapi.controller;

import com.algaworks.algafood.api.v1.model.dto.GrupoDTO;
import com.algaworks.algafood.api.v1.model.inputDto.GrupoInputDTO;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

@SecurityRequirement(name = "security_auth")
public interface GrupoControllerOpenApi {

    CollectionModel<GrupoDTO> listar();


    ResponseEntity<GrupoDTO> buscar(Long grupoId);

    ResponseEntity<GrupoDTO> adicionar(GrupoInputDTO GrupoInputDTO);

    ResponseEntity<GrupoDTO> atualizar(Long grupoId, GrupoInputDTO grupoInputDTO);

    ResponseEntity<Void> deletar(Long grupoId);

}