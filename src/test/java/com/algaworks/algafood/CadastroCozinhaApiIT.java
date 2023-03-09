package com.algaworks.algafood;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.*;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import static io.restassured.RestAssured.enableLoggingOfRequestAndResponseIfValidationFails;
import static  io.restassured.RestAssured.given;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CadastroCozinhaApiIT {

    @LocalServerPort
    private int port;

    @Test
    public void deveRetornar200_QuandoConsultarCozinhas(){

         enableLoggingOfRequestAndResponseIfValidationFails();

        given()
                .basePath("/cozinhas")
                .port(port)
                .accept(ContentType.JSON)
            .when()
                .get()
            .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void deveConter7Cozinhas_QuandoConsultarCozinhas(){

        enableLoggingOfRequestAndResponseIfValidationFails();
        given()
                .basePath("/cozinhas")
                .port(port)
                .accept(ContentType.JSON)
            .when()
                .get()
            .then()
                .body("", Matchers.hasSize(4))
            .body("nome", Matchers.hasItems("teste"));
    }

    @Test
    public void deveConter7Restaurantes_QuandoConsultarRestaurantes() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

        RestAssured.given()
                .basePath("/restaurantes")
                .port(port)
                .accept(ContentType.JSON)
                .when()
                .get()
                .then()
                .body("", Matchers.hasSize(7))
                .body("nome", Matchers.hasItems("Thai Gourmet"))
                .body("taxaFrete", Matchers.hasItems(Matchers.greaterThan(5.0f)));

    }
    @Test
    public void deveConterRestauranteComFreteGratis_e_nomeComFreteGratis_QuandoConsultarRestaurantes() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

        RestAssured.given()
                .basePath("/restaurantes")
                .port(port)
                .accept(ContentType.JSON)
            .when()
                .get()
            .then()
                .body("nome", Matchers.hasItems(Matchers.containsString("Frete Grátis")))
                    .and().body("taxaFrete", Matchers.hasItems(Matchers.equalTo(0.0f)));
    }

    @Test
    public void deveConter1Restaurante_ComFreteIgualA10_QuandoConsultarRestaurantes() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

        RestAssured.given()
                .basePath("/restaurantes")
                .port(port)
                .accept(ContentType.JSON)
                .when()
                .get("/1")
                .then()
                .body("taxaFrete", Matchers.equalTo(10.0f));

    }

}
