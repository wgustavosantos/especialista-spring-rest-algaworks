package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.api.AlgaLinks;
import com.algaworks.algafood.api.assembler.PermissaoAssembler;
import com.algaworks.algafood.api.model.dto.PermissaoDTO;
import com.algaworks.algafood.api.openapi.controller.GrupoPermissaoControllerOpenApi;
import com.algaworks.algafood.domain.model.Grupo;
import com.algaworks.algafood.domain.service.GrupoService;
import com.algaworks.algafood.domain.service.PermissaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("grupos/{grupoId}/permissoes")
public class GrupoPermissaoController implements GrupoPermissaoControllerOpenApi {

    @Autowired
    private GrupoService grupoService;

    @Autowired
    private PermissaoService permissaoService;

    @Autowired
    private PermissaoAssembler pAssembler;

    @Autowired
    private AlgaLinks algaLinks;

    @Override
    @GetMapping
    public CollectionModel<PermissaoDTO> listarPermissoes(@PathVariable Long grupoId){
        final Grupo grupo = grupoService.buscar(grupoId);
        final CollectionModel<PermissaoDTO> permissoesDTO = pAssembler
                .toCollectionModel(grupo.getPermissoes())
                .removeLinks()
                .add(algaLinks.linkToGrupoPermissoes(grupoId))
                .add(algaLinks.linkToGrupoPermissaoAssociacao(grupoId, "associar"));

        permissoesDTO.getContent().forEach(permissaoModel -> {
            permissaoModel.add(algaLinks.linkToGrupoPermissaoDesassociacao(
                    grupoId, permissaoModel.getId(), "desassociar"));
        });

        return permissoesDTO;
    }

    @Override
    @PutMapping("/{permissaoId}")
    public ResponseEntity<Void> associarPermissao(@PathVariable Long grupoId,
                                                  @PathVariable Long permissaoId){
        grupoService.adicionarPermissao(grupoId, permissaoId);

        return ResponseEntity.noContent().build();
    }

    @Override
    @DeleteMapping("/{permissaoId}")
    public ResponseEntity<Void> desassociarPermissao(@PathVariable Long grupoId,
                                                     @PathVariable Long permissaoId){
        grupoService.removerPermissao(grupoId, permissaoId);

        return ResponseEntity.noContent().build();
    }
}
