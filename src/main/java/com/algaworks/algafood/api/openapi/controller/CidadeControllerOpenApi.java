package com.algaworks.algafood.api.openapi.controller;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.model.dto.CidadeDTO;
import com.algaworks.algafood.api.model.inputDto.CidadeInputDTO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CidadeControllerOpenApi {
    @ApiOperation("Cadastra uma cidade")
    @ApiResponse(responseCode = "201", description = "Cidade cadastrada")
    ResponseEntity<CidadeDTO> adicionar(@ApiParam(name = "corpo", value = "Representação de uma nova cidade", required = true)
                                       CidadeInputDTO cidadeInputDTO);

    @ApiOperation("Lista as cidades")
    ResponseEntity<List<CidadeDTO>> listar();

    @ApiOperation("Busca uma cidade por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "ID da cidade inválido", content = @Content(schema = @Schema(implementation = Problem.class))),
            @ApiResponse(responseCode = "404", description = "Cidade não encontrada", content = @Content(schema = @Schema(implementation = Problem.class)))
    })
    CidadeDTO buscar(@ApiParam(value = "ID de uma cidade", example = "1", required = true) Long cidadeId);

    @ApiOperation("Atualiza uma cidade por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Cidade atualizada"),
            @ApiResponse(responseCode = "404", description = "Cidade não encontrada", content = @Content(schema = @Schema(implementation = Problem.class)))
    })
    CidadeDTO atualizar(@ApiParam(name = "corpo", value = "Representação de uma cidade com os novos dados")
                        CidadeInputDTO cidadeInputDTO, @ApiParam(value = "ID de uma cidade", example = "1", required = true) Long cidadeId);

    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Cidade excluída"),
            @ApiResponse(responseCode = "404", description = "Cidade não encontrada", content = @Content(schema = @Schema(implementation = Problem.class)))
    })
    @ApiOperation("Exclui uma cidade por ID")
    void deletar(@ApiParam(value = "ID de uma cidade", example = "1", required = true) Long cidadeId);
}
