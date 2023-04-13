package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.api.assembler.EstadoAssembler;
import com.algaworks.algafood.api.assembler.EstadoInputDissasembler;
import com.algaworks.algafood.api.model.dto.EstadoDTO;
import com.algaworks.algafood.api.model.inputDto.EstadoInputDTO;
import com.algaworks.algafood.api.openapi.controller.EstadoControllerOpenApi;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.service.EstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/estados", produces = MediaType.APPLICATION_JSON_VALUE)
public class EstadoController implements EstadoControllerOpenApi {

    @Autowired
    private EstadoService estadoService;

    @Autowired
    private EstadoAssembler eAssembler;

    @Autowired
    private EstadoInputDissasembler eInputDissasembler;


    @Override
    @PostMapping
    public ResponseEntity<EstadoDTO> adicionar(@RequestBody @Valid EstadoInputDTO estadoInputDTO) {

        final Estado estado = eInputDissasembler.toDomainModel(estadoInputDTO);
        final EstadoDTO estadoDTO = eAssembler.toModel(estadoService.salvar(estado));
        return ResponseEntity.status(HttpStatus.CREATED).body(estadoDTO);
    }

    @Override
    @GetMapping
    public CollectionModel<EstadoDTO> listar() {

        return eAssembler.toCollectionModel(estadoService.listar());
    }

    @Override
    @GetMapping("/{estadoId}")
    public EstadoDTO buscar(@PathVariable Long estadoId) {
        return eAssembler.toModel(estadoService.buscar(estadoId));
    }

    @Override
    @PutMapping("/{estadoId}")
    public EstadoDTO atualizar(@RequestBody @Valid EstadoInputDTO estadoInputDTO, @PathVariable Long estadoId) {

        Estado estadoAtual = estadoService.buscar(estadoId);
        eInputDissasembler.copyProperties(estadoInputDTO, estadoAtual);

        return eAssembler.toModel(estadoService.atualizar(estadoAtual));
    }

    @Override
    @DeleteMapping("/{estadoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long estadoId) {
        estadoService.deletar(estadoId);
    }
}
