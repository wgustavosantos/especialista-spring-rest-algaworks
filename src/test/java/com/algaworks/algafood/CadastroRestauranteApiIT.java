package com.algaworks.algafood;

import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import com.algaworks.algafood.util.DatabaseCleaner;
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

import java.math.BigDecimal;
import java.util.Arrays;

import static com.algaworks.algafood.util.ResourceUtils.getContentFromResource;
import static io.restassured.RestAssured.enableLoggingOfRequestAndResponseIfValidationFails;
import static io.restassured.RestAssured.given;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
public class CadastroRestauranteApiIT {

    public static final long RESTAURANTE_ID_INEXISTENTE = 100L;
    public static final String TAXA_DE_FRETE_DO_RESTAURANTE_E_OBRIGATORIA = "Taxa de frete do restaurante é obrigatória";
    private String restauranteCorreto;
    private String jsonRestauranteSemFrete;
    private String jsonRestauranteSemCozinha;
    private String jsonRestauranteComCozinhaInexistente;
    private String jsonRestauranteComFreteGratisSemNomeFreteGratis;

    @LocalServerPort
    private int port;
    private Cozinha cozinhaParaTeste;
    private Cozinha cozinhaParaTeste2;
    @Autowired
    private CozinhaRepository cozinhaRepository;

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private DatabaseCleaner databaseCleaner;
    private Restaurante restauranteParaTeste;

    @BeforeEach
    public void SetUp(){
        enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.port = port;
        RestAssured.basePath = "/restaurantes";

        restauranteCorreto =
                getContentFromResource("/JsonFiles/restauranteJsonFiles/restaurante-new-york-barbecue.json");
        jsonRestauranteSemFrete =
                getContentFromResource("/JsonFiles/restauranteJsonFiles/restaurante-new-york-barbecue-sem-frete.json");
        jsonRestauranteSemCozinha =
                getContentFromResource("/JsonFiles/restauranteJsonFiles/restaurante-new-york-barbecue-sem-cozinha.json");
        jsonRestauranteComCozinhaInexistente =
                getContentFromResource("/JsonFiles/restauranteJsonFiles/restaurante-new-york-barbecue-com-cozinha-inexistente.json");
        jsonRestauranteComFreteGratisSemNomeFreteGratis =
                getContentFromResource("/JsonFiles/restauranteJsonFiles/restaurante-new-york-barbecue-com-frete-gratis-sem-nome-frete-gratis.json");

        databaseCleaner.clearTables();
        prepararDados();
    }

    @Test
    public void deveRetornarStatus200_QuandoConsultarRestaurantes(){
        given()
                .accept(ContentType.JSON)
            .when()
                .get()
            .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void deveRetornarStatus201_QuandoCadastrarRestaurante(){
        given()
                .body(restauranteCorreto)
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
            .when()
                .post()
            .then()
                .statusCode(HttpStatus.CREATED.value());
    }

    @Test
    public void deveRetornarStatus400_QuandoCadastrarRestauranteSemTaxaFrete(){
        given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(jsonRestauranteSemFrete)
            .when()
                .post()
            .then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body("objects", Matchers.hasItem(Matchers.hasEntry("userMessage", "Taxa de frete do restaurante é obrigatória")));
    }

    @Test
    public void ddeveRetornarStatus400_QuandoCadastrarRestauranteSemCozinha(){
        given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(jsonRestauranteSemCozinha)
                .when()
                .post()
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body("objects.userMessage", Matchers.hasItem(Matchers.equalTo("Cozinha do restaurante é obrigatória")));
    }

    @Test
    public void deveRetornarStatus400_QuandoCadastrarRestauranteSemCozinha(){
        given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(jsonRestauranteSemCozinha)
            .when()
                .post()
            .then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body("objects.userMessage", Matchers.hasItems(Matchers.equalTo("Cozinha do restaurante é obrigatória")));
    }

    @Test
    public void deveRetornarStatus400_QuandoCadastrarRestauranteComCozinhaInexistente(){
        given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(jsonRestauranteComCozinhaInexistente)
                .when()
                .post()
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body("title", Matchers.equalTo("Violação de regra de negócio"));
    }

    @Test
    public void deveRetornarRespostaEStatusCorretos_QuandoConsultarRestauranteExistente() {
        given()
                .pathParam("restauranteId", this.restauranteParaTeste.getId())
                .accept(ContentType.JSON)
                .when()
                .get("/{restauranteId}")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("nome", Matchers.equalTo(restauranteParaTeste.getNome()));
    }

    @Test
    public void deveRetornarStatus404_QuandoConsultarRestauranteInexistente() {
        given()
                .pathParam("restauranteId", RESTAURANTE_ID_INEXISTENTE)
                .accept(ContentType.JSON)
                .when()
                .get("/{restauranteId}")
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }

    @Test
    public void deveRetornarStatus400_QuandoCadastrarRestauranteComFreteGratisSemNomefreteGratis(){
        given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(jsonRestauranteComFreteGratisSemNomeFreteGratis)
            .when()
                .post()
            .then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body("objects.userMessage", Matchers.hasItem(Matchers.containsString("O campo nome deve conter a palavra Frete Grátis")));
    }


    private void prepararDados(){
        Cozinha cozinha = new Cozinha();
        cozinha.setNome("cozinha americana de teste");

        Cozinha cozinha2 = new Cozinha();
        cozinha2.setNome("cozinha portuguesa de teste");

        this.cozinhaParaTeste = cozinha;
        this.cozinhaParaTeste2 = cozinha2;
        cozinhaRepository.saveAll(Arrays.asList(cozinha, cozinha2));

        Restaurante restaurante1 = new Restaurante();
        restaurante1.setNome("Restaurante Comida-todo-dia para teste");
        restaurante1.setTaxaFrete(BigDecimal.valueOf(5.0D));
        restaurante1.setCozinha(this.cozinhaParaTeste);

        Restaurante restaurante2 = new Restaurante();
        restaurante2.setNome("Restaurante belém Frete Grátis para teste");
        restaurante2.setTaxaFrete(BigDecimal.ZERO);
        restaurante2.setCozinha(this.cozinhaParaTeste2);

        this.restauranteParaTeste = restauranteRepository.save(restaurante1);
        restauranteRepository.save(restaurante2);
    }

    private long quantidadeDeRestaurante(){
        return restauranteRepository.count();
    }

}
