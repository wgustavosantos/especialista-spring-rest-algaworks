package com.algaworks.algafood.core.openapi;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.v1.model.dto.*;
import com.algaworks.algafood.api.v1.openapi.model.*;
import com.algaworks.algafood.api.v2.model.CidadeDTOV2;
import com.algaworks.algafood.api.v2.model.CozinhaDTOV2;
import com.algaworks.algafood.domain.model.*;
import com.algaworks.algafood.domain.model.dto.VendaDiaria;
import com.fasterxml.classmate.TypeResolver;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Links;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.context.request.ServletWebRequest;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.*;
import springfox.documentation.schema.AlternateTypeRules;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.json.JacksonModuleRegistrar;
import springfox.documentation.spring.web.plugins.Docket;

import java.io.File;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.URLStreamHandler;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

@Configuration
@Import(BeanValidatorPluginsConfiguration.class)
public class SpringFoxConfig {

    TypeResolver typeResolver = new TypeResolver();

    @Bean
    public Docket apiDocketV1() {

        return new Docket(DocumentationType.OAS_30)
                .groupName("V1")
                .ignoredParameterTypes(ignoredParameterTypes())
                .select()
                //.apis(RequestHandlerSelectors.any())/*Todos os endpointds*/
                .apis(RequestHandlerSelectors.basePackage("com.algaworks.algafood.api"))
                .paths(PathSelectors.ant("/v1/**"))/*Caminho padrão*/
//                .paths(PathSelectors.ant("/restaurantes/*"))
                .build()
                .apiInfo(apiInfoV1())
                .tags(tags()[0], tags())
                .useDefaultResponseMessages(false)
                .globalResponses(HttpMethod.GET, globalGetResponses())
                .globalResponses(HttpMethod.POST, globalPutResponses())
                .globalResponses(HttpMethod.PUT, globalPutResponses())
                .globalResponses(HttpMethod.DELETE, globalDeleteResponses())
                .additionalModels(typeResolver.resolve(Problem.class))
                .directModelSubstitute(Pageable.class, PageableModelOpenApi.class)
                .directModelSubstitute(Links.class, LinksModelOpenApi.class)
                .alternateTypeRules(AlternateTypeRules.newRule(
                        typeResolver.resolve(PagedModel.class, CozinhaDTO.class), CozinhasModelOpenApi.class)
                )
                .alternateTypeRules(AlternateTypeRules.newRule(
                        typeResolver.resolve(CollectionModel.class, CidadeDTO.class), CidadesModelOpenApi.class)
                )
                .alternateTypeRules(AlternateTypeRules.newRule(
                        typeResolver.resolve(CollectionModel.class, EstadoDTO.class),
                        EstadosModelOpenApi.class))
                .alternateTypeRules(AlternateTypeRules.newRule(
                        typeResolver.resolve(CollectionModel.class, FormaPagamentoDTO.class),
                        FormasPagamentoModelOpenApi.class))
                .alternateTypeRules(AlternateTypeRules.newRule(
                        typeResolver.resolve(CollectionModel.class, GrupoDTO.class),
                        GruposModelOpenApi.class))
                .alternateTypeRules(AlternateTypeRules.newRule(
                        typeResolver.resolve(CollectionModel.class, PermissaoDTO.class),
                        PermissoesModelOpenApi.class))
                .alternateTypeRules(AlternateTypeRules.newRule(
                        typeResolver.resolve(PagedModel.class, PedidoResumoDTO.class),
                        PedidosResumoModelOpenApi.class))
                .alternateTypeRules(AlternateTypeRules.newRule(
                        typeResolver.resolve(CollectionModel.class, ProdutoDTO.class),
                        ProdutosModelOpenApi.class))
                .alternateTypeRules(AlternateTypeRules.newRule(
                        typeResolver.resolve(CollectionModel.class, RestauranteBasicoDTO.class),
                        RestaurantesBasicoModelOpenApi.class))

                .alternateTypeRules(AlternateTypeRules.newRule(
                        typeResolver.resolve(CollectionModel.class, UsuarioDTO.class),
                        UsuariosModelOpenApi.class))
                .securityContexts(Arrays.asList(securityContext()))
                .securitySchemes(List.of(authenticationScheme()))
                .securityContexts(List.of(securityContext()));
    }

    @Bean
    public Docket apiDocketV2() {

        return new Docket(DocumentationType.OAS_30)
                .groupName("V2")
                .ignoredParameterTypes(ignoredParameterTypes())
                .select()
                //.apis(RequestHandlerSelectors.any())/*Todos os endpointds*/
                .apis(RequestHandlerSelectors.basePackage("com.algaworks.algafood.api"))
                .paths(PathSelectors.ant("/v2/**"))/*Caminho padrão*/
//                .paths(PathSelectors.ant("/restaurantes/*"))
                .build()
                .apiInfo(apiInfoV2())
                .useDefaultResponseMessages(false)
                .globalResponses(HttpMethod.GET, globalGetResponses())
                .globalResponses(HttpMethod.POST, globalPutResponses())
                .globalResponses(HttpMethod.PUT, globalPutResponses())
                .globalResponses(HttpMethod.DELETE, globalDeleteResponses())
                .additionalModels(typeResolver.resolve(Problem.class))
                .directModelSubstitute(Pageable.class, PageableModelOpenApi.class)
                .directModelSubstitute(Links.class, LinksModelOpenApi.class)
                .alternateTypeRules(AlternateTypeRules.newRule(
                        typeResolver.resolve(PagedModel.class, CozinhaDTOV2.class),
                        CozinhasModelV2OpenApi.class))
                .alternateTypeRules(AlternateTypeRules.newRule(
                        typeResolver.resolve(CollectionModel.class, CidadeDTOV2.class),
                        CidadesModelV2OpenApi.class))
                .tags(tagsV2()[0], tagsV2())
                .securityContexts(Arrays.asList(securityContext()))
                .securitySchemes(List.of(authenticationScheme()))
                .securityContexts(List.of(securityContext()));
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

    public ApiInfo apiInfoV1() {
        final Contact wenderson_gustavo =
                new Contact("Wenderson Gustavo",
                        "https://github.com/wgustavosantos",
                        "wgustavo.dev@gmail.com");

        return new ApiInfoBuilder()
                .title("AlgaFood API")
                .description("API aberta para clientes e restaurantes.<br>"
						+ "<strong>Essa versão da API está depreciada e deixará de existir a partir de 01/01/2021. "
						+ "Use a versão mais atual da API.")
                .version("1")
                .contact(wenderson_gustavo)
                .build();

    }

    public ApiInfo apiInfoV2() {
        final Contact wenderson_gustavo =
                new Contact("Wenderson Gustavo",
                        "https://github.com/wgustavosantos",
                        "wgustavo.dev@gmail.com");

        return new ApiInfoBuilder()
                .title("AlgaFood API")
                .description("API aberta para clientes e restaurantes")
                .version("2")
                .contact(wenderson_gustavo)
                .build();

    }

    private Tag[] tags() {
        return new Tag[]{
                new Tag("Cidades", "Gerencia as cidades"),
                new Tag("Cozinhas", "Gerencia as cozinhas"),
                new Tag("Grupos", "Gerencia os grupos de usuários"),
                new Tag("Permissões do Grupo", "Gerencia as permissões do grupo"),
                new Tag("Pedidos", "Gerencia os pedidos"),
                new Tag("Status", "Gerencia o status do pedido"),
                new Tag("Formas", "Gerencia as formas de pagamento"),
                new Tag("Fotos", "Gerencia as fotos do produto"),
                new Tag("Restaurantes", "Gerencia os restaurantes"),
                new Tag("Formas de Pagamento do Restaurante", "Gerencia a forma de pagamento do restaurante"),
                new Tag("Produtos do Restaurante", "Gerencia os produtos do restaurante"),
                new Tag("Usuarios do restaurante", "Gerencia os donos dos restaurantes"),
                new Tag("Estados", "Gerencia os estados"),
                new Tag("Estatisticas", "Gerencia as estatisticas"),
                new Tag("Usuários", "Gerencia os usuarios"),
                new Tag("Permissões", "Gerencia as permissões")

        };
    }
    private Tag[] tagsV2() {
        return new Tag[]{
                new Tag("Cidades", "Gerencia as cidades"),
                new Tag("Cozinhas", "Gerencia as cozinhas"),
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
                VendaDiaria.class,
                ServletWebRequest.class,
                URL.class, URI.class, URLStreamHandler.class, Resource.class,
                File.class, InputStream.class};
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(securityReference()).build();
    }

    private List<SecurityReference> securityReference() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return List.of(new SecurityReference("Authorization", authorizationScopes));
    }

    private HttpAuthenticationScheme authenticationScheme() {
        return HttpAuthenticationScheme.JWT_BEARER_BUILDER.name("Authorization").build();
    }
}
