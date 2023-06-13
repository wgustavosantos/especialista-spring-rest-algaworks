package com.algaworks.algafood.api.v1.model.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "cozinhas")
@Getter
@Setter
public class CozinhaDTO extends RepresentationModel<CozinhaDTO> {

    //    @JsonView(RestauranteView.Resumo.class)
    private Long id;

    //    @JsonView(RestauranteView.Resumo.class)
    private String nome;
}
