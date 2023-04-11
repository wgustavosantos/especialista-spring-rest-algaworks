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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

public interface CidadeControllerOpenApi {
    @ApiOperation("Cadastra uma cidade")
    @PostMapping
    @ApiResponse(responseCode = "201", description = "Cidade cadastrada")
    ResponseEntity<CidadeDTO> adicionar(@ApiParam(name = "corpo", value = "Representação de uma nova cidade")
                                        @RequestBody @Valid CidadeInputDTO cidadeInputDTO);

    @ApiOperation("Lista as cidades")
    @GetMapping
    ResponseEntity<List<CidadeDTO>> listar();

    @ApiOperation("Busca uma cidade por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "ID da cidade inválido", content = @Content(schema = @Schema(implementation = Problem.class))),
            @ApiResponse(responseCode = "404", description = "Cidade não encontrada", content = @Content(schema = @Schema(implementation = Problem.class)))
    })
    @GetMapping("/{cidadeId}")
    CidadeDTO buscar(@ApiParam("ID de uma cidade") @PathVariable Long cidadeId);

    @ApiOperation("Atualiza uma cidade por ID")
    @PutMapping("/{cidadeId}")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Cidade atualizada"),
            @ApiResponse(responseCode = "404", description = "Cidade não encontrada", content = @Content(schema = @Schema(implementation = Problem.class)))
    })
    CidadeDTO atualizar(@RequestBody @Valid CidadeInputDTO cidadeInputDTO, @ApiParam("ID de uma cidade") @PathVariable Long cidadeId);

    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Cidade excluída"),
            @ApiResponse(responseCode = "404", description = "Cidade não encontrada", content = @Content(schema = @Schema(implementation = Problem.class)))
    })
    @ApiOperation("Exclui uma cidade por ID")
    @DeleteMapping("/{cidadeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deletar(@ApiParam("ID de uma cidade") @PathVariable Long cidadeId);
}
