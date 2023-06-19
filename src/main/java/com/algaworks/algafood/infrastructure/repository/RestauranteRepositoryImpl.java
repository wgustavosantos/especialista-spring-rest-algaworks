package com.algaworks.algafood.infrastructure.repository;

import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import com.algaworks.algafood.domain.repository.RestauranteRepositoryQueries;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.algaworks.algafood.infrastructure.repository.spec.RestauranteSpecs.restaurantesComNomeSemelhante;

@Repository
public class RestauranteRepositoryImpl implements RestauranteRepositoryQueries {

    @PersistenceContext
    private EntityManager manager;

    @Autowired @Lazy
    private RestauranteRepository restauranteRepository;

    @Override
    public List<Restaurante> find(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal) {
        List<Predicate> predicates = new ArrayList<>();

        //instância de CriteriaBuilder que constroi elementos para criar a consulta
        CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();

        //instância de criteria de restaurante para criar uma consulta de restaurante
        CriteriaQuery<Restaurante> criteria = criteriaBuilder.createQuery(Restaurante.class);

        //equivalente a "from Restaurante" é o root da consulta
        final Root<Restaurante> root = criteria.from(Restaurante.class);

        //predicados da consulta

        if(StringUtils.hasText(nome)){
            predicates.add(criteriaBuilder.like(root.get("nome"),"%"+nome+"%"));
        }

        if(taxaFreteInicial != null){
         predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("taxaFrete"), taxaFreteInicial));
        }

        if(taxaFreteFinal != null){
            criteriaBuilder.lessThanOrEqualTo(root.get("taxaFrete"), taxaFreteFinal);
        }

        //criado a consula "form Restaurante where nome like :nome and taxaFrete >= :taxaFrenteInicial and taxaFrete >= taxaFrenteFinal"
        criteria.where(predicates.toArray(new Predicate[0]));

        //constroi a query e retorna a query do banco
        final TypedQuery<Restaurante> query = manager.createQuery(criteria);

        return query.getResultList();
    }

    @Override
    public List<Restaurante> findComFreteGratis(String nome) {
        return restauranteRepository.findAll(restaurantesComNomeSemelhante(nome)
                .and(restaurantesComNomeSemelhante(nome)));
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