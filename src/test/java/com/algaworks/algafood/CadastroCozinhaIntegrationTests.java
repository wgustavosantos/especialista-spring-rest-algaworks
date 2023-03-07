package com.algaworks.algafood;

import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.service.CozinhaService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolationException;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CadastroCozinhaIntegrationTests {

    @Autowired
    private CozinhaService cozinhaService;

    @Test
    /*Happy Path*/
    public void testarCadastroCozinhaComSucesso(){
        // cenário
        Cozinha novaCozinha = new Cozinha();
        novaCozinha.setNome("Chinesa");

        // ação
        novaCozinha = cozinhaService.salvar(novaCozinha);

        // validação
        assertThat(novaCozinha).isNotNull();
        assertThat(novaCozinha.getId()).isNotNull();
    }

    @Test
    /*bad path*/
    public void testarCadastroCozinhaSemNome(){
        // cenário
        Cozinha novaCozinha = new Cozinha();
        novaCozinha.setNome(null);

        // ação
        Assertions.assertThrows(ConstraintViolationException.class,
                () -> cozinhaService.salvar(novaCozinha));
    }
}