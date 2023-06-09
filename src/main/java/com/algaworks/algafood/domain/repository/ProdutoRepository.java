package com.algaworks.algafood.domain.repository;

import com.algaworks.algafood.domain.model.FotoProduto;
import com.algaworks.algafood.domain.model.Produto;
import com.algaworks.algafood.domain.model.Restaurante;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProdutoRepository extends CustomJpaRepository<Produto, Long>, ProdutoRepositoryQueries{

    @Query("from Produto where id = :produtoId and restaurante.id = :restauranteId")
    Optional<Produto> findByProdutoAndRestaurante(Long produtoId, Long restauranteId);

    List<Produto> findByRestaurante(Restaurante restaurante);

    @Query("from Produto p where p.ativo = true and p.restaurante = :restaurante")
    List<Produto> findByAtivosRestaurante(Restaurante restaurante);

    @Query("select f from FotoProduto f join f.produto p where p.restaurante.id = :restauranteId and p.id = :produtoId")
    Optional<FotoProduto> findFotoById(Long restauranteId, Long produtoId);
}
