package com.algaworks.algafood.api.v1.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
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
    @Schema(example = "1")
    private Long id;

    //@JsonView({RestauranteView.Resumo.class, RestauranteView.ResumoApenasNome.class})
    @Schema(example = "Thai Gourmet")
    private String nome;

    //@JsonView(RestauranteView.Resumo.class)
    @Schema(example = "12.00")
    private BigDecimal taxaFrete;
    //@JsonView(RestauranteView.Resumo.class)
    private CozinhaDTO cozinha;
    private Boolean ativo = Boolean.TRUE;
    private Boolean aberto;
    private EnderecoDTO endereco;
}
