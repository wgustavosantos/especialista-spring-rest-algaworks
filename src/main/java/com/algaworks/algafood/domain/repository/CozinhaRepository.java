package com.algaworks.algafood.domain.repository;

import com.algaworks.algafood.domain.model.Cozinha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CozinhaRepository extends JpaRepository<Cozinha, Long> {

    Optional<Cozinha> findByNome(String nome);
    List<Cozinha> findTodasByNome(String nome);
    List<Cozinha> findTodasByNomeContaining(String nome);

}
