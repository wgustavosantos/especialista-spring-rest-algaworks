package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.api.assembler.EstadoAssembler;
import com.algaworks.algafood.api.assembler.EstadoInputDissasembler;
import com.algaworks.algafood.api.model.dto.EstadoDTO;
import com.algaworks.algafood.api.model.dto.inputDto.EstadoInputDTO;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.service.EstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/estados")
public class EstadoController {

    @Autowired
    private EstadoService estadoService;

    @Autowired
    private EstadoAssembler eAssembler;

    @Autowired
    private EstadoInputDissasembler eInputDissasembler;


    @PostMapping
    public ResponseEntity<EstadoDTO> adicionar(@RequestBody @Valid EstadoInputDTO estadoInputDTO) {

        final Estado estado = eInputDissasembler.toDomainModel(estadoInputDTO);
        final EstadoDTO estadoDTO = eAssembler.toDTO(estadoService.salvar(estado));
        return ResponseEntity.status(HttpStatus.CREATED).body(estadoDTO);
    }

    @GetMapping
    public List<EstadoDTO> listar() {
        return eAssembler.toListDTO(estadoService.listar());
    }

    @GetMapping("/{estadoId}")
    public EstadoDTO buscar(@PathVariable Long estadoId) {
        return eAssembler.toDTO(estadoService.buscar(estadoId));
    }

    @PutMapping("/{estadoId}")
    public EstadoDTO atualizar(@RequestBody @Valid EstadoInputDTO estadoInputDTO, @PathVariable Long estadoId) {

        Estado estadoAtual = estadoService.buscar(estadoId);
        eInputDissasembler.copyProperties(estadoInputDTO, estadoAtual);

        return eAssembler.toDTO(estadoService.atualizar(estadoAtual));
    }

    @DeleteMapping("/{estadoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long estadoId) {
        estadoService.deletar(estadoId);
    }
}
