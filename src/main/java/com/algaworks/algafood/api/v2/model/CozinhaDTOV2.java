package com.algaworks.algafood.api.v2.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@ApiModel("CozinhaDTO")
@Relation(collectionRelation = "cozinhas")
@Getter
@Setter
public class CozinhaDTOV2 extends RepresentationModel<CozinhaDTOV2> {

    //    @JsonView(RestauranteView.Resumo.class)
    @ApiModelProperty(example = "1")
    private Long idCozinha;

    //    @JsonView(RestauranteView.Resumo.class)
    @ApiModelProperty(example = "Brasileira")
    private String nomeCozinha;
}
