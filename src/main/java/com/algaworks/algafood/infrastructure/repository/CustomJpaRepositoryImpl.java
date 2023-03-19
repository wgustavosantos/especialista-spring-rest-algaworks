package com.algaworks.algafood.infrastructure.repository;

import com.algaworks.algafood.domain.repository.CustomJpaRepository;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.Optional;
/* é a especializaçção da implementação SimpleJpaRepository,
que é a implementação padrão do Spring para JpaRepository. Não precisa de anotações pois a SimpleJpaRepository
já contém anotações necessárias para ser uma classe gerenciada pelo contexto do container spring*/
public class CustomJpaRepositoryImpl<T, ID>
        extends SimpleJpaRepository<T, ID>
        implements CustomJpaRepository<T, ID> {

    private EntityManager entityManager;

    public CustomJpaRepositoryImpl(JpaEntityInformation<T, ?> entityInformation,
                                   EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityManager = entityManager;
    }


    @Override
    public Optional<T> buscarPrimeiro() {
        //getDomainClass retorna a classe que representa a entidade, getName retorna o nome em String
        String jpql = "from " + getDomainClass().getName();

        final TypedQuery<T> query = entityManager.createQuery(jpql, getDomainClass());
        final T singleResult = query
                .setMaxResults(1)//somente 1 entidade (sql Limit)
                .getSingleResult();
        return Optional.ofNullable(singleResult);
    }

    @Override
    public void detach(T entity) {
        entityManager.detach(entity);
    }
}
