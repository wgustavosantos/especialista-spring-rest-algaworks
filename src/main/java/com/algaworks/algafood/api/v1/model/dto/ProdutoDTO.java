package com.algaworks.algafood.api.v1.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.math.BigDecimal;

@Relation(collectionRelation = "produtos")
@Getter
@Setter
public class ProdutoDTO extends RepresentationModel<ProdutoDTO> {

    @Schema(example = "1")
    private Integer id;

    @Schema(example = "Espetinho de Cupim")
    private String nome;

    @Schema(example = "Acompanha farinha, mandioca e vinagrete")
    private String descricao;

    @Schema(example = "12.50")
    private BigDecimal preco;

    @Schema(example = "true")
    private Boolean ativo;

}
