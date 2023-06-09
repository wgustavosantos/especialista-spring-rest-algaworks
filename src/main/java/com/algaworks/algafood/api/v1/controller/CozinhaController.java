package com.algaworks.algafood.api.v1.controller;

import com.algaworks.algafood.api.v1.assembler.CozinhaAssembler;
import com.algaworks.algafood.api.v1.assembler.CozinhaInputDisassembler;
import com.algaworks.algafood.api.v1.model.dto.CozinhaDTO;
import com.algaworks.algafood.api.v1.model.inputDto.CozinhaInputDTO;
import com.algaworks.algafood.api.v1.openapi.controller.CozinhaControllerOpenApi;
import com.algaworks.algafood.core.security.CheckSecurity;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.service.CozinhaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@Slf4j
@RestController
@RequestMapping(value = "/v1/cozinhas", produces = MediaType.APPLICATION_JSON_VALUE)
public class CozinhaController implements CozinhaControllerOpenApi {

    @Autowired
    private CozinhaRepository cozinhaRepository;

    @Autowired
    private CozinhaService cozinhaService;

    @Autowired
    private CozinhaAssembler cAssembler;

    @Autowired
    private CozinhaInputDisassembler cInputAssembler;

    @Autowired
    private PagedResourcesAssembler<Cozinha> pagedResourcesAssembler;

    @CheckSecurity.Cozinhas.PodeEditar
    @Override
    @PostMapping
    public ResponseEntity<CozinhaDTO> adicionar(@RequestBody @Valid CozinhaInputDTO cozinhaInputDTO) {
        final Cozinha cozinha = cInputAssembler.DTOtoDomainModel(cozinhaInputDTO);
        final CozinhaDTO cozinhaDTO = cAssembler.toModel(cozinhaService.salvar(cozinha));
        return ResponseEntity.status(HttpStatus.CREATED).body(cozinhaDTO);
    }

//    @CheckSecurity.Cozinhas.PodeConsultar
    @Override
    @GetMapping
    public PagedModel<CozinhaDTO> listar(@PageableDefault(size = 5) Pageable pageable) {

        log.info("Quantidade de cozinhas {}", pageable.getPageSize());

        /*
        21.1. Introdução ao Logback e SLF4J
        forçando exceção para para adicionar ao log
        if(true){
            throw new RuntimeException("Teste de Exception");
        } */


        final Page<Cozinha> cozinhasPage = cozinhaService.listar(pageable);

        final PagedModel<CozinhaDTO> cozinhaDTOPagedModel = pagedResourcesAssembler.toModel(cozinhasPage, cAssembler);

//        final CollectionModel<CozinhaDTO> cozinhas = cAssembler.toCollectionModel(cozinhasPage.getContent());
//
//        final PageImpl<CozinhaDTO> cozinhasPageModel = new PageImpl<>(cozinhas, pageable, cozinhas.size());
//
//        return cozinhasPageModel;
        return cozinhaDTOPagedModel;
    }

    @CheckSecurity.Cozinhas.PodeConsultar
    @Override
    @GetMapping("/{cozinhaId}")
    public CozinhaDTO buscar(@PathVariable Long cozinhaId) {
        return cAssembler.toModel(cozinhaService.buscar(cozinhaId));
    }

    @CheckSecurity.Cozinhas.PodeEditar
    @Override
    @PutMapping("/{cozinhaId}")
    public CozinhaDTO atualizar(@RequestBody @Valid CozinhaInputDTO cozinhaInputDTO, @PathVariable Long cozinhaId) {
        final Cozinha cozinhaAtual = cozinhaService.buscar(cozinhaId);
        cInputAssembler.copyProperties(cozinhaInputDTO, cozinhaAtual);

        return cAssembler.toModel(cozinhaService.atualizar(cozinhaAtual));
    }

    @CheckSecurity.Cozinhas.PodeEditar
    @Override
    @DeleteMapping("/{cozinhaId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long cozinhaId) {
        cozinhaService.deletar(cozinhaId);
    }
}
