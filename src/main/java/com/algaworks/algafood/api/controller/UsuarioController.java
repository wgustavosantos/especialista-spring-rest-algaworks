package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.api.assembler.UsuarioAssembler;
import com.algaworks.algafood.api.model.dto.UsuarioDTO;
import com.algaworks.algafood.api.model.dto.inputDto.UsuarioInputDTO;
import com.algaworks.algafood.api.model.dto.inputDto.usuarioInputSenhaDTO;
import com.algaworks.algafood.api.model.dto.inputDto.usuarioInputUpdateDTO;
import com.algaworks.algafood.domain.model.Usuario;
import com.algaworks.algafood.domain.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioAssembler aAssembler;

    @PostMapping
    public ResponseEntity<UsuarioDTO> adicionar(@RequestBody @Valid UsuarioInputDTO usuarioInputDTO) {

        final Usuario usuario = aAssembler.toDomainModel(usuarioInputDTO);
        final UsuarioDTO usuarioDTO = aAssembler.toDTO(usuarioService.salvar(usuario));
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioDTO);
    }

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> listar() {
        final List<UsuarioDTO> usuariosDTO = aAssembler.toListDTO(usuarioService.listar());

        return ResponseEntity.ok(usuariosDTO);
    }

    @GetMapping("/{usuarioId}")
    public ResponseEntity<UsuarioDTO> buscar(@PathVariable Long usuarioId) {
        final UsuarioDTO usuarioDTO = aAssembler.toDTO(usuarioService.buscar(usuarioId));

        return ResponseEntity.ok(usuarioDTO);
    }

    @PutMapping("/{usuarioId}")
    public UsuarioDTO atualizar(@RequestBody @Valid usuarioInputUpdateDTO usuarioUpdateDTO, @PathVariable Long usuarioId) {

        Usuario usuarioAtual = usuarioService.buscar(usuarioId);
        aAssembler.copyProperties(usuarioUpdateDTO, usuarioAtual);

        return aAssembler.toDTO(usuarioService.atualizar(usuarioAtual));
    }

    @DeleteMapping("/{usuarioId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deletar(@PathVariable Long usuarioId) {
        usuarioService.deletar(usuarioId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{usuarioId}/senha")
    public ResponseEntity<Void> alterarSenha(@RequestBody @Valid usuarioInputSenhaDTO senhaDTO, @PathVariable Long usuarioId) {

        usuarioService.alterarSenha(usuarioId, senhaDTO);
        return ResponseEntity.noContent().build();
    }
}