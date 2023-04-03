package com.algaworks.algafood.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.UUID;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class    FotoProduto {

    @EqualsAndHashCode.Include
    @Id
    @Column(name = "produto_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private Produto produto;

    private String nomeArquivo;
    private String descricao;
    private String contentType;
    private Long tamanho;

    public Long getRestauranteId(){
        if(this.produto != null){
            return this.produto.getRestaurante().getId();
        }
        return null;
    }

    public void gerarNomeArquivo(){
        this.nomeArquivo = UUID.randomUUID().toString() + "_" + this.nomeArquivo;
    }
}
