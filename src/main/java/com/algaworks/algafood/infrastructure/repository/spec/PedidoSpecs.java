package com.algaworks.algafood.infrastructure.repository.spec;

import com.algaworks.algafood.domain.model.Pedido;
import com.algaworks.algafood.domain.repository.filter.PedidoFilter;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class PedidoSpecs {

    public static Specification<Pedido>  filtroPedidoSpec (PedidoFilter pedidoFilter){
        return ((root, query, criteriaBuilder) -> {
            if(Pedido.class.equals(query.getResultType())){
                root.fetch("restaurante").fetch("cozinha");
                root.fetch("cliente");
            }

            List<Predicate> predicates = new ArrayList<>();

            if(pedidoFilter.getClienteId() != null){
                predicates.add(criteriaBuilder.equal(root.get("cliente"), pedidoFilter.getClienteId()));
            }

            if(pedidoFilter.getRestauranteId()!= null){
                predicates.add(criteriaBuilder.equal(root.get("restaurante"), pedidoFilter.getRestauranteId()));
            }

            if(pedidoFilter.getDataCriacaoInicio()!= null){
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("dataCriacao"), pedidoFilter.getDataCriacaoInicio()));
            }

            if(pedidoFilter.getDataCriacaoFim()!= null){
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("dataCriacao"), pedidoFilter.getDataCriacaoFim()));
            }

            final Predicate[] arrayPredicate = predicates.toArray(new Predicate[0]);

            return criteriaBuilder.and(arrayPredicate);
        });
    }
}
