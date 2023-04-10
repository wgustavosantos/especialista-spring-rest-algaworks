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
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "Cidades")
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

    @ApiOperation("Cadastra uma cidade")
    @PostMapping
    public ResponseEntity<CidadeDTO> adicionar(@ApiParam(name = "corpo", value = "Representação de uma nova cidade")
                                                   @RequestBody @Valid CidadeInputDTO cidadeInputDTO) {
        try {
            final Cidade cidade = cidadeInputDisassembler.DTOtoDomainModel(cidadeInputDTO);
            Cidade c = cidadeService.salvar(cidade);
            return ResponseEntity.status(HttpStatus.CREATED).body(cAssembler.toDTO(c));
        } catch (EstadoNaoEncontradoException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }
    @ApiOperation("Lista as cidades")
    @GetMapping
    public ResponseEntity<List<CidadeDTO>> listar() {
        final List<Cidade> cidades = cidadeService.listar();
        return ResponseEntity.ok(cAssembler.toListDTO(cidades));
    }

    @ApiOperation("Busca uma cidade por ID")
    @GetMapping("/{cidadeId}")
    public CidadeDTO buscar(@ApiParam("ID de uma cidade") @PathVariable Long cidadeId) {
        return cAssembler.toDTO(cidadeService.buscar(cidadeId));
    }

    @ApiOperation("Atualiza uma cidade por ID")
    @PutMapping("/{cidadeId}")
    public CidadeDTO atualizar(@RequestBody @Valid CidadeInputDTO cidadeInputDTO, @ApiParam("ID de uma cidade") @PathVariable Long cidadeId) {
        Cidade cidadeAtual;
        try {
            Estado estadoAtual = estadoService.buscar(cidadeInputDTO.getEstado().getId());
            cidadeAtual = cidadeService.buscar(cidadeId);
            cidadeInputDisassembler.copyProperties(cidadeInputDTO, cidadeAtual);
            cidadeAtual.setEstado(estadoAtual);
        } catch (EstadoNaoEncontradoException e) {
            throw new NegocioException(e.getMessage(), e);
        }

        return cAssembler.toDTO(cidadeService.atualizar(cidadeAtual));
    }

    @ApiOperation("Exclui uma cidade por ID")
    @DeleteMapping("/{cidadeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@ApiParam("ID de uma cidade") @PathVariable Long cidadeId) {
        cidadeService.deletar(cidadeId);

    }
}
