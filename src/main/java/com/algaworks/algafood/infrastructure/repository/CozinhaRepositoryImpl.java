package com.algaworks.algafood.infrastructure.repository;

import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class CozinhaRepositoryImpl implements CozinhaRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Cozinha> todas() {
        final TypedQuery<Cozinha> cozinhas = entityManager.createQuery("from Cozinha", Cozinha.class);
        return cozinhas.getResultList();
    }

    @Transactional
    @Override
    public Cozinha salvar(Cozinha cozinha) {
        return entityManager.merge(cozinha);
    }

    @Override
    public Cozinha buscar(Long id) {
        return entityManager.find(Cozinha.class, id);
    }

    @Transactional
    @Override
    public void excluir(Long id) {
        Cozinha cozinha = buscar(id);

        if (cozinha == null) {
            throw new EmptyResultDataAccessException("Entidade cozinha não encontrada", 1);
        }
        entityManager.remove(cozinha);
    }

    @Override
    public List<Cozinha> consultarPorNome(String nome) {
        return entityManager.createQuery("from Cozinha where nome =:nome", Cozinha.class)
                .setParameter("nome", nome).getResultList();
    }
}
