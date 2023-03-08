package com.algaworks.algafood;

import com.algaworks.algafood.domain.exception.CozinhaNaoEncontradaException;
import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.service.CozinhaService;
import com.algaworks.algafood.domain.service.RestauranteService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolationException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
class CadastroCozinhaIT {

    @Autowired
    private CozinhaService cozinhaService;

    @Autowired
    private RestauranteService restauranteService;

    @Test
    /*Happy Path*/
    public void testarCadastroCozinhaComSucesso() {
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
    public void testarCadastroCozinhaSemNome() {
        // cenário
        Cozinha novaCozinha = new Cozinha();
        novaCozinha.setNome(null);

        // ação
        Assertions.assertThrows(ConstraintViolationException.class,
                () -> cozinhaService.salvar(novaCozinha));
    }

    @Test
    /* bad path */
    public void deveFalhar_QuandoExcluirCozinhaEmUso() {
        final Long cozinhaId = restauranteService.buscarOuFalhar(1L).getCozinha().getId();

        final EntidadeEmUsoException erroEsperado = Assertions.
                assertThrows(EntidadeEmUsoException.class, () -> cozinhaService.deletar(cozinhaId));
        assertThat(erroEsperado).isNotNull();
    }

        @Test
        /* bad path */
        public void deveFalhar_QuandoExcluirCozinhaInexistente() {
            final Long cozinhaId = 55L;

            final CozinhaNaoEncontradaException erroEsperado = Assertions
                    .assertThrows(CozinhaNaoEncontradaException.class, () -> cozinhaService.deletar(cozinhaId));
            System.out.println("antes do assertThaht");

            assertThat(erroEsperado).isNotNull();
        }
}