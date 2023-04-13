package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.api.ResourceUriHelper;
import com.algaworks.algafood.api.assembler.CidadeAssembler;
import com.algaworks.algafood.api.assembler.CidadeInputDisassembler;
import com.algaworks.algafood.api.model.dto.CidadeDTO;
import com.algaworks.algafood.api.model.inputDto.CidadeInputDTO;
import com.algaworks.algafood.api.openapi.controller.CidadeControllerOpenApi;
import com.algaworks.algafood.domain.exception.EstadoNaoEncontradoException;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.service.CidadeService;
import com.algaworks.algafood.domain.service.EstadoService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Api(tags = "Cidades")
@RestController
@RequestMapping(path = "/cidades", produces = MediaType.APPLICATION_JSON_VALUE)
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
            final CidadeDTO cidadeSalva = cAssembler.toDTO(cidadeService.salvar(cidade));

            ResourceUriHelper.addUriInResponseHeader(cidadeSalva.getId());

            return ResponseEntity.status(HttpStatus.CREATED).body(cidadeSalva);
        } catch (EstadoNaoEncontradoException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    @Override
    @GetMapping
    public CollectionModel<CidadeDTO> listar() {
        final List<Cidade> cidades = cidadeService.listar();
        final List<CidadeDTO> cidadesDTOS = cAssembler.toListDTO(cidades);

        /*Para criar links para cada objeto json*/
        cidadesDTOS.forEach(cidade -> {
            final Link linkEstados = linkTo(methodOn(EstadoController.class).
                    buscar(cidade.getEstado().getId())).withSelfRel();
            cidade.getEstado().add(linkEstados);

            final Link linkCidades = linkTo(methodOn(CidadeController.class).listar()).withRel("Cidades");
            cidade.add(linkCidades);

            final Link linkCidade = linkTo( methodOn(CidadeController.class).buscar(cidade.getId()) ).withSelfRel();
            cidade.add(linkCidade);
        });

        final CollectionModel<CidadeDTO> cidadesCollectionModel = CollectionModel.of(cidadesDTOS);

        /*link para listagem de cidades*/
        cidadesCollectionModel.add(linkTo(CidadeController.class).withSelfRel());



        return cidadesCollectionModel;
    }

    @Override
    @GetMapping("/{cidadeId}")
    public CidadeDTO buscar(@PathVariable Long cidadeId) {
        final CidadeDTO cidade = cAssembler.toDTO(cidadeService.buscar(cidadeId));

        /*_link.self para o próprio recurso
        cidade.add(linkTo(CidadeController.class)
                .slash(cidade.getId()).withSelfRel());*/

        /*_link.self para o próprio recurso
        linkTo recebendo um Object method como argumento
        chamada de método para controller específicos*/
        final Link linkCidade = linkTo( methodOn(CidadeController.class).buscar(cidade.getId()) ).withSelfRel();
        cidade.add(linkCidade);

        /*_link.collection para coleção de cidades
        cidade.add(linkTo(CidadeController.class)
                .withRel("cidades"));*/

        final Link linkCidades = linkTo(methodOn(CidadeController.class).listar()).withRel("Cidades");
        cidade.add(linkCidades);

        /*_link.self para o recurso estado/id dentro de cidade
        cidade.getEstado().add(linkTo(EstadoController.class)
                .slash(cidade.getEstado().getId()).withSelfRel());*/

        final Link linkEstados = linkTo(methodOn(EstadoController.class).
                buscar(cidade.getEstado().getId())).withSelfRel();
        cidade.getEstado().add(linkEstados);

        return cidade;
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
        return cAssembler.toDTO(cidadeService.atualizar(cidadeAtual));
    }

    @Override
    @DeleteMapping("/{cidadeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long cidadeId) {
        cidadeService.deletar(cidadeId);

    }
}
