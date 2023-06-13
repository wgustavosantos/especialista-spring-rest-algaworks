package com.algaworks.algafood.api.v1.openapi.controller;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.v1.model.dto.UsuarioDTO;
import com.algaworks.algafood.api.v1.model.inputDto.UsuarioComSenhaInputDTO;
import com.algaworks.algafood.api.v1.model.inputDto.UsuarioInputSenhaDTO;
import com.algaworks.algafood.api.v1.model.inputDto.usuarioInputUpdateDTO;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

public interface UsuarioControllerOpenApi {

    CollectionModel<UsuarioDTO> listar();

    ResponseEntity<UsuarioDTO> buscar(Long usuarioId);

    ResponseEntity<UsuarioDTO> adicionar(UsuarioComSenhaInputDTO usuarioInput);

    UsuarioDTO atualizar(usuarioInputUpdateDTO usuarioInput, Long usuarioId);

    ResponseEntity<Void> alterarSenha(UsuarioInputSenhaDTO senha, Long usuarioId);

    public ResponseEntity<Void> deletar(@PathVariable Long usuarioId);
}
