package com.algaworks.algafood.api.v1.controller;

import com.algaworks.algafood.api.v1.AlgaLinks;
import com.algaworks.algafood.api.v1.assembler.PermissaoAssembler;
import com.algaworks.algafood.api.v1.model.dto.PermissaoDTO;
import com.algaworks.algafood.api.v1.openapi.controller.GrupoPermissaoControllerOpenApi;
import com.algaworks.algafood.core.security.AlgaSecurity;
import com.algaworks.algafood.core.security.CheckSecurity;
import com.algaworks.algafood.domain.model.Grupo;
import com.algaworks.algafood.domain.service.GrupoService;
import com.algaworks.algafood.domain.service.PermissaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1grupos/{grupoId}/permissoes")
public class GrupoPermissaoController implements GrupoPermissaoControllerOpenApi {

    @Autowired
    private GrupoService grupoService;

    @Autowired
    private PermissaoService permissaoService;

    @Autowired
    private PermissaoAssembler pAssembler;

    @Autowired
    private AlgaLinks algaLinks;

    @Autowired
    private AlgaSecurity algaSecurity;

    @CheckSecurity.UsuariosGruposPermissoes.PodeConsultar
    @Override
    @GetMapping
    public CollectionModel<PermissaoDTO> listarPermissoes(@PathVariable Long grupoId){
        final Grupo grupo = grupoService.buscar(grupoId);


        CollectionModel<PermissaoDTO> permissaoDTOS
                = pAssembler.toCollectionModel(grupo.getPermissoes())
                .removeLinks();

        permissaoDTOS.add(algaLinks.linkToGrupoPermissoes(grupoId));

        if (algaSecurity.podeEditarUsuariosGruposPermissoes()) {
            permissaoDTOS.add(algaLinks.linkToGrupoPermissaoAssociacao(grupoId, "associar"));

            permissaoDTOS.getContent().forEach(permissaoModel -> {
                permissaoModel.add(algaLinks.linkToGrupoPermissaoDesassociacao(
                        grupoId, permissaoModel.getId(), "desassociar"));
            });
        }

        return permissaoDTOS;
    }

    @CheckSecurity.UsuariosGruposPermissoes.PodeEditar
    @Override
    @PutMapping("/{permissaoId}")
    public ResponseEntity<Void> associarPermissao(@PathVariable Long grupoId,
                                                  @PathVariable Long permissaoId){
        grupoService.adicionarPermissao(grupoId, permissaoId);

        return ResponseEntity.noContent().build();
    }

    @CheckSecurity.UsuariosGruposPermissoes.PodeEditar
    @Override
    @DeleteMapping("/{permissaoId}")
    public ResponseEntity<Void> desassociarPermissao(@PathVariable Long grupoId,
                                                     @PathVariable Long permissaoId){
        grupoService.removerPermissao(grupoId, permissaoId);

        return ResponseEntity.noContent().build();
    }
}
