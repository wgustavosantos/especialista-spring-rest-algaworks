package com.algaworks.algafood.api.openapi.controller;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.model.dto.PedidoDTO;
import com.algaworks.algafood.api.model.dto.PedidoResumoDTO;
import com.algaworks.algafood.api.model.inputDto.PedidoInputDTO;
import com.algaworks.algafood.domain.filter.PedidoFilter;
import com.algaworks.algafood.domain.repository.data.PageableTranslator;
import com.google.common.collect.ImmutableMap;
import io.swagger.annotations.*;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.Valid;

@Api(tags = "Pedidos")
public interface PedidoControllerOpenApi {

    @ApiOperation("Registra um pedido")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Pedido registrado"),
    })
    @ResponseStatus(HttpStatus.CREATED)
    PedidoDTO adicionar(@RequestBody @Valid PedidoInputDTO pedidoInputDTO);

    @ApiImplicitParams({
            @ApiImplicitParam(value = "Nomes das propriedades para filtrar na resposta, separados por vírgula"
                    , name = "campos", paramType = "query", type = "string")
    })
    Page<PedidoResumoDTO> pesquisar(PedidoFilter pedidoFilter, Pageable pageable);

    @ApiImplicitParams({
            @ApiImplicitParam(value = "Nomes das propriedades para filtrar na resposta, separados por vírgula"
                    , name = "campos", paramType = "query", type = "string")
    })
    @ApiOperation("Busca um pedido por código")
    @ApiResponses({
            @ApiResponse(responseCode = "404", description = "Pedido não encontrado", content = @Content(schema = @Schema(implementation = Problem.class)))
    })
    PedidoDTO buscar(
            @ApiParam(value = "Código de um pedido", example = "f9981ca4-5a5e-4da3-af04-933861df3e55", required = true)
            @PathVariable String codigoId);

    default Pageable truduzirPageable(Pageable apiPageable) {
        final ImmutableMap<String, String> mapFields = ImmutableMap.of(
                "codigo", "codigo",
                "restaurante.nome", "restaurante.nome",
                "nomeCliente", "cliente.nome",
                "valortotal", "valorTotal"
        );
        return PageableTranslator.translate(apiPageable, mapFields);
    }
}
