package com.algaworks.algafood.api.v1.controller;

import com.algaworks.algafood.api.ResourceUriHelper;
import com.algaworks.algafood.api.v1.assembler.CidadeAssembler;
import com.algaworks.algafood.api.v1.assembler.CidadeInputDisassembler;
import com.algaworks.algafood.api.v1.model.dto.CidadeDTO;
import com.algaworks.algafood.api.v1.model.inputDto.CidadeInputDTO;
import com.algaworks.algafood.api.v1.openapi.controller.CidadeControllerOpenApi;
import com.algaworks.algafood.domain.exception.EstadoNaoEncontradoException;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.service.CidadeService;
import com.algaworks.algafood.domain.service.EstadoService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "Cidades")
@RestController
@RequestMapping(path = "/v1/cidades", produces = MediaType.APPLICATION_JSON_VALUE)
public class CidadeController implements CidadeControllerOpenApi {

    @Autowired
    private CidadeService cidadeService;

    @Autowired
    private EstadoService estadoService;

    @Autowired
    private CidadeAssembler cAssembler;

    @Autowired
    private CidadeInputDisassembler cidadeInputDisassembler;

    @Override
    @PostMapping
    public ResponseEntity<CidadeDTO> adicionar(@RequestBody @Valid CidadeInputDTO cidadeInputDTO) {
        try {
            final Cidade cidade = cidadeInputDisassembler.DTOtoDomainModel(cidadeInputDTO);
            final CidadeDTO cidadeSalva = cAssembler.toModel(cidadeService.salvar(cidade));

            ResourceUriHelper.addUriInResponseHeader(cidadeSalva.getId());

            return ResponseEntity.status(HttpStatus.CREATED).body(cidadeSalva);
        } catch (EstadoNaoEncontradoException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    @Deprecated
    @Override
    @GetMapping
    public CollectionModel<CidadeDTO> listar() {
        final List<Cidade> cidades = cidadeService.listar();

        return cAssembler.toCollectionModel(cidades);
    }

    @Override
    @GetMapping("/{cidadeId}")
    public CidadeDTO buscar(@PathVariable Long cidadeId) {

        return cAssembler.toModel(cidadeService.buscar(cidadeId));
    }

    @Override
    @PutMapping("/{cidadeId}")
    public CidadeDTO atualizar(@RequestBody @Valid CidadeInputDTO cidadeInputDTO, @PathVariable Long cidadeId) {
        Cidade cidadeAtual;
        try {
            Estado estadoAtual = estadoService.buscar(cidadeInputDTO.getEstado().getId());
            cidadeAtual = cidadeService.buscar(cidadeId);
            cidadeInputDisassembler.copyProperties(cidadeInputDTO, cidadeAtual);
            cidadeAtual.setEstado(estadoAtual);
        } catch (EstadoNaoEncontradoException e) {
            throw new NegocioException(e.getMessage(), e);
        }
        return cAssembler.toModel(cidadeService.atualizar(cidadeAtual));
    }

    @Override
    @DeleteMapping("/{cidadeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long cidadeId) {
        cidadeService.deletar(cidadeId);

    }
}
