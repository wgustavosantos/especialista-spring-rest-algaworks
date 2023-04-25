package com.algaworks.algafood.api.v1.openapi.controller;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.v1.model.dto.UsuarioDTO;
import com.algaworks.algafood.api.v1.model.inputDto.UsuarioComSenhaInputDTO;
import com.algaworks.algafood.api.v1.model.inputDto.UsuarioInputSenhaDTO;
import com.algaworks.algafood.api.v1.model.inputDto.usuarioInputUpdateDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

@Api(tags = "Usuários")
public interface UsuarioControllerOpenApi {

    @ApiOperation("Lista os usuários")
    CollectionModel<UsuarioDTO> listar();

    @ApiOperation("Busca um usuário por ID")
    @ApiResponses({@ApiResponse(responseCode = "400", description = "ID do usuário inválido", content = @Content(schema = @Schema(implementation = Problem.class))), @ApiResponse(responseCode = "404", description = "Usuário não encontrado", content = @Content(schema = @Schema(implementation = Problem.class)))})
    ResponseEntity<UsuarioDTO> buscar(@ApiParam(value = "ID do usuário", example = "1", required = true) Long usuarioId);

    @ApiOperation("Cadastra um usuário")
    @ApiResponses({@ApiResponse(responseCode = "201", description = "Usuário cadastrado"),})
    ResponseEntity<UsuarioDTO> adicionar(@ApiParam(name = "corpo", value = "Representação de um novo usuário", required = true) UsuarioComSenhaInputDTO usuarioInput);

    @ApiOperation("Atualiza um usuário por ID")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Usuário atualizado"), @ApiResponse(responseCode = "404", description = "Usuário não encontrado", content = @Content(schema = @Schema(implementation = Problem.class)))})
    UsuarioDTO atualizar(@ApiParam(name = "corpo", value = "Representação de um usuário com os novos dados", required = true) usuarioInputUpdateDTO usuarioInput,
                         @ApiParam(value = "ID do usuário", example = "1", required = true) Long usuarioId);

    @ApiOperation("Atualiza a senha de um usuário")
    @ApiResponses({@ApiResponse(responseCode = "204", description = "Senha alterada com sucesso"), @ApiResponse(responseCode = "404", description = "Usuário não encontrado", content = @Content(schema = @Schema(implementation = Problem.class)))})
    ResponseEntity<Void> alterarSenha(@ApiParam(name = "corpo", value = "Representação de uma nova senha", required = true) UsuarioInputSenhaDTO senha, @ApiParam(value = "ID do usuário", example = "1", required = true) Long usuarioId);

    @ApiOperation("Deleta um usuário")
    @ApiResponses({@ApiResponse(responseCode = "400", description = "ID do usuário inválido", content = @Content(schema = @Schema(implementation = Problem.class))), @ApiResponse(responseCode = "404", description = "Usuário não encontrado", content = @Content(schema = @Schema(implementation = Problem.class)))})
    public ResponseEntity<Void> deletar(@ApiParam(value = "ID do usuário", example = "1", required = true) @PathVariable Long usuarioId);
}
