package com.algaworks.algafood.domain.repository;

import com.algaworks.algafood.domain.model.Produto;
import com.algaworks.algafood.domain.model.Restaurante;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProdutoRepository extends CustomJpaRepository<Produto, Long>{

    @Query("from Produto where id = :produtoId and restaurante.id = :restauranteId")
    Optional<Produto> findByProdutoAndRestaurante(Long produtoId, Long restauranteId);

    List<Produto> findByRestaurante(Restaurante restaurante);
}
