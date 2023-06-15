package com.algaworks.algafood.api.v1.openapi.controller;

import com.algaworks.algafood.api.v1.model.dto.FotoProdutoDTO;
import com.algaworks.algafood.api.v1.model.inputDto.FotoProdutoInput;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;

import java.io.IOException;

@SecurityRequirement(name = "security_auth")
public interface RestauranteProdutoFotoControllerOpenApi {

    @Operation(summary = "Atualiza a foto do produto de um restaurante")
    FotoProdutoDTO atualizarFoto(@Parameter(description = "Id do restaurante", example = "1", required = true) Long restauranteId,
                                   @Parameter(description = "Id do produto", example = "2", required = true) Long produtoId,
                                   @RequestBody(required = true) FotoProdutoInput fotoProdutoInput) throws IOException;


    void deletarFotoProduto(Long restauranteId, Long produtoId);

    @Operation(hidden = true)
    ResponseEntity<?> servirFoto(Long restauranteId, Long produtoId, String acceptHeader) throws HttpMediaTypeNotAcceptableException;
    
    @Operation(summary = "Busca a foto do produto de um restaurante", responses = {
            @ApiResponse(responseCode = "200", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = FotoProdutoDTO.class)),
                    @Content(mediaType = "image/jpeg", schema = @Schema(type = "string", format = "binary")),
                    @Content(mediaType = "image/png", schema = @Schema(type = "string", format = "binary"))
            })
    })
    FotoProdutoDTO buscar(Long restauranteId, Long produtoId);
}