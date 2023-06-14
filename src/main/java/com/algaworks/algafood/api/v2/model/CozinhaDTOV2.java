package com.algaworks.algafood.api.v2.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "cozinhas")
@Getter
@Setter
public class CozinhaDTOV2 extends RepresentationModel<CozinhaDTOV2> {

    //    @JsonView(RestauranteView.Resumo.class)
    @Schema(example = "1")
    private Long idCozinha;

    //    @JsonView(RestauranteView.Resumo.class)
    @Schema(example = "Brasileira")
    private String nomeCozinha;
}
