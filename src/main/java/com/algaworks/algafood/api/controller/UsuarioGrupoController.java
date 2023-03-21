package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.api.assembler.GrupoAssembler;
import com.algaworks.algafood.api.model.dto.GrupoDTO;
import com.algaworks.algafood.domain.model.Usuario;
import com.algaworks.algafood.domain.service.GrupoService;
import com.algaworks.algafood.domain.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("usuarios/{usuarioId}/grupos")
public class UsuarioGrupoController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private GrupoService grupoService;

    @Autowired
    GrupoAssembler gAssembler;

    @GetMapping
    public List<GrupoDTO> listar(@PathVariable Long usuarioId){
        final Usuario usuario = usuarioService.buscar(usuarioId);

        return gAssembler.toListDTO(usuario.getGrupos());
    }

    @PutMapping("{grupoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void adicionarGrupo(@PathVariable Long usuarioId, @PathVariable Long grupoId){
        usuarioService.adicionarGrupo(usuarioId,grupoId);
    }

    @DeleteMapping("{grupoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removerGrupo(@PathVariable Long usuarioId, @PathVariable Long grupoId){
        usuarioService.removerGrupo(usuarioId,grupoId);
    }
}
