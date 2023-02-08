package com.algaworks.algafood.infrastructure.repository;

import com.algaworks.algafood.domain.model.FormaPagamento;
import com.algaworks.algafood.domain.repository.FormaPagamentoRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Component
public class FormaPagamentoRepositoryImpl implements FormaPagamentoRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<FormaPagamento> todas(){
        final TypedQuery<FormaPagamento> formasPagamento = entityManager.createQuery("from FormaPagamento", FormaPagamento.class);

        return formasPagamento.getResultList();
    }

    @Transactional
    @Override
    public FormaPagamento adicionar(FormaPagamento formaPagamento){
        return entityManager.merge(formaPagamento);
    }

    @Override
    public FormaPagamento porId(Long id){
        return entityManager.find(FormaPagamento.class, id);
    }

    @Transactional
    @Override
    public void remover (FormaPagamento formaPagamento){
        formaPagamento = porId(formaPagamento.getId());
        entityManager.remove(formaPagamento);
    }
}
