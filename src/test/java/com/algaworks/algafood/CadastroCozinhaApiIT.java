package com.algaworks.algafood;

import com.algaworks.algafood.domain.model.Cozinha;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.flywaydb.core.Flyway;
import org.hamcrest.*;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.http.HttpStatus;

import static io.restassured.RestAssured.enableLoggingOfRequestAndResponseIfValidationFails;
import static  io.restassured.RestAssured.given;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CadastroCozinhaApiIT {

    @LocalServerPort
    private int port;

    @Autowired
    private Flyway flyway;

    @BeforeEach /*Executa sempre antes de um método*/
    public void SetUp(){
        enableLoggingOfRequestAndResponseIfValidationFails();
         RestAssured.basePath = "/cozinhas";
         RestAssured.port = port;
         flyway.migrate();
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
                .body("", Matchers.hasSize(4));

    }

    @Test
    public void aveRetornarStatus201_QuandoCadastrarCozinha(){
        Cozinha cozinha = new Cozinha();
        cozinha.setNome("Nova cozinhaq");
        given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(cozinha)
            .when()
                .post()
            .then()
                .statusCode(HttpStatus.CREATED.value());

    }


}
