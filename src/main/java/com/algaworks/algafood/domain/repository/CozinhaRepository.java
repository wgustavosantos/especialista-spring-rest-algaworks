package com.algaworks.algafood.domain.repository;

import com.algaworks.algafood.domain.model.Cozinha;

import java.util.List;

public interface CozinhaRepository {
    List<Cozinha> todas();
    List<Cozinha> consultarPorNome(String nome);

    Cozinha buscar(Long id);
    Cozinha salvar(Cozinha cozinha);
    void excluir(Long id);
}
