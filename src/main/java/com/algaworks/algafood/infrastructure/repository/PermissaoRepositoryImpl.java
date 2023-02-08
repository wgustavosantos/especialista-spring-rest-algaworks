package com.algaworks.algafood.infrastructure.repository;

import com.algaworks.algafood.domain.model.Permissao;
import com.algaworks.algafood.domain.repository.PermissaoRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Component
public class PermissaoRepositoryImpl implements PermissaoRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Permissao> todas(){
        final TypedQuery<Permissao> permissoes = entityManager.createQuery("from Permissao", Permissao.class);

        return permissoes.getResultList();
    }

    @Transactional
    @Override
    public Permissao adicionar(Permissao permissao){
        return entityManager.merge(permissao);
    }

    @Override
    public Permissao porId(Long id){
        return entityManager.find(Permissao.class, id);
    }

    @Transactional
    @Override
    public void remover (Permissao permissao){
        permissao = porId(permissao.getId());
        entityManager.remove(permissao);
    }
}
