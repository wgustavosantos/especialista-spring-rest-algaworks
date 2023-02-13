package com.algaworks.algafood.infrastructure.repository;

import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.repository.CidadeRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Component
public class CidadeRepositoryImpl implements CidadeRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Cidade> todas(){
        final TypedQuery<Cidade> cidades = entityManager.createQuery("from Cidade", Cidade.class);

        return cidades.getResultList();
    }

    @Transactional
    @Override
    public Cidade salvar(Cidade cidade){
        return entityManager.merge(cidade);
    }

    @Override
    public Cidade buscar(Long id){
        return entityManager.find(Cidade.class, id);
    }

    @Transactional
    @Override
    public void remover (Cidade cidade){
        cidade = buscar(cidade.getId());
        entityManager.remove(cidade);
    }
}
