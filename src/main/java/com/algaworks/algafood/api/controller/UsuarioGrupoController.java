package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.api.AlgaLinks;
import com.algaworks.algafood.api.assembler.GrupoAssembler;
import com.algaworks.algafood.api.model.dto.GrupoDTO;
import com.algaworks.algafood.api.openapi.controller.UsuarioGrupoControllerOpenApi;
import com.algaworks.algafood.domain.model.Usuario;
import com.algaworks.algafood.domain.service.GrupoService;
import com.algaworks.algafood.domain.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/usuarios/{usuarioId}/grupos",
        produces = MediaType.APPLICATION_JSON_VALUE)
public class UsuarioGrupoController implements UsuarioGrupoControllerOpenApi {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private GrupoService grupoService;

    @Autowired
    GrupoAssembler gAssembler;

    @Autowired
    private AlgaLinks algaLinks;

    @Override
    @GetMapping
    public CollectionModel<GrupoDTO> listar(@PathVariable Long usuarioId){
        final Usuario usuario = usuarioService.buscar(usuarioId);

        CollectionModel<GrupoDTO> gruposDTO = gAssembler.toCollectionModel(usuario.getGrupos())
                .removeLinks()
                .add(algaLinks.linkToUsuarioGrupoAssociacao(usuarioId, "associar"));

        gruposDTO.getContent().forEach(grupoDTO -> {
            grupoDTO.add(algaLinks.linkToUsuarioGrupoDesassociacao(
                    usuarioId, grupoDTO.getId(), "desassociar"));
        });

        return gruposDTO;
    }

    @Override
    @PutMapping("{grupoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> adicionarGrupo(@PathVariable Long usuarioId, @PathVariable Long grupoId){
        usuarioService.adicionarGrupo(usuarioId,grupoId);

        return ResponseEntity.noContent().build();
    }

    @Override
    @DeleteMapping("{grupoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> removerGrupo(@PathVariable Long usuarioId, @PathVariable Long grupoId){
        usuarioService.removerGrupo(usuarioId,grupoId);

        return ResponseEntity.noContent().build();
    }
}
