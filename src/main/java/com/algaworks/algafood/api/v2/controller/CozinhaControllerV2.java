package com.algaworks.algafood.api.v2.controller;

import com.algaworks.algafood.api.v2.assembler.CozinhaAssemblerV2;
import com.algaworks.algafood.api.v2.assembler.CozinhaInputDisassemblerV2;
import com.algaworks.algafood.api.v2.model.CozinhaDTOV2;
import com.algaworks.algafood.api.v2.model.input.CozinhaInputDTOV2;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.service.CozinhaService;
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

@RestController
@RequestMapping(value = "/v2/cozinhas", produces = MediaType.APPLICATION_JSON_VALUE)
public class CozinhaControllerV2{

    @Autowired
    private CozinhaRepository cozinhaRepository;

    @Autowired
    private CozinhaService cozinhaService;

    @Autowired
    private CozinhaAssemblerV2 cAssembler;

    @Autowired
    private CozinhaInputDisassemblerV2 cInputAssembler;

    @Autowired
    private PagedResourcesAssembler<Cozinha> pagedResourcesAssembler;

    @PostMapping
    public ResponseEntity<CozinhaDTOV2> adicionar(@RequestBody @Valid CozinhaInputDTOV2 cozinhaInputDTO) {
        final Cozinha cozinha = cInputAssembler.DTOtoDomainModel(cozinhaInputDTO);
        final CozinhaDTOV2 cozinhaDTO = cAssembler.toModel(cozinhaService.salvar(cozinha));
        return ResponseEntity.status(HttpStatus.CREATED).body(cozinhaDTO);
    }

    @GetMapping
    public PagedModel<CozinhaDTOV2> listar(@PageableDefault(size = 5) Pageable pageable) {

        final Page<Cozinha> cozinhasPage = cozinhaService.listar(pageable);

        final PagedModel<CozinhaDTOV2> cozinhaDTOPagedModel = pagedResourcesAssembler.toModel(cozinhasPage, cAssembler);

//        final CollectionModel<CozinhaDTO> cozinhas = cAssembler.toCollectionModel(cozinhasPage.getContent());
//
//        final PageImpl<CozinhaDTO> cozinhasPageModel = new PageImpl<>(cozinhas, pageable, cozinhas.size());
//
//        return cozinhasPageModel;
        return cozinhaDTOPagedModel;
    }

    @GetMapping("/{cozinhaId}")
    public CozinhaDTOV2 buscar(@PathVariable Long cozinhaId) {
        return cAssembler.toModel(cozinhaService.buscar(cozinhaId));
    }

    @PutMapping("/{cozinhaId}")
    public CozinhaDTOV2 atualizar(@RequestBody @Valid CozinhaInputDTOV2 cozinhaInputDTO, @PathVariable Long cozinhaId) {
        final Cozinha cozinhaAtual = cozinhaService.buscar(cozinhaId);
        cInputAssembler.copyProperties(cozinhaInputDTO, cozinhaAtual);

        return cAssembler.toModel(cozinhaService.atualizar(cozinhaAtual));
    }

    @DeleteMapping("/{cozinhaId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long cozinhaId) {
        cozinhaService.deletar(cozinhaId);
    }
}
