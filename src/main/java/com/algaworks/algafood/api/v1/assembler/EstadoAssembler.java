package com.algaworks.algafood.api.v1.assembler;

import com.algaworks.algafood.api.v1.AlgaLinks;
import com.algaworks.algafood.api.v1.controller.EstadoController;
import com.algaworks.algafood.api.v1.model.dto.EstadoDTO;
import com.algaworks.algafood.core.security.AlgaSecurity;
import com.algaworks.algafood.domain.model.Estado;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EstadoAssembler extends RepresentationModelAssemblerSupport<Estado, EstadoDTO> {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    private AlgaLinks algaLinks;

    @Autowired
    private AlgaSecurity algaSecurity;

    public EstadoAssembler() {
        super(EstadoController.class, EstadoDTO.class);
    }

    public EstadoDTO toModel(Estado estado){
        final EstadoDTO estadoDTO = createModelWithId(estado.getId(), estado);

        modelMapper.map(estado, estadoDTO);

        if (algaSecurity.podeConsultarEstados()) {
            estadoDTO.add(algaLinks.linkToEstados("estados"));
        }

        return estadoDTO;
    }

    public List<EstadoDTO> toListDTO(List<Estado> estados){
        return estados.stream().map(this::toModel).toList();
    }

    @Override
    public CollectionModel<EstadoDTO> toCollectionModel(Iterable<? extends Estado> entities) {
        CollectionModel<EstadoDTO> collectionModel = super.toCollectionModel(entities);

        if (algaSecurity.podeConsultarEstados()) {
            collectionModel.add(algaLinks.linkToEstados());
        }

        return collectionModel;
    }

}
