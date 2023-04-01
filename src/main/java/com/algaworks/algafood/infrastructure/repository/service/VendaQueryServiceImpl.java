package com.algaworks.algafood.infrastructure.repository.service;

import com.algaworks.algafood.domain.filter.VendaDiariaFilter;
import com.algaworks.algafood.domain.model.Pedido;
import com.algaworks.algafood.domain.model.dto.VendaDiaria;
import com.algaworks.algafood.domain.service.VendaQueryService;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.util.Date;
import java.util.List;

@Repository
public class VendaQueryServiceImpl implements VendaQueryService {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<VendaDiaria> consultarVendasDiarias(VendaDiariaFilter vendaDiariaFilter){

        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<VendaDiaria> query = criteriaBuilder.createQuery(VendaDiaria.class);
        final Root<Pedido> root = query.from(Pedido.class);

        final Expression<Date> function = criteriaBuilder.function("date", Date.class, root.get("dataCriacao"));

        final CompoundSelection<VendaDiaria> selection = criteriaBuilder.construct(VendaDiaria.class,
                function,
                criteriaBuilder.count(root.get("id")),
                criteriaBuilder.sum(root.get("valorTotal")));

        final CriteriaQuery<VendaDiaria> select = query.select(selection);
        query.groupBy(function);

        return entityManager.createQuery(query).getResultList();
    }
}
