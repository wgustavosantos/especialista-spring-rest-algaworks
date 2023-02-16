package com.algaworks.algafood.infrastructure.repository;

import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepositoryQueries;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

@Repository
public class RestauranteRepositoryImpl implements RestauranteRepositoryQueries {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Restaurante> find(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal) {

        //constroi elementos para criar a consulta
        CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();

        //instância de criteria de restaurante para criar uma consulta de restaurante
        CriteriaQuery<Restaurante> criteria = criteriaBuilder.createQuery(Restaurante.class);

        //constroi a consulta
        criteria.from(Restaurante.class);

        final TypedQuery<Restaurante> query = manager.createQuery(criteria);

        return query.getResultList();
    }

    /*Código sem utilizar criteria query*/
    private TypedQuery<Restaurante> getRestauranteTypedQuery(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal) {
        StringBuilder jpql = new StringBuilder();
        jpql.append("from Restaurante where 0 = 0 ");

        final HashMap<String, Object> parametros = new HashMap<>();

        if(StringUtils.hasLength(nome)){
            jpql.append(("and nome like :nome "));
            parametros.put("nome", "%" + nome + "%");
        }

        if(taxaFreteInicial != null){
            jpql.append("and taxaFrete >= :taxaFreteInicial ");
            parametros.put("taxaFreteInicial", taxaFreteInicial);
        }

        if(taxaFreteFinal != null){
            jpql.append("and taxaFrete <= :taxaFreteFinal");
            parametros.put("taxaFreteFinal", taxaFreteFinal);
        }
        TypedQuery<Restaurante> query = manager.createQuery(jpql.toString(), Restaurante.class);
        parametros.forEach(query::setParameter);
        return query;
    }

}