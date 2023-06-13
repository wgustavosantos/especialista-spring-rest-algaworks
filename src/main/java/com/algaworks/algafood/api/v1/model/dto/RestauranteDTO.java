package com.algaworks.algafood.api.v1.model.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.math.BigDecimal;

@Relation(collectionRelation = "restaurantes")
@Getter
@Setter
public class RestauranteDTO extends RepresentationModel<RestauranteDTO> {

//    @JsonView({RestauranteView.Resumo.class, RestauranteView.ResumoApenasNome.class})
    private Long id;

    //@JsonView({RestauranteView.Resumo.class, RestauranteView.ResumoApenasNome.class})
    private String nome;

    //@JsonView(RestauranteView.Resumo.class)
    private BigDecimal taxaFrete;
    //@JsonView(RestauranteView.Resumo.class)
    private CozinhaDTO cozinha;
    private Boolean ativo = Boolean.TRUE;
    private Boolean aberto;
    private EnderecoDTO endereco;
}
