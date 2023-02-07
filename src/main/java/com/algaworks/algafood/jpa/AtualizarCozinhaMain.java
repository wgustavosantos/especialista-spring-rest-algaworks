package com.algaworks.algafood.jpa;

import com.algaworks.algafood.AlgafoodApiApplication;
import com.algaworks.algafood.domain.model.Cozinha;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

public class AtualizarCozinhaMain {
    public static void main(String[] args) {

        ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        final CadastroCozinha bean = applicationContext.getBean(CadastroCozinha.class);

        Cozinha cozinha1 = new Cozinha();

        cozinha1.setNome("Brasileira");
        cozinha1.setId(1L);
        bean.salvar(cozinha1);

    }
}
