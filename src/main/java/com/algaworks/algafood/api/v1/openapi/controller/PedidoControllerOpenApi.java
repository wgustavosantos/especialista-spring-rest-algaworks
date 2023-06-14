package com.algaworks.algafood.api.v1.openapi.controller;

import com.algaworks.algafood.api.v1.model.dto.PedidoDTO;
import com.algaworks.algafood.api.v1.model.dto.PedidoResumoDTO;
import com.algaworks.algafood.api.v1.model.inputDto.PedidoInputDTO;
import com.algaworks.algafood.core.springdoc.PageableParameter;
import com.algaworks.algafood.domain.filter.PedidoFilter;
import com.algaworks.algafood.domain.repository.data.PageableTranslator;
import com.google.common.collect.ImmutableMap;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.Valid;

@SecurityRequirement(name = "security_auth")
public interface PedidoControllerOpenApi {

    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Registra um pedido", responses = {
            @ApiResponse(responseCode = "201", description = "Pedido registrado"),
    })
    PedidoDTO adicionar(@RequestBody(description = "Representação de um novo pedido", required = true) PedidoInputDTO pedidoInputDTO);

    @Operation(
            summary = "Pesquisa os pedidos",
            parameters = {
                    @Parameter(in = ParameterIn.QUERY, name = "clienteId",
                            description = "ID do cliente para filtro da pesquisa",
                            example = "1", schema = @Schema(type = "integer")),
                    @Parameter(in = ParameterIn.QUERY, name = "restauranteId",
                            description = "ID do restaurante para filtro da pesquisa",
                            example = "1", schema = @Schema(type = "integer")),
                    @Parameter(in = ParameterIn.QUERY, name = "dataCriacaoInicio",
                            description = "Data/hora de criação inicial para filtro da pesquisa",
                            example = "2019-12-01T00:00:00Z", schema = @Schema(type = "string", format = "date-time")),
                    @Parameter(in = ParameterIn.QUERY, name = "dataCriacaoFim",
                            description = "Data/hora de criação final para filtro da pesquisa",
                            example = "2019-12-02T23:59:59Z", schema = @Schema(type = "string", format = "date-time"))
            }
    )
    @PageableParameter
    PagedModel<PedidoResumoDTO> pesquisar(@Parameter(hidden = true) PedidoFilter pedidoFilter,@Parameter(hidden = true) Pageable pageable);

    @Operation(summary = "Busca um pedido por código", responses = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "404", description = "Pedido não encontrado", content = {
                    @Content(schema = @Schema(ref = "Problema"))}),
    })
    PedidoDTO buscar(@Parameter(description = "Código de um pedido", example = "04813f77-79b5-11ec-9a17-0242ac1b0002",
            required = true) String codigoId);

    default Pageable truduzirPageable(Pageable apiPageable) {
        final ImmutableMap<String, String> mapFields = ImmutableMap.of("codigo", "codigo", "restaurante.nome", "restaurante.nome", "nomeCliente", "cliente.nome", "valortotal", "valorTotal");
        return PageableTranslator.translate(apiPageable, mapFields);
    }
}
