package com.algaworks.algafood;

import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.util.DatabaseCleaner;
import com.algaworks.algafood.util.ResourceUtils;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;

import java.util.Arrays;

import static io.restassured.RestAssured.enableLoggingOfRequestAndResponseIfValidationFails;
import static io.restassured.RestAssured.given;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
public class CadastroCozinhaApiIT {

    public static final int ID = 100;
    public static final String FILES_DATA_JSON = "/JsonFiles/data.json";
    @LocalServerPort
    private int port;

    /*Classe para limpar base de dados*/
    @Autowired
    private DatabaseCleaner databaseCleaner;

    @Autowired
    private CozinhaRepository cozinhaRepository;

    private long cozinhas;

    private Cozinha cozinhaParaTeste;

    @BeforeEach /*Executa sempre antes de um método*/
    public void SetUp(){
        enableLoggingOfRequestAndResponseIfValidationFails();
         RestAssured.basePath = "/cozinhas";
         RestAssured.port = port;
         databaseCleaner.clearTables();
         prepararDados();
         this.cozinhas = quantidadeDeCozinhas();
    }

    @Test
    public void deveRetornar200_QuandoConsultarCozinhas(){
        given()
                .accept(ContentType.JSON)
            .when()
                .get()
            .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void zveRetornar4Cozinhas_QuandoConsultarCozinhas(){
        given()
            .accept(ContentType.JSON)
        .when()
            .get()
        .then()
                .body("", Matchers.hasSize((int)cozinhas));

    }

    @Test
    public void aveRetornarStatus201_QuandoCadastrarCozinha(){
        given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(ResourceUtils.getContentFromResource(FILES_DATA_JSON))
            .when()
                .post()
            .then()
                .statusCode(HttpStatus.CREATED.value());
    }

    @Test
    public void deveRetornarRespostasEStatusCorretos_QuandoConsultarCozinhaExistente(){
        given()
                .accept(ContentType.JSON)
                .pathParams("cozinhaId", cozinhaParaTeste.getId())
            .when()
                .get("/{cozinhaId}")
            .then()
                .statusCode(HttpStatus.OK.value())
                .body("nome", Matchers.equalTo(cozinhaParaTeste.getNome()));
    }

    @Test
    public void deveRetornarStatus404_QuandoConsultarCozinhaInexistente(){
        given()
                .accept(ContentType.JSON)
                .pathParams("cozinhaId", ID)
                .when()
                .get("/{cozinhaId}")
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }

    private void prepararDados(){
        Cozinha cozinha = new Cozinha();
        cozinha.setNome("Americana");
        Cozinha cozinha2 = new Cozinha();
        cozinha2.setNome("Portuguesa");

        this.cozinhaParaTeste = cozinha;
        cozinhaRepository.saveAll(Arrays.asList(cozinha, cozinha2));
    }

    private long quantidadeDeCozinhas(){
        return cozinhaRepository.count();
    }
}
