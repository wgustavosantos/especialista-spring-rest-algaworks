package com.algaworks.algafood.jpa;

import com.algaworks.algafood.AlgafoodApiApplication;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.util.List;

public class ConsultaRestauranteMain {
    public static void main(String[] args) {

        ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        final RestauranteRepository restaurantes = applicationContext.getBean(RestauranteRepository.class);

        final List<Restaurante> todos = restaurantes.todos();

        for (Restaurante r : todos) {
            System.out.printf("Nome: %s Cozinha: %s Taxa Frete: %.2f\n", r.getNome(), r.getCozinha().getNome(), r.getTaxaFrete());
        }


    }
}
