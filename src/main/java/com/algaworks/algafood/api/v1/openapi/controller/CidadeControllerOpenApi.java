package com.algaworks.algafood.api.v1.openapi.controller;

import com.algaworks.algafood.api.v1.model.dto.CidadeDTO;
import com.algaworks.algafood.api.v1.model.inputDto.CidadeInputDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Cidades")
public interface CidadeControllerOpenApi {

    @Operation(summary = "Cadastra uma cidade", description = "Cadastro de uma cidade, " +
            "necessita de um estado e um nome válido", responses = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "400", description = "ID da cidade inválido",
                    content = @Content(schema = @Schema(ref = "Problema"))
            ),
            @ApiResponse(responseCode = "404", description = "Cidade não encontrada",
                    content = @Content(schema = @Schema(ref = "Problema")))
    })
    ResponseEntity<CidadeDTO> adicionar(@RequestBody(description = "Representação de uma nova cidade", required = true) CidadeInputDTO cidadeInputDTO);

    @Operation(summary = "Lista as cidades")
    CollectionModel<CidadeDTO> listar();

    @Operation(summary = "Busca uma cidade por Id", responses = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "400", description = "ID da cidade inválido",
                    content = @Content(schema = @Schema(ref = "Problema"))
            )
    })
    CidadeDTO buscar(@Parameter(description = "ID de uma cidade", example = "1", required = true) Long cidadeId);

    @Operation(summary = "Atualizado uma cidade por ID", responses = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "400", description = "ID da cidade inválido",
                    content = @Content(schema = @Schema(ref = "Problema"))
            ),
            @ApiResponse(responseCode = "404", description = "Cidade não encontrada",
                    content = @Content(schema = @Schema(ref = "Problema")))
    })
    CidadeDTO atualizar(@RequestBody(description = "Representação de uma cidade com dados atualizados", required = true) CidadeInputDTO cidadeInputDTO,
                        @Parameter(description = "ID de uma cidade", example = "1", required = true) Long cidadeId);

    @Operation(summary = "Excluir uma cidade por ID", responses = {
            @ApiResponse(responseCode = "204"),
            @ApiResponse(responseCode = "400", description = "ID da cidade inválido",
                    content = @Content(schema = @Schema(ref = "Problema"))
            ),
            @ApiResponse(responseCode = "404", description = "Cidade não encontrada",
                    content = @Content(schema = @Schema(ref = "Problema")))
    })
    void deletar(@Parameter(description = "ID de uma cidade", example = "1", required = true) Long cidadeId);
}
