package com.algaworks.algafood.api.v1.assembler;

import com.algaworks.algafood.api.v1.AlgaLinks;
import com.algaworks.algafood.api.v1.controller.RestauranteController;
import com.algaworks.algafood.api.v1.model.dto.RestauranteApenasNomeDTO;
import com.algaworks.algafood.core.security.AlgaSecurity;
import com.algaworks.algafood.domain.model.Restaurante;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class RestauranteApenasNomeDTOAssembler
        extends RepresentationModelAssemblerSupport<Restaurante, RestauranteApenasNomeDTO> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AlgaLinks algaLinks;

    @Autowired
    private AlgaSecurity algaSecurity;

    public RestauranteApenasNomeDTOAssembler() {
        super(RestauranteController.class, RestauranteApenasNomeDTO.class);
    }

    @Override
    public RestauranteApenasNomeDTO toModel(Restaurante restaurante) {
        RestauranteApenasNomeDTO restauranteModel = createModelWithId(
                restaurante.getId(), restaurante);

        modelMapper.map(restaurante, restauranteModel);

        if (algaSecurity.podeConsultarRestaurantes()) {
            restauranteModel.add(algaLinks.linkToRestaurantes("restaurantes"));
        }

        return restauranteModel;
    }

    @Override
    public CollectionModel<RestauranteApenasNomeDTO> toCollectionModel(Iterable<? extends Restaurante> entities) {
        CollectionModel<RestauranteApenasNomeDTO> collectionModel = super.toCollectionModel(entities);

        if (algaSecurity.podeConsultarRestaurantes()) {
            collectionModel.add(algaLinks.linkToRestaurantes());
        }

        return collectionModel;
    }
}