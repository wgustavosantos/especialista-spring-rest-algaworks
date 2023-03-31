package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.api.assembler.CozinhaAssembler;
import com.algaworks.algafood.api.assembler.CozinhaInputDisassembler;
import com.algaworks.algafood.api.model.dto.CozinhaDTO;
import com.algaworks.algafood.api.model.dto.inputDto.CozinhaInputDTO;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.service.CozinhaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/cozinhas")
public class CozinhaController {

    @Autowired
    private CozinhaRepository cozinhaRepository;

    @Autowired
    private CozinhaService cozinhaService;

    @Autowired
    private CozinhaAssembler cAssembler;

    @Autowired
    private CozinhaInputDisassembler cInputAssembler;

    @PostMapping
    public ResponseEntity<CozinhaDTO> adicionar(@RequestBody @Valid CozinhaInputDTO cozinhaInputDTO) {
        final Cozinha cozinha = cInputAssembler.DTOtoDomainModel(cozinhaInputDTO);
        final CozinhaDTO cozinhaDTO = cAssembler.toDTO(cozinhaService.salvar(cozinha));
        return ResponseEntity.status(HttpStatus.CREATED).body(cozinhaDTO);
    }

    @GetMapping
    public Page<CozinhaDTO> listar(@PageableDefault( size = 5) Pageable pageable) {
        final List<CozinhaDTO> cozinhas = cAssembler.toListDTO(cozinhaService.listar(pageable).getContent());

        return new PageImpl<>(cozinhas, pageable, cozinhas.size());
    }

    @GetMapping("/{cozinhaId}")
    public CozinhaDTO buscar(@PathVariable Long cozinhaId) {
        return cAssembler.toDTO(cozinhaService.buscar(cozinhaId));
    }

    @PutMapping("/{cozinhaId}")
    public Cozinha atualizar(@RequestBody @Valid CozinhaInputDTO cozinhaInputDTO, @PathVariable Long cozinhaId) {
        final Cozinha cozinhaAtual = cozinhaService.buscar(cozinhaId);
        cInputAssembler.copyProperties(cozinhaInputDTO, cozinhaAtual);

        return cozinhaService.atualizar(cozinhaAtual);
    }

    @DeleteMapping("/{cozinhaId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long cozinhaId) {
        cozinhaService.deletar(cozinhaId);
    }
}
