package com.algaworks.algafood.api.assembler;

import com.algaworks.algafood.api.controller.EstadoController;
import com.algaworks.algafood.api.model.dto.EstadoDTO;
import com.algaworks.algafood.domain.model.Estado;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Component
public class EstadoAssembler extends RepresentationModelAssemblerSupport<Estado, EstadoDTO> {

    @Autowired
    ModelMapper modelMapper;

    public EstadoAssembler() {
        super(EstadoController.class, EstadoDTO.class);
    }

    public EstadoDTO toModel(Estado estado){
        final EstadoDTO estadoDTO = createModelWithId(estado.getId(), estado);

        modelMapper.map(estado, estadoDTO);

        estadoDTO.add(linkTo(EstadoController.class).withRel("estados"));

        return estadoDTO;
    }

    public List<EstadoDTO> toListDTO(List<Estado> estados){
        return estados.stream().map(this::toModel).toList();
    }

    @Override
    public CollectionModel<EstadoDTO> toCollectionModel(Iterable<? extends Estado> entities) {
        return super.toCollectionModel(entities)
                .add(linkTo(EstadoController.class).withSelfRel());
    }

}
