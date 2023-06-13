package com.algaworks.algafood.api.v2.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "cozinhas")
@Getter
@Setter
public class CozinhaDTOV2 extends RepresentationModel<CozinhaDTOV2> {

    //    @JsonView(RestauranteView.Resumo.class)
    private Long idCozinha;

    //    @JsonView(RestauranteView.Resumo.class)
    private String nomeCozinha;
}
