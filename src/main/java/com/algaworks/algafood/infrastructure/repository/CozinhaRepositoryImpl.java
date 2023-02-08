package com.algaworks.algafood.infrastructure.repository;

import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Component
public class CozinhaRepositoryImpl implements CozinhaRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Cozinha> todas(){
        final TypedQuery<Cozinha> cozinhas = entityManager.createQuery("from Cozinha", Cozinha.class);

        return cozinhas.getResultList();
    }

    @Transactional
    @Override
    public Cozinha adicionar(Cozinha cozinha){
        return entityManager.merge(cozinha);
    }

    @Override
    public Cozinha porId(Long id){
        return entityManager.find(Cozinha.class, id);
    }

    @Transactional
    @Override
    public void remover (Cozinha cozinha){
        cozinha = porId(cozinha.getId());
        entityManager.remove(cozinha);
    }
}
