package com.algaworks.algafood.api.openapi.controller;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.model.dto.RestauranteDTO;
import com.algaworks.algafood.api.model.inputDto.RestauranteInputDTO;
import com.algaworks.algafood.api.openapi.model.RestauranteBasicoModelOpenApi;
import io.swagger.annotations.*;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Set;

@Api(tags = "Restaurantes")
public interface RestauranteControllerOpenApi {

    @ApiOperation("Cadastra um restaurante")
    @ApiResponses({@ApiResponse(responseCode = "201", description = "Restaurante cadastrado"),})
    RestauranteDTO adicionar(@ApiParam(name = "corpo", value = "Representação de um novo restaurante", required = true) RestauranteInputDTO restauranteInput);

    @ApiOperation(value = "Lista restaurantes", response = RestauranteBasicoModelOpenApi.class)
    @ApiImplicitParams({@ApiImplicitParam(value = "Nome da projeção de pedidos", allowableValues = "apenas-nome", name = "projecao", paramType = "query", type = "string")})
//    @JsonView(RestauranteView.Resumo.class)
    CollectionModel<RestauranteDTO> listar();

    @ApiOperation(value = "Lista restaurantes", hidden = true)
    CollectionModel<RestauranteDTO> listarApenasNome();

    @ApiOperation("Busca um restaurante por ID")
    @ApiResponses({@ApiResponse(responseCode = "400", description = "ID do restaurante inválido", content = @Content(schema = @Schema(implementation = Problem.class))), @ApiResponse(responseCode = "404", description = "Restaurante não encontrado", content = @Content(schema = @Schema(implementation = Problem.class)))})
    RestauranteDTO buscar(@ApiParam(value = "ID de um restaurante", example = "1", required = true) Long restauranteId);

    @ApiOperation("Atualiza um restaurante por ID")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Restaurante atualizado"), @ApiResponse(responseCode = "404", description = "Restaurante não encontrado", content = @Content(schema = @Schema(implementation = Problem.class)))})
    RestauranteDTO atualizar(@ApiParam(name = "corpo", value = "Representação de um restaurante com os novos dados", required = true) RestauranteInputDTO restauranteInput, @ApiParam(value = "ID de um restaurante", example = "1", required = true) Long restauranteId);

    @ApiOperation("Deleta um restaurante por ID")
    @ApiResponses({@ApiResponse(responseCode = "400", description = "ID do restaurante inválido", content = @Content(schema = @Schema(implementation = Problem.class))), @ApiResponse(responseCode = "404", description = "Restaurante não encontrado", content = @Content(schema = @Schema(implementation = Problem.class)))})

    void deletar(@ApiParam(value = "ID de um restaurante", example = "1", required = true) Long restauranteId);


    @ApiOperation("Ativa um restaurante por ID")
    @ApiResponses({@ApiResponse(responseCode = "204", description = "Restaurante ativado com sucesso"), @ApiResponse(responseCode = "404", description = "Restaurante não encontrado", content = @Content(schema = @Schema(implementation = Problem.class)))})
    ResponseEntity<Void> ativar(@ApiParam(value = "ID de um restaurante", example = "1", required = true) Long restauranteId);

    @ApiOperation("Inativa um restaurante por ID")
    @ApiResponses({@ApiResponse(responseCode = "204", description = "Restaurante inativado com sucesso"), @ApiResponse(responseCode = "404", description = "Restaurante não encontrado", content = @Content(schema = @Schema(implementation = Problem.class)))})
    ResponseEntity<Void> inativar(@ApiParam(value = "ID de um restaurante", example = "1", required = true) Long restauranteId);

    @ApiOperation("Abre um restaurante por ID")
    @ApiResponses({@ApiResponse(responseCode = "204", description = "Restaurante aberto com sucesso"), @ApiResponse(responseCode = "404", description = "Restaurante não encontrado", content = @Content(schema = @Schema(implementation = Problem.class)))})
    ResponseEntity<Void> abrir(@ApiParam(value = "ID de um restaurante", example = "1", required = true) Long restauranteId);

    @ApiOperation("Fecha um restaurante por ID")
    @ApiResponses({@ApiResponse(responseCode = "204", description = "Restaurante fechado com sucesso"), @ApiResponse(responseCode = "404", description = "Restaurante não encontrado", content = @Content(schema = @Schema(implementation = Problem.class)))})
    ResponseEntity<Void> fechar(@ApiParam(value = "ID de um restaurante", example = "1", required = true) Long restauranteId);

    @ApiOperation("Ativa múltiplos restaurantes")
    @ApiResponses({@ApiResponse(responseCode = "204", description = "Restaurantes ativados com sucesso")})
    void ativarRestaurantes(@ApiParam(name = "corpo", value = "IDs de restaurantes", required = true) @RequestBody Set<Long> restauranteIds);

    @ApiOperation("Inativa múltiplos restaurantes")
    @ApiResponses({@ApiResponse(responseCode = "204", description = "Restaurantes ativados com sucesso")})
    @DeleteMapping("/inativacoes")
    void inativarRestaurantes(@ApiParam(name = "corpo", value = "IDs de restaurantes", required = true) @RequestBody Set<Long> restauranteIds);

    @PatchMapping("/{id}")
    RestauranteDTO atualizarParcial(Long id, @RequestBody Map<String, Object> campos, HttpServletRequest servletRequest);
}
