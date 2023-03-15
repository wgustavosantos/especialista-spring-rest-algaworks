package com.algaworks.algafood;

import com.algaworks.algafood.api.controller.RestauranteController;
import com.algaworks.algafood.api.model.dto.inputDto.RestauranteInputDTO;
import com.algaworks.algafood.domain.exception.RestauranteNaoEncontradoException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.service.CozinhaService;
import com.algaworks.algafood.domain.service.RestauranteService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolationException;
import java.math.BigDecimal;

import static io.restassured.RestAssured.enableLoggingOfRequestAndResponseIfValidationFails;

@SpringBootTest
public class CadastroRestauranteIT {
    private final long restauranteIdInexistente = 99L;

    @Autowired
    private CozinhaService cozinhaService;

    @Autowired
    private RestauranteService restauranteService;

    @Autowired
    private RestauranteController restauranteController;

    private Cozinha cozinhaTeste;
    private Restaurante restauranteTeste;

    @BeforeEach
    public void SetUp(){
        enableLoggingOfRequestAndResponseIfValidationFails();
        prepararDados();
    }

    @Test
    public void testarCadastroRestauranteComSucesso(){
        Restaurante restaurante = new Restaurante();

        restaurante.setNome(this.restauranteTeste.getNome());
        restaurante.setCozinha(this.cozinhaTeste);
        restaurante.setTaxaFrete(this.restauranteTeste.getTaxaFrete());

        restauranteService.salvar(restaurante);

    }

    @Test
    public void testarCadastroRestauranteSemCozinha(){
        RestauranteInputDTO restauranteInputDTO = new RestauranteInputDTO();

        restauranteInputDTO.setNome(this.restauranteTeste.getNome());
        restauranteInputDTO.setTaxaFrete(this.restauranteTeste.getTaxaFrete());

        Assertions
                .assertThrows
                        (NullPointerException.class,
                                () -> restauranteController.adicionar(restauranteInputDTO));
    }

    @Test
    public void testarCadastroRestauranteSemNome(){
        Restaurante restaurante = new Restaurante();

        restaurante.setNome(null);
        restaurante.setTaxaFrete(this.restauranteTeste.getTaxaFrete());
        restaurante.setCozinha(this.cozinhaTeste);
        Assertions
                .assertThrows
                        (ConstraintViolationException.class,
                                () -> restauranteService.salvar(restaurante));
    }

    @Test
    public void testarCadastroRestauranteComFreteNegativo(){
        Restaurante restaurante = new Restaurante();

        restaurante.setNome(this.restauranteTeste.getNome());
        restaurante.setTaxaFrete(BigDecimal.valueOf(-1));
        restaurante.setCozinha(this.cozinhaTeste);
        Assertions
                .assertThrows
                        (ConstraintViolationException.class,
                                () -> restauranteService.salvar(restaurante));
    }

    @Test
    public void testarCadastroExclusaoDeRestauranteInexistente(){

        Assertions
                .assertThrows
                        (RestauranteNaoEncontradoException.class,
                                () -> restauranteService.deletar(restauranteIdInexistente));
    }

    private void prepararDados(){
        Cozinha cozinha = new Cozinha();
        cozinha.setNome("Cozinha para teste de integração");

        this.cozinhaTeste = cozinha = cozinhaService.salvar(cozinha);

        Restaurante restaurante = new Restaurante();
        restaurante.setNome("Restaurante para teste de integração");
        restaurante.setTaxaFrete(BigDecimal.valueOf(10.0));
        restaurante.setCozinha(cozinha);

        this.restauranteTeste = restaurante = restauranteService.salvar(restaurante);

    }
}
