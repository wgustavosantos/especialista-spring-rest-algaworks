package com.algaworks.algafood.infrastructure.repository;

import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.EstadoRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Component
public class EstadoRepositoryImpl implements EstadoRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Estado> todos(){
        final TypedQuery<Estado> estados = entityManager.createQuery("from Estado", Estado.class);

        return estados.getResultList();
    }

    @Transactional
    @Override
    public Estado salvar(Estado estado){
        return entityManager.merge(estado);
    }

    @Override
    public Estado buscar(Long id){
        return entityManager.find(Estado.class, id);
    }

    @Transactional
    @Override
    public void excluir(Long estadoId){
        final Estado estado = buscar(estadoId);
        if(estado == null){
            throw new EmptyResultDataAccessException("Entidade cozinha não encontrada", 1);
        }
        entityManager.remove(estado);
    }
}
