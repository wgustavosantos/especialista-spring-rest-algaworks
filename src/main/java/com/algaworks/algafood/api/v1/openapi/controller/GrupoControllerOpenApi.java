package com.algaworks.algafood.api.v1.openapi.controller;

import com.algaworks.algafood.api.v1.model.dto.GrupoDTO;
import com.algaworks.algafood.api.v1.model.inputDto.GrupoInputDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Grupos")
public interface GrupoControllerOpenApi {

    @Operation(summary = "Lista os grupos")
    CollectionModel<GrupoDTO> listar();

    @Operation(summary = "Busca um grupo por ID")
    ResponseEntity<GrupoDTO> buscar(@Parameter(description = "ID de um grupo", example = "1", required = true) Long grupoId);

    @Operation(summary = "Cadastra um grupo")
    ResponseEntity<GrupoDTO> adicionar(@RequestBody(description = "Representação de um novo grupo", required = true) GrupoInputDTO grupoInput);

    @Operation(summary = "Atualiza um grupo por ID")
    ResponseEntity<GrupoDTO> atualizar(
            @Parameter(description = "ID de um grupo", example = "1", required = true) Long grupoId,
            @RequestBody(description = "Representação de um grupo com os novos dados", required = true) GrupoInputDTO grupoInput);

    @Operation(summary = "Exclui um grupo por ID")
    ResponseEntity<Void> deletar(Long grupoId);

}