package com.algaworks.algafood.infrastructure.repository.spec;

import com.algaworks.algafood.domain.model.Restaurante;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public class RestauranteSpecs {

    public static Specification<Restaurante> restaurantesComFreteGratis(){
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("taxaFrete"), BigDecimal.ZERO);
    }

    public static Specification<Restaurante> restaurantesComNomeSemelhante(String nome){
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("nome"), "%"+nome+"%");
    }
}
