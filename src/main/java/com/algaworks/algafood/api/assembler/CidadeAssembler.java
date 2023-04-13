package com.algaworks.algafood.api.assembler;

import com.algaworks.algafood.api.controller.CidadeController;
import com.algaworks.algafood.api.controller.EstadoController;
import com.algaworks.algafood.api.model.dto.CidadeDTO;
import com.algaworks.algafood.domain.model.Cidade;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class CidadeAssembler extends RepresentationModelAssemblerSupport<Cidade, CidadeDTO> {

    @Autowired
    ModelMapper modelMapper;

    public CidadeAssembler(){
        super(CidadeController.class, CidadeDTO.class);
    }

    @Override
    public CidadeDTO toModel(Cidade cidade){


        /*_link.self para o próprio recurso
        cidadeDTO.add(linkTo(CidadeController.class)
                .slash(cidadeDTO.getId()).withSelfRel());*/

        /*_link.self para o próprio recurso
        linkTo recebendo um Object method como argumento
        chamada de método para controller específicos
        final Link linkCidade = linkTo( methodOn(CidadeController.class).buscar(cidadeDTO.getId()) ).withSelfRel();
        cidadeDTO.add(linkCidade);*/

        /*Adicionando linkself dinamicamente*/
        final CidadeDTO cidadeDTO = createModelWithId(cidade.getId(), cidade);

        modelMapper.map(cidade, cidadeDTO);

        /*_link.collection para coleção de cidades
        cidadeDTO.add(linkTo(CidadeController.class)
                .withRel("cidades"));*/

        final Link linkCidades = linkTo(methodOn(CidadeController.class).listar()).withRel("Cidades");
        cidadeDTO.add(linkCidades);

        /*_link.self para o recurso estado/id dentro de cidadeDTO
        cidadeDTO.getEstado().add(linkTo(EstadoController.class)
                .slash(cidadeDTO.getEstado().getId()).withSelfRel());*/

        final Link linkEstados = linkTo(methodOn(EstadoController.class).
                buscar(cidadeDTO.getEstado().getId())).withSelfRel();
        cidadeDTO.getEstado().add(linkEstados);

        return cidadeDTO;
    }

    @Override
    public CollectionModel<CidadeDTO> toCollectionModel(Iterable<? extends Cidade> entities) {

        return super.toCollectionModel(entities).add(linkTo(CidadeController.class).withSelfRel());
    }
}
