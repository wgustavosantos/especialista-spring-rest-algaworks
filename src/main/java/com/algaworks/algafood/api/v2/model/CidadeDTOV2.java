package com.algaworks.algafood.api.v2.model;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@ApiModel("CidadeDTO")
@Relation(collectionRelation = "cidades")
@Getter
@Setter
public class CidadeDTOV2 extends RepresentationModel<CidadeDTOV2> {

    private Long idCidade;

    private String nomeCidade;

    private Long idEstado;
    private String nomeEstado;

}
