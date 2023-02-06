package com.algaworks.algafood.jpa;

import com.algaworks.algafood.domain.model.Cozinha;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Component
public class CadastroCozinha {

    @PersistenceContext
    private EntityManager entityManager;
    
    public List<Cozinha> listar(){
        final TypedQuery<Cozinha> cozinhas = entityManager.createQuery("from Cozinha", Cozinha.class);

        return cozinhas.getResultList();
    }
}
