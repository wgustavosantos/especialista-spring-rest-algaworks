package com.algaworks.algafood.infrastructure.repository.service.query;

import com.algaworks.algafood.domain.filter.VendaDiariaFilter;
import com.algaworks.algafood.domain.model.Pedido;
import com.algaworks.algafood.domain.model.dto.VendaDiaria;
import com.algaworks.algafood.domain.model.enums.StatusPedido;
import com.algaworks.algafood.domain.service.VendaQueryService;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class VendaQueryServiceImpl implements VendaQueryService {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<VendaDiaria> consultarVendasDiarias(VendaDiariaFilter vendaDiariaFilter, String timeOffSet){

        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<VendaDiaria> query = criteriaBuilder.createQuery(VendaDiaria.class);
        final Root<Pedido> root = query.from(Pedido.class);

        List<Predicate> predicates = new ArrayList<>();

        final Expression<Date> convert_tz = criteriaBuilder.
                function("convert_tz", Date.class, root.get("dataCriacao"),
                        criteriaBuilder.literal("+00:00"), criteriaBuilder.literal(timeOffSet));

        final Expression<Date> date = criteriaBuilder.function("date", Date.class, convert_tz);



        if(vendaDiariaFilter == null){
            vendaDiariaFilter = new VendaDiariaFilter();
        }

        if(vendaDiariaFilter.getRestauranteId() != null){
            predicates.add(criteriaBuilder.equal(root.get("restaurante").get("id"), vendaDiariaFilter.getRestauranteId()));
        }

        if(vendaDiariaFilter.getDataCriacaoInicio() != null){
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("dataCriacao"), vendaDiariaFilter.getDataCriacaoInicio()));
        }

        if(vendaDiariaFilter.getDataCriacaoFim() != null){
            predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("dataCriacao"), vendaDiariaFilter.getDataCriacaoFim()));
        }

       predicates.add(root.get("status").in(StatusPedido.CONFIRMADO, StatusPedido.ENTREGUE));



        final CompoundSelection<VendaDiaria> selection = criteriaBuilder.construct(VendaDiaria.class,
                date,
                criteriaBuilder.count(root.get("id")),
                criteriaBuilder.sum(root.get("valorTotal")));



        query.select(selection);
        query.where(predicates.toArray(new Predicate[0]));
        query.groupBy(date);

        return entityManager.createQuery(query).getResultList();
    }
}
