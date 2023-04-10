package com.algaworks.algafood.core.openapi;

import com.algaworks.algafood.domain.model.*;
import com.algaworks.algafood.domain.model.dto.VendaDiaria;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Response;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Arrays;
import java.util.List;

@Configuration
@Import(BeanValidatorPluginsConfiguration.class)
public class SpringFoxConfig {

    @Bean
    public Docket apiDocket() {

        return new Docket(DocumentationType.OAS_30)
                .ignoredParameterTypes(ignoredParameterTypes())
                .select()
                //.apis(RequestHandlerSelectors.any())/*Todos os endpointds*/
                .apis(RequestHandlerSelectors.basePackage("com.algaworks.algafood.api"))
                .paths(PathSelectors.any())/*Caminho padrão*/
//                .paths(PathSelectors.ant("/restaurantes/*"))
                .build()
                .apiInfo(apiInfo())
                .tags(new Tag("Cidades", "Gerencia as cidades"))
                .useDefaultResponseMessages(false)
                .globalResponses(HttpMethod.GET, globalResponses());
    }

    private List<Response> globalResponses() {
        return Arrays.asList(
                new ResponseBuilder()
                        .code(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()))
                        .description("Erro interno do Servidor")
                        .build(),
                new ResponseBuilder()
                        .code(String.valueOf(HttpStatus.NOT_ACCEPTABLE.value()))
                        .description("Recurso não possui representação que pode ser aceita pelo consumidor")
                        .build()
        );
    }

    public ApiInfo apiInfo() {

        final Contact wenderson_gustavo =
                new Contact("Wenderson Gustavo",
                        "https://github.com/wgustavosantos",
                        "wgustavo.dev@gmail.com");

        return new ApiInfoBuilder()
                .title("AlgaFood API")
                .description("API aberta para clientes e restaurantes")
                .version("1")
                .contact(wenderson_gustavo)
                .build();

    }

    Class[] ignoredParameterTypes(){

        Class[] classes = {Cidade.class, Cozinha.class, Endereco.class, FormaPagamento.class, Produto.class, Restaurante.class, Pageable.class, Sort.class, Usuario.class, VendaDiaria.class, Page.class};

        return classes;
    }
}
