package com.algaworks.algafood.api.openapi.controller;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.model.dto.FormaPagamentoDTO;
import com.algaworks.algafood.api.model.inputDto.FormaPagamentoInputDTO;
import com.algaworks.algafood.api.openapi.model.FormasPagamentoModelOpenApi;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.ServletWebRequest;

@Api(tags = "Formas de pagamento")
public interface FormaPagamentoControllerOpenApi {
    @ApiOperation("Cadastra uma forma de pagamento")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Forma de pagamento cadastrada", content = @Content(schema = @Schema(implementation = Problem.class))),
    })
    ResponseEntity<FormaPagamentoDTO> adicionar(
            @ApiParam(name = "corpo", value = "Representação de uma nova forma de pagamento", required = true)
             FormaPagamentoInputDTO formaPagamentoInputDTO);
    @ApiOperation("Busca uma forma de pagamento por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "400", description = "ID da forma de pagamento inválido", content = @Content(schema = @Schema(implementation = Problem.class))),
            @ApiResponse(responseCode = "404", description = "Forma de pagamento não encontrada", content = @Content(schema = @Schema(implementation = Problem.class)))
    })
    ResponseEntity<FormaPagamentoDTO> buscar(
            @ApiParam(value = "ID de uma forma de pagamento", example = "1", required = true) Long formaPagamentoId,
            ServletWebRequest request);

    @ApiOperation("Atualiza uma cidade por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Forma de pagamento atualizada"),
            @ApiResponse(responseCode = "404", description = "Forma de pagamento não encontrada", content = @Content(schema = @Schema(implementation = Problem.class)))
    })
    ResponseEntity<FormaPagamentoDTO> atualizar(
            @ApiParam(value = "ID de uma forma de pagamento", example = "1", required = true)
            Long formaPagamentoId,

            @ApiParam(name = "corpo", value = "Representação de uma forma de pagamento com os novos dados", required = true)
            FormaPagamentoInputDTO formaPagamentoInputDTO);
    @ApiOperation(value = "Lista as formas de pagamento")
    @io.swagger.annotations.ApiResponses(value = {
            @io.swagger.annotations.ApiResponse(code = 200, message = "OK", response = FormasPagamentoModelOpenApi.class)
    })
    ResponseEntity<CollectionModel<FormaPagamentoDTO>> listar(ServletWebRequest request);

    @ApiOperation("Exclui uma forma de pagamento por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Forma de pagamento excluída"),
            @ApiResponse(responseCode = "404", description = "Forma de pagamento não encontrada", content = @Content(schema = @Schema(implementation = Problem.class)))
    })
    ResponseEntity<Void> deletar(@ApiParam(value = "ID de uma forma de pagamento", example = "1", required = true) Long formaPagamentoId);
}
