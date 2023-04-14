package com.algaworks.algafood.api.openapi.controller;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.model.dto.GrupoDTO;
import com.algaworks.algafood.api.model.inputDto.GrupoInputDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

@Api(tags = "Grupos")
public interface GrupoControllerOpenApi {

    @ApiOperation("Lista os grupos")
    CollectionModel<GrupoDTO> listar();

    @ApiOperation("Busca um grupo por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "ID da grupo inválido", content = @Content(schema = @Schema(implementation = Problem.class))),
            @ApiResponse(responseCode = "404", description = "Grupo não encontrado", content = @Content(schema = @Schema(implementation = Problem.class)))
    })
    ResponseEntity<GrupoDTO> buscar(
            @ApiParam(value = "ID de um grupo", example = "1", required = true)
            Long grupoId);

    @ApiOperation("Cadastra um grupo")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Grupo cadastrado"),
    })
    ResponseEntity<GrupoDTO> adicionar(
            @ApiParam(name = "corpo", value = "Representação de um novo grupo", required = true)
            GrupoInputDTO GrupoInputDTO);

    @ApiOperation("Atualiza um grupo por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Grupo atualizado"),
            @ApiResponse(responseCode = "404", description = "Grupo não encontrado", content = @Content(schema = @Schema(implementation = Problem.class)))
    })
    ResponseEntity<GrupoDTO> atualizar(
            @ApiParam(value = "ID de um grupo", example = "1", required = true)
            Long grupoId,
            @ApiParam(name = "corpo", value = "Representação de um grupo com os novos dados", required = true)
            GrupoInputDTO grupoInputDTO);

    @ApiOperation("Exclui um grupo por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Grupo excluído"),
            @ApiResponse(responseCode = "404", description = "Grupo não encontrado", content = @Content(schema = @Schema(implementation = Problem.class)))
    })
    ResponseEntity<Void> deletar(
            @ApiParam(value = "ID de um grupo", example = "1", required = true)
            Long grupoId);

}