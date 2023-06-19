package com.algaworks.algafood.api.v1.controller;

import com.algaworks.algafood.api.v1.assembler.GrupoAssembler;
import com.algaworks.algafood.api.v1.assembler.PermissaoAssembler;
import com.algaworks.algafood.api.v1.model.dto.GrupoDTO;
import com.algaworks.algafood.api.v1.model.inputDto.GrupoInputDTO;
import com.algaworks.algafood.api.v1.openapi.controller.GrupoControllerOpenApi;
import com.algaworks.algafood.core.security.CheckSecurity;
import com.algaworks.algafood.domain.model.Grupo;
import com.algaworks.algafood.domain.service.GrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/v1/grupos", produces = MediaType.APPLICATION_JSON_VALUE)
public class GrupoController implements GrupoControllerOpenApi {

    @Autowired
    private GrupoService grupoService;

    @Autowired
    private GrupoAssembler gAssembler;

    @Autowired
    private PermissaoAssembler pAssembler;

    @CheckSecurity.UsuariosGruposPermissoes.PodeEditar
    @Override
    @PostMapping
    public ResponseEntity<GrupoDTO> adicionar(@RequestBody @Valid GrupoInputDTO grupoInputDTO){
        final Grupo grupo = gAssembler.toDomainModel(grupoInputDTO);
        final GrupoDTO grupoDTO = gAssembler.toModel(grupoService.salvar(grupo));

        return ResponseEntity.status(HttpStatus.CREATED).body(grupoDTO);
    }

    @CheckSecurity.UsuariosGruposPermissoes.PodeConsultar
    @Override
    @GetMapping
    public CollectionModel<GrupoDTO> listar(){
        return gAssembler.toCollectionModel(grupoService.listar());
    }

    @CheckSecurity.UsuariosGruposPermissoes.PodeConsultar
    @Override
    @GetMapping("/{grupoId}")
    public ResponseEntity<GrupoDTO> buscar(@PathVariable Long grupoId){
        final GrupoDTO grupoDTO = gAssembler.toModel(grupoService.buscar(grupoId));
        return ResponseEntity.ok(grupoDTO);
    }

    @CheckSecurity.UsuariosGruposPermissoes.PodeEditar
    @Override
    @PutMapping("/{grupoId}")
    public ResponseEntity<GrupoDTO> atualizar(@PathVariable Long grupoId, @RequestBody GrupoInputDTO grupoInputDTO){

        final Grupo grupoAtual = grupoService.buscar(grupoId);
        gAssembler.copyProperties(grupoInputDTO, grupoAtual);
        final GrupoDTO grupoDTO = gAssembler.toModel(grupoService.atualizar(grupoAtual));

        return ResponseEntity.ok(grupoDTO);
    }

    @CheckSecurity.UsuariosGruposPermissoes.PodeEditar
    @Override
    @DeleteMapping("{grupoId}")
    public ResponseEntity<Void> deletar(@PathVariable Long grupoId){
        grupoService.deletar(grupoId);
        return ResponseEntity.noContent().build();
    }
}
