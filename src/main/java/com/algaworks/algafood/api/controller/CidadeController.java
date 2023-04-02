package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.api.assembler.CidadeAssembler;
import com.algaworks.algafood.api.assembler.CidadeInputDisassembler;
import com.algaworks.algafood.api.model.dto.CidadeDTO;
import com.algaworks.algafood.api.model.inputDto.CidadeInputDTO;
import com.algaworks.algafood.domain.exception.EstadoNaoEncontradoException;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.service.CidadeService;
import com.algaworks.algafood.domain.service.EstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/cidades")
public class CidadeController {

    @Autowired
    private CidadeService cidadeService;

    @Autowired
    private EstadoService estadoService;

    @Autowired
    private CidadeAssembler cAssembler;

    @Autowired
    private CidadeInputDisassembler cidadeInputDisassembler;

    @PostMapping
    public ResponseEntity<CidadeDTO> adicionar(@RequestBody @Valid CidadeInputDTO cidadeInputDTO) {
        try {
            final Cidade cidade = cidadeInputDisassembler.DTOtoDomainModel(cidadeInputDTO);
            Cidade c = cidadeService.salvar(cidade);
            return ResponseEntity.status(HttpStatus.CREATED).body(cAssembler.toDTO(c));
        } catch (EstadoNaoEncontradoException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    @GetMapping
    public ResponseEntity<List<CidadeDTO>> listar() {
        final List<Cidade> cidades = cidadeService.listar();
        return ResponseEntity.ok(cAssembler.toListDTO(cidades));
    }

    @GetMapping("/{cidadeId}")
    public CidadeDTO buscar(@PathVariable Long cidadeId) {
        return cAssembler.toDTO(cidadeService.buscar(cidadeId));
    }

    @PutMapping("/{id}")
    public CidadeDTO atualizar(@RequestBody @Valid CidadeInputDTO cidadeInputDTO, @PathVariable Long id) {
        Cidade cidadeAtual;
        try {
            Estado estadoAtual = estadoService.buscar(cidadeInputDTO.getEstado().getId());
            cidadeAtual = cidadeService.buscar(id);
            cidadeInputDisassembler.copyProperties(cidadeInputDTO, cidadeAtual);
            cidadeAtual.setEstado(estadoAtual);
        } catch (EstadoNaoEncontradoException e) {
            throw new NegocioException(e.getMessage(), e);
        }

        return cAssembler.toDTO(cidadeService.atualizar(cidadeAtual));
    }

    @DeleteMapping("/{cidadeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long cidadeId) {
        cidadeService.deletar(cidadeId);

    }
}
