package com.algaworks.algafood.api.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.math.BigDecimal;

@Relation(collectionRelation = "restaurantes")
@Getter
@Setter
public class RestauranteDTO extends RepresentationModel<RestauranteDTO> {

    @ApiModelProperty(example = "1")
//    @JsonView({RestauranteView.Resumo.class, RestauranteView.ResumoApenasNome.class})
    private Long id;

    @ApiModelProperty(example = "Thai Gourmet")
    //@JsonView({RestauranteView.Resumo.class, RestauranteView.ResumoApenasNome.class})
    private String nome;

    @ApiModelProperty(example = "12.00")
    //@JsonView(RestauranteView.Resumo.class)
    private BigDecimal taxaFrete;
    //@JsonView(RestauranteView.Resumo.class)
    private CozinhaDTO cozinha;
    private Boolean ativo = Boolean.TRUE;
    private Boolean aberto;
    private EnderecoDTO endereco;
}
