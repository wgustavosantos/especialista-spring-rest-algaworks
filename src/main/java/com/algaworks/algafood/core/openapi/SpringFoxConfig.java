package com.algaworks.algafood.core.openapi;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.core.openapi.model.PageableModelOpenApi;
import com.algaworks.algafood.domain.model.*;
import com.algaworks.algafood.domain.model.dto.VendaDiaria;
import com.fasterxml.classmate.TypeResolver;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.*;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Response;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.json.JacksonModuleRegistrar;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

@Configuration
@Import(BeanValidatorPluginsConfiguration.class)
public class SpringFoxConfig {

    TypeResolver typeResolver = new TypeResolver();

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
                .tags(tags()[0], tags())
                .useDefaultResponseMessages(false)
                .globalResponses(HttpMethod.GET, globalGetResponses())
                .globalResponses(HttpMethod.POST, globalPutResponses())
                .globalResponses(HttpMethod.PUT, globalPutResponses())
                .globalResponses(HttpMethod.DELETE, globalDeleteResponses())
                .additionalModels(typeResolver.resolve(Problem.class))
                .directModelSubstitute(Pageable.class, PageableModelOpenApi.class);
    }

    @Bean
    public JacksonModuleRegistrar springFoxJacksonConfig() {
        return objectMapper -> objectMapper.registerModule(new JavaTimeModule());
    }

    private List<Response> globalDeleteResponses() {
        return Arrays.asList(
                new ResponseBuilder()
                        .code(String.valueOf(HttpStatus.BAD_REQUEST.value()))
                        .description("Requisição inválida (erro do cliente)")
                        .representation(MediaType.APPLICATION_JSON)
                        .apply(getProblemaModelReference())
                        .build(),
                new ResponseBuilder()
                        .code(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()))
                        .description("Erro interno no servidor")
                        .representation(MediaType.APPLICATION_JSON)
                        .apply(getProblemaModelReference())
                        .build()
        );
    }

    private List<Response> globalPutResponses() {
        return Arrays.asList(
                new ResponseBuilder()
                        .code(String.valueOf(HttpStatus.BAD_REQUEST.value()))
                        .description("Requisição inválida (erro do cliente)")
                        .representation(MediaType.APPLICATION_JSON)
                        .apply(getProblemaModelReference())
                        .build(),
                new ResponseBuilder()
                        .code(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()))
                        .description("Erro interno no servidor")
                        .representation(MediaType.APPLICATION_JSON)
                        .apply(getProblemaModelReference())
                        .build(),
                new ResponseBuilder()
                        .code(String.valueOf(HttpStatus.NOT_ACCEPTABLE.value()))
                        .description("Recurso não possui representação que poderia ser aceita pelo consumidor")
                        .build(),
                new ResponseBuilder()
                        .code(String.valueOf(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value()))
                        .description("Requisição recusada porque o corpo está em um formato não suportado")
                        .representation(MediaType.APPLICATION_JSON)
                        .apply(getProblemaModelReference())
                        .build()
        );
    }

    private List<Response> globalGetResponses() {
        return Arrays.asList(
                new ResponseBuilder()
                        .code(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()))
                        .description("Erro interno do Servidor")
                        .representation(MediaType.APPLICATION_JSON)
                        .apply(getProblemaModelReference())
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

    private Tag[] tags() {
        return new Tag[] {
            new Tag("Cidades", "Gerencia as cidades"),
                    new Tag("Cozinhas", "Gerencia as cozinhas"),
                    new Tag("Grupos", "Gerencia os grupos"),
                    new Tag("Permissões do grupo", "Gerencia as permissões do grupo"),
                    new Tag("Pedidos", "Gerencia os pedidos"),
                    new Tag("Status dos pedidos", "Gerencia o status do pedido"),
                    new Tag("Formas de pagamento", "Gerencia as formas de pagamento"),
                    new Tag("Fotos dos produtos", "Gerencia as fotos do produto"),
                    new Tag("Restaurantes", "Gerencia os restaurantes"),
                    new Tag("Formas de pagamento do restaurante", "Gerencia a forma de pagamento do restaurante"),
                    new Tag("Produtos do restaurante", "Gerencia os produtos do restaurante"),
                    new Tag("Usuários do restaurante", "Gerencia os donos dos restaurantes"),
                    new Tag("Estados", "Gerencia os estados"),
                    new Tag("Estatísticas", "Gerencia as estatísticas"),
                    new Tag("Usuários", "Gerencia os usuários"),
                    new Tag("Grupo de usuários", "Gerencia os grupos de usuários")
        };
    }

    private Consumer<RepresentationBuilder> getProblemaModelReference() {
        return r -> r.model(m -> m.name("Problema")
                .referenceModel(ref -> ref.key(k -> k.qualifiedModelName(
                        q -> q.name("Problema").namespace("com.algaworks.algafood.api.exceptionhandler")))));
    }


    private Class[] ignoredParameterTypes() {

        return new Class[]{
                Cidade.class,
                Cozinha.class,
                Endereco.class,
                FormaPagamento.class,
                Produto.class,
                Restaurante.class,
                Usuario.class,
                VendaDiaria.class};
    }
}
