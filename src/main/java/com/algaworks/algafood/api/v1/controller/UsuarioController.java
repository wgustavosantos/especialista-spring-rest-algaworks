package com.algaworks.algafood.api.v1.controller;

import com.algaworks.algafood.api.v1.assembler.UsuarioAssembler;
import com.algaworks.algafood.api.v1.model.dto.UsuarioDTO;
import com.algaworks.algafood.api.v1.model.inputDto.UsuarioComSenhaInputDTO;
import com.algaworks.algafood.api.v1.model.inputDto.UsuarioInputSenhaDTO;
import com.algaworks.algafood.api.v1.model.inputDto.usuarioInputUpdateDTO;
import com.algaworks.algafood.api.v1.openapi.controller.UsuarioControllerOpenApi;
import com.algaworks.algafood.core.security.CheckSecurity;
import com.algaworks.algafood.domain.model.Usuario;
import com.algaworks.algafood.domain.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/v1/usuarios", produces = MediaType.APPLICATION_JSON_VALUE)
public class UsuarioController implements UsuarioControllerOpenApi {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioAssembler aAssembler;

    @CheckSecurity.UsuariosGruposPermissoes.PodeEditar
    @Override
    @PostMapping
    public ResponseEntity<UsuarioDTO> adicionar(@RequestBody @Valid UsuarioComSenhaInputDTO usuarioInputDTO) {

        final Usuario usuario = aAssembler.toDomainModel(usuarioInputDTO);
        final UsuarioDTO usuarioDTO = aAssembler.toModel(usuarioService.salvar(usuario));
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioDTO);
    }

    @CheckSecurity.UsuariosGruposPermissoes.PodeConsultar
    @Override
    @GetMapping
    public CollectionModel<UsuarioDTO> listar() {
        final CollectionModel<UsuarioDTO> usuariosDTO = aAssembler.toCollectionModel(usuarioService.listar());

        return usuariosDTO;
    }

    @CheckSecurity.UsuariosGruposPermissoes.PodeConsultar
    @Override
    @GetMapping("/{usuarioId}")
    public ResponseEntity<UsuarioDTO> buscar(@PathVariable Long usuarioId) {
        final UsuarioDTO usuarioDTO = aAssembler.toModel(usuarioService.buscar(usuarioId));

        return ResponseEntity.ok(usuarioDTO);
    }

    @CheckSecurity.UsuariosGruposPermissoes.PodeAlterarUsuario
    @Override
    @PutMapping("/{usuarioId}")
    public UsuarioDTO atualizar(@RequestBody @Valid usuarioInputUpdateDTO usuarioUpdateDTO, @PathVariable Long usuarioId) {

        Usuario usuarioAtual = usuarioService.buscar(usuarioId);
        aAssembler.copyProperties(usuarioUpdateDTO, usuarioAtual);

        return aAssembler.toModel(usuarioService.atualizar(usuarioAtual));
    }

    @CheckSecurity.UsuariosGruposPermissoes.PodeEditar
    @Override
    @DeleteMapping("/{usuarioId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deletar(@PathVariable Long usuarioId) {
        usuarioService.deletar(usuarioId);
        return ResponseEntity.noContent().build();
    }

    @CheckSecurity.UsuariosGruposPermissoes.PodeAlterarPropriaSenha
    @Override
    @PutMapping("/{usuarioId}/senha")
    public ResponseEntity<Void> alterarSenha(@RequestBody @Valid UsuarioInputSenhaDTO senhaDTO, @PathVariable Long usuarioId) {

        usuarioService.alterarSenha(usuarioId, senhaDTO);
        return ResponseEntity.noContent().build();
    }
}