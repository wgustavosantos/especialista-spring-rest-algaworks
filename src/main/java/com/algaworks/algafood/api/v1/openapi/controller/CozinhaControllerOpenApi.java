package com.algaworks.algafood.api.v1.openapi.controller;

import com.algaworks.algafood.api.v1.model.dto.CozinhaDTO;
import com.algaworks.algafood.api.v1.model.inputDto.CozinhaInputDTO;
import com.algaworks.algafood.core.springdoc.PageableParameter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Cozinhas")
public interface CozinhaControllerOpenApi {

    @PageableParameter
    @Operation(summary = "Lista as cozinhas com paginação")
    PagedModel<CozinhaDTO> listar(@Parameter(hidden = true) Pageable pageable);

    @Operation(summary = "Busca uma cozinha por ID", responses = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "400", description = "ID da cozinha inválido",
                    content = @Content(schema = @Schema(ref = "Problema"))),
            @ApiResponse(responseCode = "404", description = "Cozinha não encontrada",
                    content = @Content(schema = @Schema(ref = "Problema")))
    })
    CozinhaDTO buscar(@Parameter(description = "ID de uma cozinha", example = "1", required = true) Long cozinhaId);

    @Operation(summary = "Cadastra uma cozinha", responses = {
            @ApiResponse(responseCode = "201", description = "Cozinha cadastrada"),
    })
    ResponseEntity<CozinhaDTO> adicionar(
            @RequestBody(description = "Representação de uma nova cozinha", required = true) CozinhaInputDTO CozinhaInputDTO);

    @Operation(summary = "Atualiza uma cozinha por ID", responses = {
            @ApiResponse(responseCode = "200", description = "Cozinha atualizada"),
            @ApiResponse(responseCode = "404", description = "Cozinha não encontrada",
                    content = @Content(schema = @Schema(ref = "Problema"))),
    })
    CozinhaDTO atualizar(
            @RequestBody(description = "Representação de uma cozinha com os novos dados", required = true) CozinhaInputDTO CozinhaInputDTO,
            @Parameter(description = "ID de uma cozinha", example = "1", required = true) Long cozinhaId);

    @Operation(summary = "Exclui uma cozinha por ID", responses = {
            @ApiResponse(responseCode = "204", description = "Cozinha excluída"),
            @ApiResponse(responseCode = "404", description = "Cozinha não encontrada",
                    content = @Content(schema = @Schema(ref = "Problema")))
    })
    void deletar(@Parameter(description = "ID de uma cozinha", example = "1", required = true) Long cozinhaId);


}