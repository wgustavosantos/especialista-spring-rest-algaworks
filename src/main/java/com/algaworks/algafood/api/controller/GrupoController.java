package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.api.assembler.GrupoAssembler;
import com.algaworks.algafood.api.assembler.PermissaoAssembler;
import com.algaworks.algafood.api.model.dto.GrupoDTO;
import com.algaworks.algafood.api.model.inputDto.GrupoInputDTO;
import com.algaworks.algafood.domain.model.Grupo;
import com.algaworks.algafood.domain.service.GrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/grupos")
public class GrupoController implements com.algaworks.algafood.api.controller.openapi.GrupoControllerOpenApi {

    @Autowired
    private GrupoService grupoService;

    @Autowired
    private GrupoAssembler gAssembler;

    @Autowired
    private PermissaoAssembler pAssembler;

    @Override
    @PostMapping
    public ResponseEntity<GrupoDTO> adicionar(@RequestBody @Valid GrupoInputDTO grupoInputDTO){
        final Grupo grupo = gAssembler.toDomainModel(grupoInputDTO);
        final GrupoDTO grupoDTO = gAssembler.toDTO(grupoService.salvar(grupo));

        return ResponseEntity.status(HttpStatus.CREATED).body(grupoDTO);
    }

    @Override
    @GetMapping
    public ResponseEntity<List<GrupoDTO>> listar(){
        final List<GrupoDTO> gruposDTO = gAssembler.toListDTO(grupoService.listar());
        return ResponseEntity.ok(gruposDTO);
    }

    @Override
    @GetMapping("/{grupoId}")
    public ResponseEntity<GrupoDTO> buscar(@PathVariable Long grupoId){
        final GrupoDTO grupoDTO = gAssembler.toDTO(grupoService.buscar(grupoId));
        return ResponseEntity.ok(grupoDTO);
    }

    @Override
    @PutMapping("/{grupoId}")
    public ResponseEntity<GrupoDTO> atualizar(@PathVariable Long grupoId, @RequestBody GrupoInputDTO grupoInputDTO){

        final Grupo grupoAtual = grupoService.buscar(grupoId);
        gAssembler.copyProperties(grupoInputDTO, grupoAtual);
        final GrupoDTO grupoDTO = gAssembler.toDTO(grupoService.atualizar(grupoAtual));

        return ResponseEntity.ok(grupoDTO);
    }

    @Override
    @DeleteMapping("{grupoId}")
    public ResponseEntity<Void> deletar(@PathVariable Long grupoId){
        grupoService.deletar(grupoId);
        return ResponseEntity.noContent().build();
    }
}
