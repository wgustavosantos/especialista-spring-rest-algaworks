package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.api.assembler.PermissaoAssembler;
import com.algaworks.algafood.api.model.dto.PermissaoDTO;
import com.algaworks.algafood.api.openapi.controller.GrupoPermissaoControllerOpenApi;
import com.algaworks.algafood.domain.model.Grupo;
import com.algaworks.algafood.domain.service.GrupoService;
import com.algaworks.algafood.domain.service.PermissaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("grupos/{grupoId}/permissoes")
public class GrupoPermissaoController implements GrupoPermissaoControllerOpenApi {

    @Autowired
    private GrupoService grupoService;

    @Autowired
    private PermissaoService permissaoService;

    @Autowired
    private PermissaoAssembler pAssembler;

    @Override
    @GetMapping
    public List<PermissaoDTO> listarPermissoes(@PathVariable Long grupoId){
        final Grupo grupo = grupoService.buscar(grupoId);
        return pAssembler.toListDTO(grupo.getPermissoes());
    }

    @Override
    @PutMapping("/{permissaoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void associarPermissao(@PathVariable Long grupoId,
                                  @PathVariable Long permissaoId){
        grupoService.adicionarPermissao(grupoId, permissaoId);
    }

    @Override
    @DeleteMapping("/{permissaoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void desassociarPermissao(@PathVariable Long grupoId,
                                     @PathVariable Long permissaoId){
        grupoService.removerPermissao(grupoId, permissaoId);
    }
}
