package com.algaworks.algafood.api.v2.controller;

import com.algaworks.algafood.api.ResourceUriHelper;
import com.algaworks.algafood.api.v2.assembler.CidadeAssemblerV2;
import com.algaworks.algafood.api.v2.assembler.CidadeInputDisassemblerV2;
import com.algaworks.algafood.api.v2.model.CidadeDTOV2;
import com.algaworks.algafood.api.v2.model.input.CidadeInputDTOV2;
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
@RequestMapping(path = "/v2/cidades", produces = MediaType.APPLICATION_JSON_VALUE)
public class CidadeControllerV2 {

    @Autowired
    private CidadeService cidadeService;

    @Autowired
    private EstadoService estadoService;

    @Autowired
    private CidadeAssemblerV2 cidadeAssemblerV2;

    @Autowired
    private CidadeInputDisassemblerV2 cidadeInputDisassemblerV2;

    @PostMapping
    public ResponseEntity<CidadeDTOV2> adicionar(@RequestBody @Valid CidadeInputDTOV2 cidadeInputDTOV2) {
        try {
            final Cidade cidade = cidadeInputDisassemblerV2.DTOtoDomainModel(cidadeInputDTOV2);
            final CidadeDTOV2 cidadeSalva = cidadeAssemblerV2.toModel(cidadeService.salvar(cidade));

            ResourceUriHelper.addUriInResponseHeader(cidadeSalva.getIdCidade());

            return ResponseEntity.status(HttpStatus.CREATED).body(cidadeSalva);
        } catch (EstadoNaoEncontradoException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    @GetMapping
    public CollectionModel<CidadeDTOV2> listar() {
        final List<Cidade> cidades = cidadeService.listar();

        return cidadeAssemblerV2.toCollectionModel(cidades);
    }

    @GetMapping("/{cidadeId}")
    public CidadeDTOV2 buscar(@PathVariable Long cidadeId) {

        return cidadeAssemblerV2.toModel(cidadeService.buscar(cidadeId));
    }

    @PutMapping("/{cidadeId}")
    public CidadeDTOV2 atualizar(@RequestBody @Valid CidadeInputDTOV2 cidadeInputDTOV2, @PathVariable Long cidadeId) {
        Cidade cidadeAtual;
        try {
            Estado estadoAtual = estadoService.buscar(cidadeId);
            cidadeAtual = cidadeService.buscar(cidadeId);
            cidadeInputDisassemblerV2.copyProperties(cidadeInputDTOV2, cidadeAtual);
            cidadeAtual.setEstado(estadoAtual);
        } catch (EstadoNaoEncontradoException e) {
            throw new NegocioException(e.getMessage(), e);
        }
        return cidadeAssemblerV2.toModel(cidadeService.atualizar(cidadeAtual));
    }

    @DeleteMapping("/{cidadeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long cidadeId) {
        cidadeService.deletar(cidadeId);

    }
}
