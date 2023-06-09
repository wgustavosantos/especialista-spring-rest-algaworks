# Curso Especialista Spring Rest

## REST API desenvolvida com Spring Boot seguindo as melhoras práticas do mercado.

* Certificado de 280 horas - [https://app.algaworks.com/certs/RYMGMI88P0o](https://app.algaworks.com/certs/RYMGMI88P0)

* Projeto na Nuvem da AWS - http://54.160.160.146:8080/swagger-ui/index.html - Leia instruções de Uso [Instruções](#instruções)
  

## Tecnologias / Frameworks
 - SpringBoot 2.7 e 3.0
 - Spring Data JPA
 - Sprint Security
 - Maven
 - MySQL
 - Flyway
 - Java 11/17
 - OAuth2
 - Docker
 - Redis
 - OpenAPI/Swagger/SpringDoc
 - Squiggly
 - Testes de Integração
 - Spring Authorizarion Server (Spring Boot 3)
 - AWS (EC2, ECR, S3, ECS, System Manager, VPC, RDS)

## Conteúdo programático

## Índice
- [Especialista Spring Rest](#especialista-spring-rest)
	- [Introdução](#introdução)
	- [Capítulo 2 - Spring e Injeção de Dependências](#capítulo-2---spring-e-injeção-de-dependências)
	- [Capítulo 3 - Introdução ao JPA e Hibernate](#capítulo-3---introdução-ao-jpa-e-hibernate)
	- [Capítulo 4 - REST com Spring](#capítulo-4---rest-com-spring)
	- [Capítulo 5 - Spring Data JPA](#capítulo-5---spring-data-jpa)
	- [Capítulo 6 - JPA e Hibernate](#capítulo-6---jpa-e-hibernate)
	- [Capítulo 7 - Pool de conexões e Flyway](#capítulo-7---pool-de-conexões-e-flyway)
	- [Capítulo 8 - Tratamento e modelagem de erros da API](#capítulo-8---tratamento-e-modelagem-de-erros-da-api)
	- [Capítulo 9 - Validações com Bean Validation](#capítulo-9---validações-com-bean-validation)
	- [Capítulo 10 - Testes](#capítulo-10---testes)
	- [Capítulo 11 - Boas práticas e técnicas para APIs](#capítulo-11---boas-práticas-e-técnicas-para-apis)
	- [Capítulo 12 - Modelagem avançada e implementação da API](#capítulo-12---modelagem-avançada-e-implementação-da-api)
	- [Capítulo 13 - Modelagem de projeções, pesquisas e relatórios](#capítulo-13---modelagem-de-projeções-pesquisas-e-relatórios)
	- [Capítulo 14 - Upload e Download de arquivos](#capítulo-14---upload-e-download-de-arquivos)
	- [Capítulo 15 - E-mails transacionais e Domain Events](#capítulo-15---e-mails-transacionais-e-domain-events)
	- [Capítulo 16 - CORS e consumo da API com JavaScript e Java](#capítulo-16---cors-e-consumo-da-api-com-javascript-e-java)
	- [Capítulo 17 - Cache de HTTP](#capítulo-17---cache-de-http)
	- [Capítulo 18 - Documentação da API com OpenAPI, Swagger UI e SpringFox](#capítulo-18---documentação-da-api-com-openapi-swagger-ui-e-springfox)
	- [Capítulo 19 - Discoverability e HATEOAS: A Glória do REST](#capítulo-19---discoverability-e-hateoas-a-glória-do-rest)
	- [Capítulo 20 - Evoluindo e versionando a API](#capítulo-20---evoluindo-e-versionando-a-api)
	- [Capítulo 21 - Logging](#capítulo-21---logging)
	- [Capítulo 22 - Segurança com Spring Security e OAuth2](#capítulo-22---segurança-com-spring-security-e-oauth2)
	- [Capítulo 23 - OAuth2 avançado com JWT e controle de acesso](#capítulo-23---oauth2-avançado-com-jwt-e-controle-de-acesso)
	- [Capítulo 24 - Dockerizando a aplicação](#capítulo-24---dockerizando-a-aplicação)
	- [Capítulo 25 - Deploy em containers Docker na Amazon](#capítulo-25---deploy-em-containers-docker-na-amazon)
	- [Notas](#notas)


### Resumo por capítulo: [resumo](/minhas-anotacoes/)
- [x] Introdução
- [x] Spring e Injeção de Dependências
- [x] Introdução ao JPA e Hibernate
- [x] REST com Spring
- [x] Super poderes do Spring Data JPA
- [x] Explorando mais do JPA e Hibernate
- [x] Pool de conexões e Flyway
- [x] Tratamento e modelagem de erros da API
- [x] Validações com Bean Validation
- [x] Testes de integração
- [x] Boas práticas e técnicas para APIs
- [x] Modelagem avançada e implementação da API
- [x] Modelagem de projeções, pesquisas e relatórios
- [x] Upload e download de arquivos
- [x] E-mails transacionais e Domain Events
- [x] CORS e consumo da API com JavaScript e Java
- [x] Cache de HTTP
- [x] Documentação da API com OpenAPI, Swagger UI e SpringFox
- [x] Discoverability e HATEOAS A Glória do REST
- [x] Evoluindo e versionando a API
- [x] Logging
- [x] Segurança com Spring Security e OAuth2
- [x] OAuth2 avançado com JWT e controle de acesso
- [x] Deploy em produção
> atualizações:
- [x] Documentação da API com SpringDoc
- [x] Spring Authorization Server
- [x] Spring Boot 3

# Modelo de Domínio usando DDD

![Modelo de domínio](./minhas-anotacoes/images/diagrama-de-classes-de-dominio.jpg)

# EER Diagrama

![ER Diagram](./minhas-anotacoes/images/EER-diagram.png)

# Estrutura do Projeto

![Estrutura de Pastas](./minhas-anotacoes/images/modelo-de-pacotes.png)

## Documentação no Swagger usando Spring Doc

![Spring Doc](./minhas-anotacoes/images/Swagger.png)

## Instruções
#### Clone o projeto e execute na sua IDE

Altere o arquivo `application-development.properties` para utilizar o seu database
```
spring.datasource.username=seu-username
spring.datasource.password=sua-senha

Não é necessário configurar o database, pois caso não exista, o Spring vai criar dinamicamente

spring.datasource.url=jdbc:mysql://${DB_HOST:localhost}/algafood?createDatabaseIfNotExist=true&serverTimezone=UTC

```

O arquivo `db/data/afterMigrate.sql` possui a massa de dados de desenvolvimento, incluíndo clients do Spring Authorization Server, a cada reinicialização, todo o banco é resetado.

### Rodando o build do projeto

É possível rodar o build algafood-api-3.0.0.jar via linha de comando. Entre na raiz do projeto e localize o arquivo .jar substitua com as propriedades do seu banco de dados

```
java -jar -Dspring.datasource.username=dbusername -Dspring.datasource.password=sua-senha algafood-api-3.0.0.jar
```

![run-build](./minhas-anotacoes/images/run-build.png)

É necessário se autenticar na aplicação através do swagger para explorar todos os endpoints

```
 > user_id: joao.ger@algafood.com

 > user_secret: 123

NOTA
 A aplicação possui autenticação e autorização em alguns endpoints, o usuário acima possui autorização em todos os endpoints pois é um usuário administrador, mas se for um usuário comun, alguns endpoints estarão inacessíveis, como endpoints com verbos HTTP não seguros. Crie um usuário comum no endpoint de criação de usuário para logar com ele e acessar métodos idempotentes.
 ```

### POSTMAN
Para utilizar o postman, baixe a coleção utilizada, todos os endpoints da aplicação estão aqui [Coleção do Postman](./minhas-anotacoes/AlgaFood.postman_collection.json) basta importar o arquivo dentro do postman.

O fluxo de segurança é o Authorization Code com PKCE, utilize o endpoint `localhost:8080/oauth2/authorize?response_type=code&client_id=algafood-web&state=abc&redirect_uri=http://127.0.0.1:8080/authorized&scope=READ WRITE&code_challenge=bKE9UspwyIPg8LsQHkJaiehiTeUdstI5JZOvaoQRgJA&code_challenge_method=S256` em um navegador para o obter o code. Use as credenciais do user acima.

Em seguida, o Spring Authorization responde com um code na URL, por exemplo

```
http://127.0.0.1:8080/authorized?code=m701WvOCwmTDQTA_aB3vfvwdFYu4UGRq-52wasZYYY2BOgvwb9yYHzAQ28XWZ5FqpN6Kepdr9z1NZjRGTSuHuo43rad9K3PORTaFbIWltkKarNNhuK_1wOAuKNPB_X3t&state=abc
```

o code     `m701WvOCwmTDQTA_aB3vfvwdFYu4UGRq-52wasZYYY2BOgvwb9yYHzAQ28XWZ5FqpN6Kepdr9z1NZjRGTSuHuo43rad9K3PORTaFbIWltkKarNNhuK_1wOAuKNPB_X3t`
é adicionado no endpoint de solicitação do Access Token
![Access-token](./minhas-anotacoes/images/acess-token.png)

na chave "code" e o seu "value" é o code gerado. O Access Token retornado é um token JWT com seus SCOPES de acesso a determinados endpoints.

A coleção do POSTMAN está configurada para utilizar o access token automaticamente nos endpoints

![using-token](./minhas-anotacoes/images/using-access-token.png)

https://github.com/wgustavosantos/especialista-spring-rest-algaworks/assets/77124683/fcd87971-e88a-4623-ac81-9111d2c30ea2.mp4


## Projeto em produção

http://54.160.160.146:8080/swagger-ui/index.html

A listagem (GET) de:

* Cozinhas
* Cidades
* Estados
* Restaurantes
* Pedidos
* Produtos

e POST em:

* Criação de Usuário

Estão acessíveis sem a autenticação.

# Minhas Notas

## Introdução
`Spring`, além de um framework, é um ecossistema de projetos, que auxiliam na resolução de vários problemas do dia-a-dia de um programador java. Objetivando foco no desenvolvimento das regras de negócio e não no código de infraestrutura da aplicação. <br>
Pontos positivos da utilização do Spring são a simplicidade que fornece ao desenvolvimento, maturidade (várias empresas utilizam a algum tempo), modularidade (não é necessário baixar algo gigantesco para sua utilização), evolução constante, open source, possui comunidade grande e forte, popularidade alta na utilização pelas empresas, alta empregabilidade.

`Spring Framework` é apenas um dentre os projetos que o Spring possui. É o projeto que serve de base para os demais projetos Spring. Dentre suas principais funcionalidades estão Spring MVC, Core Technologies (que engloba a implementação de injeção de dependências).

`Spring Data` é outro projeto Spring que engloba vários outros projetos relacionados a acesso a dados, dentre estes está o `Spring Data JPA` ou Jakarta Persistence (especificação de persistência de dados para Java).

`Spring Boot` ajuda a criar projetos que se altoconfiguram seguindo convenções com uma visão opinativa, opinião que seus desenvolvedores acreditam serem as melhores. Reduzindo código boilerplate, trechos de código repetidos em diversos lugares com pouca ou nenhuma alteração.

## Capítulo 2 - Spring e Injeção de Dependências

[Injeção de dependências](https://blog.algaworks.com/injecao-de-dependencias-com-spring/) é um tipo de inversão de controle (ou Inversion of Control – IoC) que dá nome ao processo de prover instâncias de classes que um objeto precisa para funcionar.
Dessa forma é possível programar voltados para interfaces e, com isso, manter o baixo acoplamento entre as classes de um mesmo projeto.
Baixo acoplamento: componentes de um sistema são interconectados de modo que um dependa do outro o mínimo possível.

#### @Component
Notifica que a classe é um Bean gerenciado pelo Spring. Durante a varredura de componentes, o bootstrap da aplicação, o Spring Framework detecta automaticamente as classes anotadas com @Component e as instancia.
Por padrão, as instâncias de bean desta classe têm o mesmo nome que o nome da classe com uma inicial minúscula.
@Repository , @Service , @Configuration e @Controller são todas meta-anotações de @Component.

#### @Controller
`@Controller`também é um componente gerenciado pelo Spring, no caso, que responde requisições Web.
Essa anotação de nível de classe informa ao Spring Framework que essa classe serve como um controlador no Spring MVC.

#### @Retention
Usamos a anotação `@Retention` para dizer em que parte do ciclo de vida do programa nossa anotação se aplica.
Para fazer isso, precisamos configurar @Retention com uma das três políticas de retenção:

```java
RetentionPolicy.SOURCE - visível nem pelo compilador nem pelo tempo de execução
RetentionPolicy.CLASS - visível pelo compilador
RetentionPolicy.RUNTIME - visível pelo compilador e pelo tempo de execução
```

#### @Profile
Permite mapear beans para diferentes perfis. Considere um cenário básico: temos um bean que deve estar ativo apenas durante o desenvolvimento, mas não implantado na produção. Apenas Beans anotados com `@Profile("dev")` seriam instanciados caso esteja configurado o perfil de desenvolvimento em application.properties `spring.profiles.active=dev`.

#### @Bean
No Spring, os objetos que formam o backbone de seu aplicativo e que são gerenciados pelo container Spring IoC são chamados de beans. Um bean é um objeto instanciado, montado e gerenciado de outra forma por um container [Spring IoC](https://docs.spring.io/spring-framework/docs/3.2.x/spring-framework-reference/html/beans.html#beans) (Inversion of Control).

#### @Configuration
As classes de configuração podem conter métodos de definição de bean anotados com `@Bean`.

#### Ciclo de vida dos Beans
Inicialização, execução, destruição. Após construtor e importações é chamado o init (`@PostConstructor`).

#### Propriedades
- Substituindo propriedades via linha de comando, por exemplo:
`java -jar target/food-api.jar --server.port=8081`
- Substituindo variáveis por variável de ambiente:

	- `EXPORT SERVER_PORT=8082` (macOs)
	- `set SERVER_PORT=8081
		echo %SERVER_PORT%`(Windows)
- Ativando o Spring Profile por linha de comando:
`java -jar target/food-api.jar --spring.profiles.active=development`
- Ativando o Spring Profile por variável de ambiente:
	- `set SPRING_PROFILES_ACTIVE=production` (Windows)

#### @ConfigurationProperties
Essa anotação auxilia na configuração externalizada e facilita acesso às propriedades definidas nos arquivos de propriedades
O Spring vinculará automaticamente qualquer propriedade definida em nosso arquivo de propriedade que tenha o prefixo "notificador.email" e o mesmo nome de um dos campos na classe `NotificadorProperties`.

## Capítulo 3 - Introdução ao JPA e Hibernate

#### EntityManager
EntityManager é uma parte da Java Persistence API. Principalmente, ele implementa as interfaces de programação e regras de ciclo de vida definidas pela especificação JPA 2.0.

#### @Transactional
Podemos anotar um bean com @Transactional no nível da classe ou do método. A anotação também oferece suporte a outras configurações:

- o tipo de propagação da transação
- o nível de isolamento da transação
- um tempo limite para a operação envolvida pela transação
- um sinalizador readOnly - uma dica para o provedor de persistência de que a transação deve ser somente leitura
- as regras de reversão para a transação
Observe que - por padrão, a reversão acontece para o tempo de execução, exceções não verificadas apenas. A exceção verificada não dispara um rollback da transação.

#### Estados de uma entidade
Uma entidade pode assumir alguns estados com relação ao EntityManager. Os estados podem ser:

- Novo (new ou transient)
- Gerenciado (managed) - através dos métodos `persist`, `merge` ou buscar a entidade usando o EntityManager
- Removido (removed) - método `remove`
- Desanexado (detached) - método `detach`

Observar que não é possível um objeto em estado *transient* ir direto para o estado *removed*. Por isso a entidade foi buscada, para ficar em estado gerenciado, e só após isso é chamado o método *removed*.

![Diagrama de estados JPA](https://s3.amazonaws.com/algaworks-blog/wp-content/uploads/Diagrama-de-estados.png)

#### Padrão DDD - Domain-Driven Design
Design Orientado a Domínio representa um grupo de objetos de domínio que podem ser tratados como uma única unidade. Um exemplo pode ser um pedido e seus itens de pedido, eles serão objetos separados, mas é útil tratar o pedido (junto com seus itens de linha) como um único agregado.
<https://martinfowler.com/bliki/DDD_Aggregate.html>

Não se deve criar repositórios para entidades que não são agregate root, no exemplo o agregate root é o pedido.

#### Dialeto SQL
 Ao inserir relacionamento entre entidades é necessário inserir o dialeto para que o hibernate adicione a *foreign key* ao criar a coluna anotada com o relacionamento: `spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect`.

#### Propriedade nullable de @Column e @JoinColumn
Indica que ao criar tabela através da aplicação, os campos serão ou não anuláveis no banco de dados.

## Capítulo 4 - REST com Spring

#### Constraints do REST
 - Cliente-servidor
 - Stateless
 - Cache
 - Interface uniforme
 - Sistema em camadas
 - Código sob demanda

#### REST *vs* RESTful
REST é o estilo arquitetural que segue as constraints do REST, é a especificação. RESTful é uma API desenvolvida em conformidade com as constraints REST.

#### Recursos REST

Coisas expostas na web, possui importância para ser referenciado como uma coisa no software. Pode ser Singleton (representa uma única coisa) ou Collection.
Rest usa URIs(Uniform Resource Identifier) para identificar um recurso.

URI vs URL -  URL (Uniform Resource Locator) é um tipo de URI, especifica a localização do recurso (com protocolo por exemplo). Ex.: https://market.com.br/produtos. O plural é consenso de utilização.
URI deve referenciar à alguma coisa, um substantivo e não a um verbo ou ação, coisas possuem propriedades, verbos não.

#### @ResponseBody
A resposta do método deve ser enviada como resposta da requisição HTTP. `@RestController`engloba as anotations _@Controller_ e _@ResponseBody_.

#### Negociação de conteúdo
Para realização de content negotiation o cliente afirma qual formato de conteúdo ele aceita através do cabeçalho `Accept` com um MediaType, por exemplo application/json, application/xml, etc.

Ao adicionar dependência jackson a api passa a responder as requisições tanto com json quanto com xml.
```xml
<dependency>
	<groupId>com.fasterxml.jackson.dataformat</groupId>
	<artifactId>jackson-dataformat-xml</artifactId>
</dependency>
```
Ao definir o *MediaType* em um método específico, este passa a responder apenas o tipo configurado, respondendo com `406 Not Acceptable`. Também é possível definir qual método é chamado a partir do tipo de negociação de conteúdo.
`@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })`

 - Status HTTP para collection resource vazia: 200
 - Status HTTP para singleton resource inexistente: 404. Indica erro do cliente, por exemplo a url `cozinhas/9999` não retorna nenhum recurso, "não existe", já `cozinhas/2` existe.

#### SerialVersionUID
Ele é o recurso que usamos para dizer ao Java que um objeto serializado é compatível ou não com o .class utilizado para desserializar.
Dentro da [especificação](https://docs.oracle.com/javase/8/docs/platform/serialization/spec/class.html#a4100) existe uma nota recomendando que os desenvolvedores declarem a propriedade explicitamente para evitar problemas de desserialização.

## Capítulo 5 - Spring Data JPA
JPQL é a linguagem de consultas do JPA.

#### Keywords e Query Methods
Mecanismo de criação de queries por meio de palavras chave: Distinct, And, Or, Containing, Between, OrderBy, Null, After, etc.
[Documentação do Spring Data JPA:Keywords de querymethods](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods.query-creation)

Deve começar por "find", mas pode começar também por "read", "get", "Query" ou "stream".
 - Prefixos de query methods: *count, top, first, exists*, etc.

#### Criteria
`CriteriaQuery` é responsável por criar a estrutura de uma query, a composição das cláusulas.
`CriteriaBuilder` funciona como uma fábrica que contrói elementos para a construção da consulta.
Não vale a pena ser utilizada para consultas simples por ser mais verbosa e demandar mais esforço programático.

#### Specification
Filtros são as *specifications* de forma mais isolada, de modo que podem ser combinados.
Ponto negativo, possibilidade de usar o mesmo specification em vários lugares, caso seja necessária alguma alteração, a implementação seria ajustada em vários locais do código. Possível solução, isolar a combinação de specifications em um método na classe RepositoryImpl.

#### Customizando o repositório Base
Estendendo *JpaRepository*, utilizando *Generics*, importando *EntityManager* é possível criar um repositório base customizado, com os métodos do JPA e outros mais.
Necessário inserir a anotação na classe main:

`@EnableJpaRepositories(repositoryBaseClass = CustomJpaRepositoryImpl.class)`

## Capítulo 6 - JPA e Hibernate

#### @Embeddable
JPA fornece a  anotação @Embeddable para declarar que uma classe será integrada por outras entidades.

```java
@Embeddable // Entidade incorporável
public class Endereco {
```

#### @Embedded
A anotação JPA @Embedded é usada para embutir um tipo em outra entidade. Incorporando para uma única tabela de banco de dados, no exemplo em *tb_restaurante*.

```java
public class Restaurante {
	//...

	@Embedded
	private Endereco endereco;
```
#### @CreationTimestamp
Marca uma propriedade como o carimbo de data/hora ao salvar a entidade pela primeira vez. Anotação da implementação Hibernate.

#### @UpdateTimestamp
Marca uma propriedade como o carimbo de data/hora ao atualizar uma entidade. Anotação da implementação Hibernate. O próprio Hibrenate insere o valor do atibuto.

#### Eager Loading
Carregamento "ansioso", antecipado. Todas as associações terminados em _"ToOne"_ utilizam a estratégia `Eager Loading`. Ainda com @JsonIgnore o select de entidade mappeada é realizado, por exemplo ao buscar um restaurante específico, é feito select de cozinhas realizadas, por causa da estratégia EagerLoading. Ou seja, quando uma entidade é carregada no banco de dados as entidades associadas a ela também serão carregadas.
Não significa que a associação Eager será feita em um select apenas através _joins_, pode gerar vários selects. "Problema" "n+1", uma busca na verdade origina mais *n* consultas.

Para melhorar a performance e evitar consultas desnecessárias, é possível utilizar `@ManyToOne(fetch = FetchType.LAZY)` na propriedade do objeto ou `fetch join` apenas na query JPQL. A query pode gerar um produto cartesiano com mais tuplas por causa do join, porém o jpa faz a correspondência para evitar duplicações.

```
	@Query("from Restaurante r join fetch r.cozinha")
	List<Restaurante> findAll();
```

<b>Aula 6.14</b> - No cenário há _many_ Restaurantes para uma Cozinha. Ao realizar a busca de uma lista de restaurantes com a implemetação padrão do método `findAll()` pelo Spring Data JPA, cada restaurante gera uma busca pela Cozinha associada a ele. Um select é disparado para a tabela restaurantes e, havendo `n` restaurantes, dispara _n_ selects para a tabela cozinhas. Ocorrendo várias consultas `SELECTS` encadeadas.<br>
Nesse caso a consulta `SELECT * from Restaurante r join fetch r.cozinha` realiza apenas uma consulta _SELECT_, buscando as cozinhas associadas através de `inner join`. Atenção para possíveis registros duplicados devido aos relacionamentos.

#### Lazy Loading
Carregamento preguiçoso. Todas as associações terminados em _"ToMany"_ utilizam a estratégia `Lazy Loading`. Não realiza consultas de entidade associadas sem que algum método dessa entidade seja explicitamente chamado.
Sem o `@JsonIgnore` é preciso analisar pois a consulta da entidade provalvelmente será realizada carregando os abjetos associados, apenas não estaria serializando para json no retorno das requisições.

## Capítulo 7 - Pool de conexões e Flyway

`spring.jpa.hibernate.ddl-auto=update` cria uma coluna, porém alterações posteriores, como nullable, alteração do nome da coluna não são realizadas pois não garantem a manutenão do funcionamento da aplicação, logo `Schema generation` em produção não é uma boa prática. O próprio Hibernate [afirma](https://docs.jboss.org/hibernate/orm/5.2/userguide/html_single/Hibernate_User_Guide.html#schema-generation).

> Embora a geração automática de esquema seja muito útil para fins de teste e prototipagem, em um ambiente de produção, é muito mais flexível gerenciar o esquema usando scripts de migração incremental.

#### Flyway
Necessário inserir arquivos *sql* na pasta `resources/db/migration` com nome de versão, podendo ter pontos, underlines ou data hora, iniciando por V maiúsculo, por exemplo: V1.0.sql, V1_0.sql, V001__descricao.sql, V20212801.
É criada a tabela `flyway_schema_history` com auditoria de alterações.

As alterações são incrementais, da versão 1 para a 4, são necessários executar a v2, em seguida v3 e só após a v4. Deve ser evitado inserir inserts de dados de teste em *migrations* para evitar que estes valores entrem em produção.

Passos sugeridos, criar arquivo .sql dentro da pasta *db/migration* apenas com uma descrição, como "cria-tabela-usuario.sql". Em seguida inserir os comandos sql no arquivo, e então renomear inserindo a versão, com V maiúsculo e número da versão.
Verifica a inalteração de versão arquivo sql anteriormente adicionado pelo campo *checksum*.

Para alterações maiores em banco sugere-se criar um backup/dump do banco de dados em desenvolvimento. Em seguida escrever o script e executar para verificar o funcionamento, após isso restaurar o backup realizado anteriormente, e copiar os comandos *sql* para o arquivo migration dentro do projeto Spring.
Não se deve inserir dados de teste no banco através de arquivos migration sql.

##### Inserção de dados
Dados de teste podem ser inseridos no banco através do arquivo `afterMigrate.sql`. Ele será executado no callback do Flyway, após executar todas as migrações, na prática sempre que a aplicação for iniciada retornando a um estado conhecido. Podemos inserir este arquivo em pasta específica e indicar ao Flyway a leitura nessa pasta através de propriedade em *application*:

> spring.flyway.locations=classpath:db/migration,classpath:db/testdata

##### Falha
Quando uma migração **falha** ela fica armazenada na tabela de auditoria criada pelo Flyway chamada *flyway_schema_history*, com o valor 0 na coluna "success". Ainda que seja corrigida, o Flyway não permite a aplicação de uma versão já executada. Portanto deve ser observado até onde o arquivo foi executado, essas alterações devem ser desfeitas, o arquivo corrigido e o comportamento (em tese/desenvolvimento) seria deletar essa tupla em *flyway_schema_history*. Corrigir o arquivo sql migration e executar novamente o projeto.

Outra opção para remover a ultima tupla de *flyway_schema_history* seria executar na pasta do projeto o comando `./mvnw flyway:repair -Dflyway.configFiles=src/main/resources/flyway.properties`. Sendo criado o arquivo mencionado com os dados de conexão com o banco. 

*Se você copiar (da raiz do nosso projeto, ou qualquer projeto gerado pelo Spring Initializr) os arquivos mvnw, mvnw.cmd e a pasta .mvn você será capaz de executar os comandos maven de dentro do diretório onde colocou esses arquivos.*

##### Criando migração a partir de DDL gerado por schema generation
Em `application.properties` adicionamos duas propriedades. Elas serão temporárias, após a geração do arquivo serão deletadas.
 - *spring.jpa.properties.javax.persistence.schema-generation.scripts.action=create* para gerar um script sem que este seja executado automaticamente.
 - *spring.jpa.properties.javax.persistence.schema-generation.scripts.create-target=src/main/resources/ddl.sql* para informar o arquivo em que o script dll será armazenado

O comportamento esperado é que o script de geração não seja executado, e seja gerado o arquivo de criação das tabelas. Caso haja arquivo `src/main/resources/import.sql` seu conteúdo também será inserido em comandos no arquivo dll gerado.
Após a captura desse script, faça as devidas revisões e verificações, alteração de nome de foreign keys para um nome mais reconhecível, verificação de length de colunas e demais alterações desejadas.
Observação: `spring.jpa.properties.` utilizado para adicionar propriedades nativas para o provedor do JPA.

## Capítulo 8 - Tratamento e modelagem de erros da API

A anotação `@ResponseStatus(HttpStatus.NO_CONTENT)` acima do método no Controller indica o status http retornado caso não hajam exceções.
Exceção ainda retornada com atributos: "timestamp", "status", "error", "message", "trace".
As classes de domínio não devem ter contato com a camada que se relaciona com a web, os controllers têm essa responsabilidade, como por exemplo inserir código de status de resposta Http.

`ResponseStatusException` indicada para projetos menores, quando se deseja que uma única exceção retorne diferentes status Http, quando não tem possui tempo hábil para criação de exceções customizadas. Com esse objeto ainda não é possível customizar corpo da resposta de exceção, apenas status code e mensagem.
Uma exceção personalizada pode estender `ResponseStatusException`, como vantagem têm-se a possibilidade de enviar diferentes códigos de status Http por quem lança a exception, temos uma exceção que pode retornar diversos status Http. Desvantagem é caso a exceção esteja dentro de uma classe serviço, então o código http seria definido em um pacote que deve conter regras de negócio.

Importante ter um padrão de objeto de resposta de exceções em uma API.

Exemplo de especificações que tentam padronizar o formato de resposta com os detalhes do erro como: jsonapi.org, vnd.error, [Problem Details for Http APIs](https://tools.ietf.org/html/rfc7807) (RFC 7807, IETF). Neste projeto será usado a última especificaçao:

```json
{
    "status": 400,
    "type": "https://foodkamila.combr/recurso-em-uso.",
    "title": "Recurso em uso",
    "detail": "Não foi possível excluir a cozinha de código 8, porque está em uso",
    "instance": "/cozinhas/8/erros/98204983"
}]
```

Propriedade *status* deve ter o mesmo código de status da requisição original gerado pela API. *Type* é uma URI que identifica o tipo do problema, pode ou não ser uma URL acessível pela internet, sendo um a URI que descreve um problema único e como resolvê-lo. *Title* descreve um texto legível para humanos que descreve o erro, deve ser o mesmo para mesmo código de statusHttp de erro.
*Detail* é uma descrição mais detalhada do erro específico legível para humanos. *Instance* é um apropriedade opcional onde conta uma URI exata específica do erro retornado.`

##### Podemos expor detalhes internos do erro, como a stacktrace?
Pode-se adicionar a pilha de erros à resposta por opção de escolha. Porém em APIs públicas, onde terceiros podem acessar, não é recomendado retornar detalhes internos da API, pois essa informação não é útil para o cliente e pode expor dados sensíveis da aplicação para terceiros.


##### Todas as respostas com erros precisam ter um corpo descrevendo o problema?
Não, pois há problemas já bem documentados pelo protocolo Http. Interessante retornar o *status* e *title* pelo menos.

##### Benefícios
Formato único para descrever erros, de forma que o consumidor possa entender o que aconteceu e tomar uma decisão. Facilita a construção e manutenção da API. Caso o cliente utilize mais de uma API, se elas utilizaerem o mesmo padrão de resposta de erro não é necessário tratamentos específicos para compreensão dessa resposta.

Deve-se analisar se a excepção que ocorreu é um subtipo de outra exceção. Caso positivo, deve-se tratar a exceção mais genérica.

## Capítulo 9 - Validações com Bean Validation

A partir da versão 2.3.0 do Spring o Bean Validation não é adicionado automaticamente como dependência do pacote spring-boot-starter-web. Nesse caso é necessário adicionar a dependência do spring-boot-starter-validation no projeto de forma explícita:

```xml
<dependency> 
    <groupId>org.springframework.boot</groupId> 
    <artifactId>spring-boot-starter-validation</artifactId> 
</dependency>
```
Apenas inserindo critério de validação na entidade, está é implementada no repository. Para validar no controller deve ser inserido `@Valid` no objeto recebido no controller.

Lista e documentação de [validações](https://docs.jboss.org/hibernate/stable/validator/reference/en-US/html_single/#section-builtin-constraints).
Por padrão uma entidade não valida outra entidade inserida nela em cascata para isso deve-se colocar a anotação `@Valid` no atributo.
Por padrão a validação utiliza o group `javax.validation.Groups.Default.class`.
Anotação `@ConvertGroup` informa pra entidade modelo que ao validar o atributo anotado, deve-se utilizar o group utilizado. Convertendo de group `Default` para um grupo customizado apenas naquele atributo.

##### Resource Bundle do Bean Validation
Pode-se observar nas dependências do projeto o jar `hibernate-validator`, e o resource bundle `ValidationMessages.properties`. Esse arquivo contém as mensagens relacionadas à validação.
Podemos criar um arquivo de mesmo nome na pasta `resource`do projeto, que será responsável no nosso projeto por resolver as mensagens do *Bean Validator*.

```java
	// Restaurante.class
	@NotNull
	@PositiveOrZero(message = "{TaxaFrete.invalida}")
	@Column(name = "taxa_frete", nullable = false)
	private BigDecimal taxaFrete;
	
	// ValidationMessages.properties
	TaxaFrete.invalida = Taxa frete está inválida. Informe um valor positivo. 
```
Após a resolução da mensagem pelo Bean Validator o Spring percorre seu resource bundle `messages.properties` e sobrescreve a mensagem caso esteja presente no bundle do Spring.

```java
	// messages.properties
	PositiveOrZero={0} deve ser um valor maior ou igual a zero
```
Por possuir precedência, recomenda-se a utilização do arquivo *messages.properties*.

##### Placeholder em Bundle MessagesProperties
Para o placeholder de índice 0  espera-se o valor da propriedade. Em caso de validação de classe não se tem esse dado, pois não se trata de uma propriedade específica.
Os placeholders são atribuídos aos índices em sua ordem alfabética.

##### Validando objeto Map
Ao receber um objeto do tipo `Map` o controlador não é capaz de validá-lo. Para validá-lo é necessário convertê-lo em uma classe anotada com validações, em seguida validá-lo programaticamente utilizando `SmartValidator.validate`.

```java
	BeanPropertyBindingResult bindingResult = new BeanPropertyBindingResult(objetoASerValidado, "nomeDaEntidade");
	org.springframework.validation.SmartValidator.validate(objetoASerValidado, bindingResult);
```

## Capítulo 10 - Testes

### Testes de Integração
Teste de integração deve validar uma única funcionalidade.
Deve possuir três partes: cenário, ação, validação.
É possível executar apenas um método de teste, clicando com o lado direito do mouse, "Run as", "JUnit Test".

```java
	// Exemplo de Teste de Integração
	@Test(expected = ConstraintViolationException.class)
	public void deveFalhar_QuandoCadastroCozinhaSemNome() {
		Cozinha novaCozinha = new Cozinha();
		novaCozinha.setNome(null);
		
		novaCozinha = cadastroCozinhaService.salvar(novaCozinha);
	}
```

##### Nomes de testes
Ideal escolher um padrão para nomes de métodos de teste, e utilizar esse padrão em todo o projeto.
É aceitável utilização de padrão under_scores em métodos de teste (separação das palavras por underlines), por exemplo `cadastro_cozinha_com_sucesso()`.
Utilização de palavras chave, por exemplo, `given` (dado que), `when` (quando), `than` (então), por exemplo: `givenJaExisteCozinhaChinesa_WhenCadastroCozinhaChinesa_ThenDeveFalhar()`.
Outro exemplo de padrão: `should` (deve acontecer), `when` (quando). Por exemplo: `deveAtribuirId_QuandoCadastrarCozinhaComDadosCorretos()`
Outros exemplos:
 - `whenCadastroCozinhaComDadosCorretos_ThenDeveAtribuirId()`

##### Executando testes por linha de comando
Para executar os testes em um projeto maven execute o comando a seguir na pasta do projeto:

> ./mvnw test

Ao gerar um build do projeto maven com `mvn clean package` os testes também são executados, no caso de falha o build não é gerado, porém torna mais longo o tempo para geração do build.

##### Maven Failsafe Plugin
Com o plugin `maven-failsafe-plugin` é possível gerar o build sem executar os teses de integração. Dessa maneira os testes serão executados apenas pelo comando `./mvnw verify`. `./mvnw test`também não executará os teste de integração. Para identificação pelo plugin as classes de teste de integração devem terminar com IT (Integration Teste).

```java
	<plugin>
		<artifactId>maven-failsafe-plugin</artifactId>
	</plugin>
```

### Testes de API
Testes de API fazem uma chamada real para uma requisição no serviço rest e executa todo o fluxo.
Para execução do teste não é preciso executar o projeto, a própria classe de teste ao ser executada levanta um container com a aplicação somente para os testes. Através da anotação na classe de teste:
`@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)`
Executado a classe com JUnit.

```java
	// Exemplo de Teste de API
	@Test
	public void deveRetornarStatus200_QuandoConsultarCozinhas() {
		RestAssured.given() // Dado que
			.accept(ContentType.JSON)
		.when() // Quando
			.get()
		.then() // Então
			.statusCode(HttpStatus.OK.value());
	}
```

#### Hamcrest Matchers
É uma [biblioteca](http://hamcrest.org/JavaHamcrest/javadoc/1.3/org/hamcrest/Matchers.html) para escrever expressões com regras de correspondência entre objetos. Página: http://hamcrest.org/JavaHamcrest/tutorial.

#### Ordem de execução dos testes
O JUnit não garante a execução dos métodos de teste na ordem em que estão dispostos no código. Desse modo entende-se que os métodos de teste não devem ser condicionados a execução anterior de outro método de teste.
Um método de teste não pode depender da execução ou não execução de outro método. *Testes devem ser independentes*.

#### Criando um banco somente para testes e usando @TestPropertySource
Para isto, é criado um arquivo chamado "aplication-test.properties" em "src/test/resources". Foi retirado o trecho "classpath:db/testdata" pois não é desejado que o afterMigrate da aplicação principal seja utilizada para o banco apenas de teste.
Através da anotação `@TestPropertySource` na classe de teste indicamos que as propriedades em 'applicaiton-test.properties' sobrescreverão as mesmas propriedades em "src/main/resources/application.properties".
`@TestPropertySource("application-test.properties")`. 

Ao escolher o que se deve testar o desenvolvedor só deve escolher o que agrega valor. Pode ser focado na interface/contrato da API, como o código de status ou nome de uma propriedade de resposta que não deve ser alterado. Pode-se testar funcionalidades específicas, verificando se a ação esperada é realmente executada. Lembre-se de testar o caminho infeliz.

## Capítulo 11 - Boas práticas e técnicas para APIs

#### Escopo das transações
É aconselhado anotar métodos com dados que serão commitados com `@Transactional` de "org.springframework.transaction.annotation.Transactiona", métodos save, delete por exemplo.
Caso haja mais de uma ação em método que manipule valores em banco de dados, é necessário anotar o método com @Transactional para evitar inconsistências no banco. Sem essa anotação caso uma dessas ações falhe a outra seria executada, pois estariam em transações diferentes, provocando inconsistência de dados.

Ao ser chamado o entity manager, e ser executado método do SimpleJpaRepository anotado com @Transactional, a transação iniciada anteriormente em uma classe `Service` é mantida. A Transactional no SimpleJpaRepository é presente para o caso de o método em Service não ter aberto nenhuma transação.

#### @JsonIgnoreProperties

Ao executar a chamada rest para atualização de um Restaurante que contém um objeto Cozinha como atributo é possível enviar atributos da cozinha. É interessante que a Api retorne erro, pois o nome da cozinha, por exemplo, não será atualizado nesse método, portanto não deve ser recebido e apenas ignorado.

```json
{
    "id": 1,
    "nome": "Thai Gourmet",
    "taxaFrete": 15.00,
    "cozinha": {
        "id": 1,
        "nome": "Thai Gourmet alterado" <- Propriedade de cozinha que não será atualizada
    }
}
```
Para não aceitar a propriedade "nome" na desserialização (json -> java object) adiciona-se a anotação `JsonIgnoreProperties` no atributo Cozinha da classe Restaurante. A fim de retornar ainda o nome da Cozinha no get de Restaurantes, indicaremos a permissão para serialização através do atributo `allowGetters = true`. Ao enviar um Restaurante com o atributo "nome" de cozinha será retornado erro 400 a partir desta alteração.

```java
	@JsonIgnoreProperties(value = "nome", allowGetters = true) // Ao desserializar (json to java object) um Restaurante ignora o nome de Cozinha
	...
	private Cozinha cozinha;
```
#### Jackson Mixin
É uma classe que tem membros da classe original que possuem anotações do Jackson.
Dessa forma anotações do `com.fasterxml.jackson` relacionados a API e não relacionados ao domínio ficam descritas não mais na classe de modelo, mas na classe *mixin*.
Através do método `setMixInAnnotation` em classe que estende `SimpleModule` podemos relacionar a classe mixin com a classe com o mapeamento da entidade.

#### Data e Hora em REST APIs
Fuso horário possui relação ao Merifdiano de Grenwish. Regiões à direita (leste) possuem fuso horário com diferença de horas a mais e regiões à esquerda possuem diferença de 
horas a menos.
GMT é o um dos fusos horários seguidos por alguns países, sem nenhum offset (deslocamento). `O UTC é o padrão de referência universal` e não o GMT, apesar de compartilharem o mesmo horário base. Por exemplo, horário no Brasil está 3 horas a menos do UTC: BRT = UTC - 3.

##### Boas páticas para trabalhar com datas e horas com Rest APIs
**1.** Use ISO-8601 para formatar data/hora. Por exemplo:
 - YYYY-MM-DDThh:mm:ss p.ex. "2007-07-16T19:20:28" 
 - YYYY-MM-DDThh:mm:ssTZD, insere offset do UTC, p.ex. "2007-07-16T19:20:28-3:00"
 - YYYY-MM-DDThh:mm:ss, sem nenhum offset, como no fuso geográfico GMT, p.ex. "2007-07-16T19:20:28Z"

**2.** Aceite qualquer fuso horário: A API deve converter para a data/hora que estiver utilizando. </br>
**3.** Armazene em UTC: sem nenhum offset, pois evita problemas de mudança de fuso horário e erros na apresentação de dados. </br>
**4.** Retorne em UTC: para que os consumidores recebam em UTC e possam converter para o padrão da aplicação consumidora. </br>
**5.** Não inclua o horário se não for necessário: em um campo onde só é necessária a data é possível que, ao adicionar a diferença de horas do fuso horário, o dia seja acrescido.</br>


Inserindo na aplicação um TimeZone específico como `spring.datasource.url=jdbc:mysql://localhost:3306/food?createDatabaseIfNotExist=true&serverTimezone=America/Sao_Paulo`, é utilizado o timeZone padrão do sistema operacional. Não é a melhor implementação, pois dessa maneira, a hora é salva utilizando o Timezone do SO do servidor, as datas retornadas também utilizam o SO do servidor representando o mesmo dado, porém é desconhecido o Timezone para o consumidor da aplicação. Caso o consumidor esteja em outro Timezone não seria possível identificar a relação do dado recebido com o Timezone UTF.

##### Salvando e retornando dados em UTC

Em application.properties a propriedade `serverTimezone=UTC` configura o driver JDBC do MySql para usar o UTC. Indica que os horários no banco de dados estão em UTC, este é o comportamento desejado.

A classe`LocalDateTime` não possui o offset do timeZone em relação ao UTC. A classe`java.time.OffsetDateTime` possui o offset relacionado ao UTC e será utilizada, retornando ao consumidor da API o formato:
>  "dataCadastro": "2021-03-07T11:57:00-03:00"

A utilizacao em arquivos sql de `current_timestamp` indica a utilização da data/hora do sistema operacional. É importante salvar os dados on mesmo timezone configurado no driver JDBC, para que os dados sejam salvos e exibidos em relação ao mesmo timezone não havendo equivocos de conversão data/hora. Assim sendo, nos sqls de inserção deve ser utilizado `utc_timestamp`. Dessa maneira o banco contém o dado:
`2021-03-07 15:10:32` e a resposta da requisição retorna `2021-03-07T12:10:32-03:00` calculando 15hs (UTC = 0) menos 3 horas de diferença de Timezone em relação ao UTF.

Para retornar em UTF Timezone zero iremos configurar o Timezone na aplicação. Desse modo o cliente receberá o dado `2021-03-07T15:10:32Z`, onde "Z" é igual a "+00:00". O consumidor fica responsável por converter a data/hora para o Timezone desejado. Calculando o dado recebido com o Timezone de utilização do cliente.

```java
	public class FoodApiApplication {
		public static void main(String[] args) {
			TimeZone.setDefault(TimeZone.getTimeZone("UTC"));	
			SpringApplication.run(FoodApiApplication.class, args);
		}
	}
```

#### Domain Model do Representation Model com o padrão DTO
Quando o controllador retorna uma entidade, uma classe Domain Module está sendo exposta pela API, e sendo utilizada tanto como Domain Module como Representation Module, o que pode provocar problemas.</br>
Casos de exemplo: uma propriedade que deve ser exposta em um endpoint e ignorada/ocultada em outro; necessidade de ignorar completamente propriedades no Representation Module (como o Mixin está fazendo); ao alterar uma entidade automaticamente a representação também é alterada podendo quebrar nas aplicações consumidoras, congelando suas entidades; necessidade de retornar um objeto com propriedades diferentes do Domain Module. </br>
Para evitar esses problemas é indicado isolar o Domain Module do Representation Module. Um padrão para este caso é o **DTO*: "Data Access Object", agrupando um conjunto de propriedades de uma ou mais entidades, em uma classe para transferência de dados apenas com as propriedades necessárias, definindo as propriedades que se desejam serializar. A nomenclatura não é definida, aqui será utilizada a terminação "model".

Não existe resposta definitiva para momento de utilização, porém para projetos reais é recomendada a utilização desse padrão por oferecer maior segurança, flexibilidade, liberdade de desenvolver o modelo de domínio sobretudo se já está em produção. </br>
Nem sempre um objeto retornado pela API é aceito para entrada, por isso existe a possibilidade de criar um modelo apenas para entrada de dados e outro para saída, podendo haver representações diferentes para um mesmo recurso.

Os `mixins` deixam de ser necessários e devem ser removidos.

Sobre as `validações` elas serão inseridas na classe DTO mapeada como input de dados. É possível que elas estejam apenas nas classes DTO de input de dados e não nas entidades, porém assumindo que estas entidades sempre serão salvas e atualizadas passando pelo controller que recebe o DTO de input. No caso de haver outras interfaces, services, salvando ou cadastrando é interessante deixar a anotação de validação na entidade. Pois no momento de persistência o JPA verifica o BeanValidation, porém com um erro menos específico.

#### ModelMapper
Auxilia no parseamento de atributos entre objetos. Exemplo de utilização, retornando objeto RestauranteModel a partir de instância de objeto Rrestaurante: `modelMapper.map(restaurante,  RestauranteModel.class);`.
Para injetar [Model Mapper](http://modelmapper.org/) nas demais entidades, utilizando uma instancia singleton no contexto do Spring Boot podemos fazer:

```java
	@Configuration
	public class ModelMapperConfig {
		@Bean
		public ModelMapper modelMapper() {
			var modelMapper = new ModelMapper();		
			return modelMapper;
		}
		
	}
```

**Algumas regras para match:**
 - Estratégia padrão, separa o nome das propriedades em pedaços por nome de propriedades, nomes separados no padrão camelCase, são os tokens. Correspondência por semelhança de tokens de atributos, em qualquer ordem.
 - Todos os tokens da propriedades de destino devem corresponder com tokens de origem.
 - Nome da propriedade de origem deve ter pelo menos um token de destino. Por exemplo, em Cozinha.nome, nome de origem é "nome", tokens: cozinha, nome. Nome da propriedade de destino em RestauranteModel sendo a String "nomeCozinha" têm-se os tokens: nome, cozinha. Considera-se correspondentes pelo ModelMapper.

#### Snake case
Se quiseres retornar os dados para o cliente em snake case sem alterar a nomenclatura das propriedades, deixando nas classes modelo a nomenclatura camel case, pode-se adicionar ao application.properties:
`spring.jackson.property-naming-strategy=SNAKE_CASE`. O mais comum é lower camel case.

#### Flush
Por padrão, o JPA normalmente não grava alterações no banco de dados até que a transação seja completada. Isso normalmente é importante para evitar acessos ao banco de dados até ser realmente necessário. Então quando chamamos o `método flush` todas as alterações no banco de dados (de qualquer entidade) são executadas antes da transação ser finalizada.</br>
O flush no repository irá executar todas as ações pendentes, dentro da transação, para que o commit não seja efetuado ao final do bloco catch e a exceção não seja capturada.
Vai sincronizar os dados que estão no entityManager com a base de dados. E apenas no final da transação o commit será de fato efetuado.

Caso haja uma operação pendente e seja executada outra alteração em banco de dados, o gerenciamento do Spring é inteligente o suficiente para executar no banco (fazer o commit) da primeira operação para só posteriormente executar a segunda operação. Exemplo de duas operações:

```java
	cozinhaRepository.deleteById(idCozinha);
	cozinhaRepository.findAll();
```

## Capítulo 12 - Modelagem avançada e implementação da API

#### Modelagem de Sub-recursos
Qualquer informação que pode ser identificada por um nome é um recurso. Exemplos de modelagem:</br> 
Sub-recurso:
 - restaurante/1 (recurso) - Retornando informações do restaurante com granularidade fina, sem dados de outras entidades. Ou com outras entidades aninhadas, granularidade grossa.
 - restaurante/1/endereco (sub-recurso) - Retorna apenas dados do endereço de um restaurante
 
Sub-recursos de coleções:
 - /restaurante/1 (recurso) - Retorna Restaurante com todos os seus produtos
 - /produtor?restaurante=1 - Retorna apenas produtos do restaurante especificado
 - /restaurantes/1/produtos (sub-recurso de coleção) - Os consumidores da api só podem manipular produtos a partir do Restaurante? Produto só pode existir se Restaurante existir? Nesse caso é retornado array de produtos do restaurante
 - /restaurantes/1/produtos/100 (recurso únido dentro do sub-recurso de coleção)

#### Chatty vs Chunk API
Chatty ("tagarela") é uma API de granularidade fina, onde os consumidores precisam fazer várias chamadas para executar uma única operação. 
Chunky ("pedaço grande") API é aquela em que uma operação é feita em uma única requisição.</br>
Caso haja a possibilidade de o recurso ficar em estado inconsistente, é mais interessante criar o recurso com granularidade grossa. Caso seja algo mais específico da API, sem que haja a possibilidade de inconsistência é possível optar por granularidade fina.

##### Ações não-CRUD como recursos
 - Quando se nomeia recursos devem ser utilizados substantivos e não verbos. Ex.: restaurantes/1/ativo [put]
 - É indicada a utilização de hífen para separar palavras. Ex.: restaurantes/1/alteracoes-status [get] Retornando uma lista de eventos de alteração de status, histórico.
 - Ainda que não exista uma entidade no domain model é possível que exista um recurso com a nomenclatura. Ex.: compras/1/pagamento [post] Admitindo que não exista a entidade pagamento, e nesse recurso seria enviado dados como numero do cartão de crédito, nome do titular, vencimento. Assim utiliza-se de conceitos abstratos, coisificando um objeto pagamento.
 - É possível a criação de um recurso para um processo de negócio que não representa uma entidade nem persistência em banco. Ex.: recurso que envia notificações para clientes sem persistência, /notificações-restaurantes [post]

##### Um pouco mais sobre JPA
Um objeto alterado fora da transação, uma entidade gerenciada pelo JPA, alterada em um método não transacionado (sem @Transactional) é sincronizado com o banco de dados, se posteriormente o processo participa de uma transação. Ou seja, ao ser executado outro método no mesmo processo transacionado, ainda que o objeto referido não seja alterado nele ou enviado para ele, o objeto gerenciado será persistido em banco ao final da transação quando o commit for realizado.

Caso ocorra uma exception no método transacionado o spring realiza Rollback do comando no banco de dados.

#### Tipo Enum em Coluna
Necessária anotação `@Enumerated(EnumType.STRING)` na porpriedade pra evitar erro de des/serialização ao buscar dado do banco.

#### Entidades ricas x Entidades anêmicas
 - Entidades ricas - entidades que contém regras de negócio.
 - Entidades anêmicas - servem apenas para representar dados, propriedades, getters e setters.

#### UUID
Para evitar a exposição de IDs, IDs sequenciais, de determinadas entidades, por ser uma informação desnecessária para o consumidor, evitando expor a quantidade de pedidos 
existente na aplicação, ou mesmo evitando acesso a informações enviando IDs intuidos. Para isso uma possibilidade é a utilizacao de um código UUID para identificacao do objeto.

## Capítulo 13 - Modelagem de projeções, pesquisas e relatórios

#### @JsonView
Jackson Json View é uma ferramenta para customizar a serialização/desserialização de objetos, através de uma projeção resumida do objeto.
Assim, cria-se uma interface para referência da representação:

```java
public interface RestauranteView {
	public interface Resumo {}
}
```
Em seguida anotamos as propriedades do objeto retornado que devem ser serializadas nesse JsonView através de `@JsonView(RestauranteView.Resumo.class)`. Em seguida utiliza-se com a mesma anotação o método rest no controller, para afirmar que ele retornará a projeção JsonView criada.

Em comparativo com o padrão DTO, no dto há uma flexibilidade maior para alteração de serialização, utilizando muitos JsonView pode deixar o código mais poluído, sobretudo as classes model.

##### MappingJacksonValue
Empacotador que recebe uma interface para projeção do objeto, é um wrapper:
`restaurantesWrapper.setSerializationView(RestauranteView.ApenasNome.class);`

#### @JsonFilter
Opção de filtrar propriedades dos objetos e retornar apenas as selecionadas. O cliente poderia selecionar os campos que deseja receber, como em `PedidoController.listar()`. Fornece um controle fino do objeto de retorno pelo consumidor da API. Interessante para o caso de os consumidores da API precisarem de muita flexibilidade. Atenção para a aplicação de Over Engeneering, ou seja, tentar otimizar problemas que nem mesmo irão existir. Opiniões contrárias afirmam que esse controle fino deveria pertencer apenas ao servidor, uma Rest API não seria uma ferramente apenas de acesso de dados via HTTP.

#### Squiggly
Biblioteca que permite interceptar as requisições, a fim de receber o parametro "fields" contendo o nome das propriedades que o consumidor deseja receber do objeto retornado. O nome do parâmetro pode ser customizado, foi utilizado "campos". Por exemplo, buscará os campos codigo, valorTotal, qualquer campo que inicie com "sub", todos os atributos de restaurante exceto o "id", apenas o id e nome do cliente:

> http://localhost:8080/pedidos?campos=codigo,valorTotal,sub*,restaurante[-id],cliente[id,nome]

Risco: é uma biblioteca pequena, com manutenção desconhecida.

#### Modelando pesquisas complexas
* Receber parâmetros de URL no recurso de coleção
* Considerar a própria pesquisa como recurso. Post passando objeto com as propriedades do filtro. Quebra a constrant de cash do Rest, não considerada restfull por alguns. Segundo  curso deve ser evitado.
* Considerar a própria pesquisa como recurso (de verdade). POST para criação de objeto de filtro, respondendo com 201 Created com id do filtro. Posteriormente usa id do filtro para pesquisa do resultado: GET pedidos/filtros/<id>
	* Retornando resultado da pesquisa por parametro de url: GET /pedidos?filtro=<id>

#### Customizando serializador para Page
Estendendo `JsonSerializer<Page<?>>` e anotando com `@JsonComponent` é possível alterar os atributos do objetos Page retornados pela API, pelo fato de muitos deles não serem necessários ou utilizados. Sobrescrevendo o método "serialize" é possível definir cada atributo do objeto Page.

```
@Override
	public void serialize(Page<?> page, JsonGenerator gen, SerializerProvider serializers) throws IOException {
		gen.writeStartObject(); // Inicia objeto Json
		gen.writeObjectField("content", page.getContent()); // Cria atributo com valor
		// ... outros atributos
		gen.writeEndObject(); // Finaliza objeto Json
	}
```

#### Data/Hora GMT/UTC +0 em relatórios
Como a data/hora está sendo persistida no timezone GMT/UTC +0, caso um pedido esteja persistido no dia 06 de maio de 2021 às
2 horas da madrugada em GMT 0. No horário de Brasília na verdade esse pedido aconteceu no dia 05/05/2021 às 23hs. Isso pode provocar 
distorções em relatórios de dados.
Por esse motivo em EstatisticasController há requisições recebendo o timeOffset, dando essa opção de seleção para o consumidor da Api,
convertendo os dados da busca para o timezone recebido (função _convert_tz_ do mysql, por exemplo).

#### JasperReports
Crie um novo projeto, com o lado direito do mouse crie um novo Jasper Report.
Para fins de teste é possível criar dados randômicos. Para isso acesse na aba esquerda `Repository Explorer`, em `Data adapters`
clique com lado direito e crie novo `Random Adapter`. Dessa maneira na pre-visualização do seu report será possível selecionar o Random Adapter criado 
para viasualização de dados.

Bandas de design:
 - Title: título presente apenas na primeira página
 - Page Header: título da página, se repete em todas as páginas
 - Column Header: título do detail, aparece apenas antes do detalhe do relatório, pode conter nomes das colunas por exemplo.
 - Detail: conteúdo principal do relatório, linhas de um relatório tabular por exemplo.
 - Column Footer: rodapé, apece apenas ao final do detail
 - Page Footer: é exibido em cada página, pode conter o número da página.
 - Summary: é exibido apenas ao final do relatório, pode conter um somatório geral por exemplo.

Basic Elements:
 - Static Text: bem literal, textos estáticos
 - Text Field: elementos de texto que receberão valores de dados. Para isso deve-se criar os campos em `Outline`>`Fields`>`Craete field`, selecionando o mesmo nome da propriedade e tipo.
Em duplo clique sobre o text field é possível adicionar o campo criado. Para formatação monetário, acesse `Properties` do lado inferio direito,
   Text Field, Pattern.
   
Importando report. Selecione o arquivo .jrxml e clique em `Compile report`, copie e cole o arquivo `.jasper` em resources do projeto Spring.

## Capítulo 14 - Upload e Download de arquivos

Artigo sobre problemas ao implementar relacionamento bidirecional OneToOne: [Mapeamento OneToOne bidirecional com Lazy Loading](https://blog.algaworks.com/lazy-loading-com-mapeamento-onetoone/).
Por esse motivo o produto não recebe propriedade FotoProduto nesta implementação.
Não será criado Repositorio FotoProduto, pois utilizaremos um repositório por agregado.
Aggregate root, que no caso é o Produto, possui seu repositório que engloba as entidades dentro desse agregado.

Separar services por responsabilidades, como salvar no banco de dados informações da imagem ou salvar em repositório dados em bytes da imagem.
Ao utilizar `default` na declaração de um método em interface inserimos uma implementação default para este método na própria interface.

No endpoint para buscar dados em bytes da imagem, espera-se que o consumidor da aplicação aceite content-type "image/jpeg".
Devido essa negociação de conteúdo o consumidor não recebe resposta em json com informação de detalhe de erro caso a foto não existe para os ids informados.
Nesse caso retornamos apenas `HttpStatus.NotFound` 404.

Retirando no controller `@GetMapping(produces = MediaType.APPLICATION_JPEG_VALUE)` tiramos a obrigatoriedade de o cliente ter no header da requisição "Accept: image/jpeg".
Isso porque outros formatos de imagem também são aceitos na aplicação, como png, ou o header "image/*" até. Dessa maneira optamos por verificar se o header presente aceita o tipo de imagem que será retornado para o consumidor.

#### Amazon S3
Criar conta (gratuita).
Acessar console (https://s3.console.aws.amazon.com/), serviços, S3 e criar <b>Bucket</b> ("food-kami").
Acessar serviço "IAM" e adicionar <b>usuário</b> ("food-test-s3"). Habilitar "Acesso programático".
No passo "Definir permissões", criar nova <b>política</b>. 

Na nova política inserir serviço "S3" e habilitar:
 - Gravação: PutObject (gravar arquivo), DeleteObject (excluir)
 - Gerenciamento de permissões: PutObjectAcl, PutObjectVersionAcl (através da API podemos alterar permissões de um arquivo)
Em "Recursos", "Adicionar ARNs" deve ser inserido o nome do bucket.
   
Na aba de "Adicionar usuário" atualizar e selecionar a política criada. Ao criar usuário é exibida a chave secreta.

##### Chave secreta
Para criar nova chave secreta de acesso é necessário acessa ro serviço `Identity and Access Management (IAM)`, selecionar "Usuários",
na ana "Credenciais de Segurança" selecionar `Criar chave de acesso`.

##### Properties
Ao anotar classe com `@ConfigurationProperties` podemos definir o prefixo presente em application.properties
que será mapeado para as propriedades da classe, como realizado na classe `StorageProperties`.

Após salvo no S3, caso a url do arquivo não esteja exibindo a imagem no navegador, porém fazendo download, deve-se observar em "Metadados"
se o "Content-type" é de imagem como "image/jpg", "image/png".
Esse contet-type deve ser enviado juntamente com o arquivo ao S3. 

##### Alternância de implementação de Storage
Para possibilitar alternância de implementação de storage a partir de configuração no `application/properties`
criamos um `Bean` Spring que verifica qual a implementação selecionada a partir de configuração.
Sendo que as instâncias retornadas não estão anotaadas com "@Service" dados que não desejamos que alguma seja a implementação padrão.

```java
  @Bean
    public FotoStorageService fotoStorageService() {
        if (TipoStorage.S3.equals(storageProperties.getTipo())) {
            return new S3FotoStorageService();
        } else {
            return new LocalFotoStorageService();
        }
    }
```

## Capítulo 15 - E-mails transacionais e Domain Events

Plataformas de envio de e-mail:
 - [Amazon Simple Email Service](https://aws.amazon.com/pt/ses/)
 - [Mandrill](http://mandrill.com/)
 - [MailGun](https://www.mailgun.com/)
 - [SendGrid](https://sendgrid.com/)
 
SendGrid será utilizada por não solicitar cadastro de cartão de crédito.
O remetente deve ser um e-mail válido, verificado no SendGrid em "Single Sender Verification". </br>
A dependência `javax.mail`, artifactId mail foi inserida para envio de e-mails.

[Apache FreeMarker](https://freemarker.apache.org/) é um _template engine_, uma biblioteca Java com a qual se pode formar um Html a partir de um template e com a inserção de objetos.
FreeMarker fornece alguns recursos como [diretivas](https://freemarker.apache.org/docs/ref_directive_list.html) que podem auxiliar na construção do html como: <#list, <#else, <#items.

Práticas recomendadas para html de e-mails requerem adaptações de Html para Web, pois muitos clientes de e-mail são restritivos e não renderizam todo o css e tags específicas.
Segue [artigo com Boas práticas de HTML para e-mails](https://ajuda.locaweb.com.br/wiki/boas-praticas-de-html-para-email-marketing-ajuda-locaweb/).

* Na implementação Sandbox (caixa de areia) enviamos um e-mail real para um endereço específico.
  Na implementação Fake apenas visualizamos o corpo do email no log da aplicação.
  Para evitar enviar e-mails para contatos de email reais do backup de banco de dados.

### Events
Ao chamar o método `registerEvent(T event)` de **AbstractAggregateRoot** em alguma classe/método, os métodos que estiverem anotados com `@EventListener` em uma classe anotada com `@Component` 
e recebendo o evento T passado como parâmetro serão executados, sem uma chamada direta.

Recomendado garantir é que a transação no banco de dados seja realizada com sucesso antes de disparar o evento. 
O Spring irá publicar o evento um instante antes de o Aggregate Root ser salvo, ou seja realizar o flush().

Artigo sobre [eventos assíncronos](https://www.baeldung.com/spring-events#anonymous-events).

#### @TransactionalEventListener
Permite vincular o método listener de um evento a uma fase da transação.
Assim, se o método foi anotado com `@TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)` o evento será publicado após o comit da transação e rollback não será possível.
Já se o método foi anotado com `@TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)` o evento será publicado antes do comit da transação e rollback será realizado em caso de exceção.

## Capítulo 16 - CORS e consumo da API com JavaScript e Java

#### CORS
Política de proteção do navegador que bloqueia requisição de origem diferente da origem do servidor, origem cruzada, Control-Allow-Origin.
Sendo "origem" a combinação de: protocolo + domínio + porta.

##### Preflight
Preflight é uma solicitação de comprovação, uma pequena solicitação enviada pelo navegador antes da solicitação real.
Ele contém informações como qual método HTTP é usado, bem como se algum cabeçalho HTTP personalizado está presente.
O preflight dá ao servidor a chance de examinar a aparência da solicitação real antes de ser feita.
O servidor pode então indicar se o navegador deve enviar a solicitação real, retornando os headers do CORS aceitos, ou retornar um erro ao cliente sem enviar a solicitação.
Identifica-se pelo Request Method: `OPTIONS` inserido pelo navegador no envio da requisição.
Só acontece quando a request é realizada de uma origem diferente do servidor.
Requisições "simples" nem sempre ativam o preflight, como tipo get, post, head, ou requisições com cabeçalhos comuns (não-específicos).

A resposta da request `preflight` com os origins permitidos, tipos de métodos permitidos, é armazenada em cache pelo navegador, por padrão por 
A propriedade "maxAge" indica quanto tempo em segundos o navegador pode salvar a resposta do preflight em cache.
`@CrossOrigin(origins = "*", maxAge = 10)`

[Definição de requisição simples, de acordo com CORS.](https://developer.mozilla.org/en-US/docs/Web/HTTP/CORS#simple_requests)

##### ObjectMapper
Object da biblioteca Jackson utilizada para realizar desserialização de conteúdo JSON em um objeto Java.

```java
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false); // Ignorando propriedades recebidas mas não mapeadas
        mapper.registerModule(new JavaTimeModule()); // Habilitando instanciação de classes Date
        mapper.findAndRegisterModules();
        var problem = mapper.readValue(cause.getResponseBodyAsString(), Problem.class);
```

## Capítulo 17 - Cache de HTTP

Na repetição de uma requisição utilizar a resposta recebida anteriormente armazenada em cache, não realizando uma nova requisição. 
Cache privado: localizado no consumidor, apenas ele tem acesso.
Cache compartilhado: localizado no servidor, em um proxy por exemplo.

Benefícios da utilização de cache: 
 - Reduz uso de banda, importante para dispositivos móveis com banda limitada
 - Reduz latência
 - Reduz carga nos servidores, menos requisições são processadas pelo origin server
 - Esconde problemas na rede

Quando não fazer cache?
 - Quando os dados mudam com frequência
 - Quando os consumidores não toleram diferenças entre estados no cache local e no servidor. Quando há diferenças no objeto que não devem ser toleradas pois o dado atualizado é importante para a aplicação.

#### Cabeçalho Cache-Control
Ao retornar o header Cache-Control o navegador mantém a informação `fresh` durante o tempo determinado pelo Origin Server, não realizando de fato a requisição durante este período de tempo.
O Postman não consegue reproduzir esse comportamento. Extensão `Talent API Tester` do Chrome pode ser utilizada. Após o tempo determinado em max-age a informação se torna velha, `stale`.

```java
 return ResponseEntity.ok()
 		.cacheControl(CacheControl.maxAge(60, TimeUnit.SECONDS))

```
Navegador indica que o dado foi recuperado do cache local: ![cache](/minhas-anotacoes/images/cache.png)


#### Validações e requisições condicionais com Etags

Entity Tag código que identifica a representação retornada em uma requisição, exemplo: "6198c0be3", hash calculado a partir do corpo de resposta. Se o corpo for diferente o hash será diferente e a requisição retornara o objeto completo. <br>
Após uma request ser considerada stale, ela será realizada novamente com o cabeçalho `If-None-Match`. Por exemplo: 'If-None-Match: "6198c0be3"'.
 - Servidor, me retorne o dado solicitado caso a representação Etag que eu possuo ("6198c0be3") esteja desatualizada. Caso contrário, apenas me informe que a representação está atualizada.
 
Caso a informação esteja atualizada o servidor retorna `304 - Not Modified`, sem corpo, e o browser altera a informação para "fresh".

```java
 	@Bean // Habilitando Filtro comparador de Etags
    public Filter shallowEtagHeaderFilter() {
        return new ShallowEtagHeaderFilter();
    }
```
Navegador exibindo Etag recebida: <br>
 ![etag](/minhas-anotacoes/images/etag.png)

#### Diretivas de Cache-Control na resposta HTTP

 - Private:
Configuração que informa que a resposta seja armazenada apenas em caches locais, logo, caches compartilhados entre o servidor e o consumidor, como o cache de um proxy reverso, não podem armazenar o dado de resposta.<br>
O padrão é `cachePublic` caso não configurado. Exemplo: 
`ResponseEntity.ok().cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS).cachePrivate())`
 - NoCache:
Indica que se a resposta for cacheada (local ou compartilhado) é necessário sempre que seja realizada a validação pelo servidor. A validação da resposta cache será sempre realizada, a informação está sempre "velha", ou seja, em modo "stale". Sendo enviado o cabeçalho `If-None-Match` para validação. <br>
`ResponseEntity.ok().cacheControl(CacheControl.noCache())`
 - NoStore:
Indica que a resposta não é cacheavél, em nehum tipo de cache.
`ResponseEntity.ok().cacheControl(CacheControl.noStore())`

#### Diretiva no-cache no cabeçalho Cache-Control da Requisição

Do lado do consumidor ao adicionar o header `Cache-Control: no-cache` é possível forçar que a requisição seja feita no servidor e não capturada do cache.

#### Deep Etags

Implementação realizada "na mão" para verificação de eTags, como a adição de uma coluna referente à data de atualização. Calculando um valor para última data de atualização de uma tupla. Como em `FormaPagamentoController`, a requisição recebe `ServletWebRequest` como parâmetro. Após o maxAge do cache o endpoint verifica se este parâmetro possui eTag é semelhante ao eTag calculado. Caso positivo a request retorna nulo (http status 304) e o consumidor/navegador utiliza o cache armazenado como resposta. <br>
Verificando se eTag é igual ao receber `If-None-Match`: `request.checkNotModified(eTag)`. Vale ressaltar que nesse caso foi realizado didaticamente, a ShallowEtagHeaderFilter supriria a necessidade. É necessário avaliar se vale a pena a implementação e o processamento.

## Capítulo 18 - Documentação da API com OpenAPI, Swagger UI e SpringFox

Documentar facilita a compreensão e desenvolvimento do consumidor da API. O resultado do nosso trabalho objetiva ajudar outras pessoas.<br>
 - Cuidados ao documentar uma API: descrever de forma simples e clara o que o endpoint faz, descrever os parâmetros e o modelo de representação de entrada, descrever o modelo de representação retornado em caso de sucesso, descrever o modelo de representação de um problema, garantir que existe consistência entre a documentação e a forma que a API realmente funciona.

 Especificações para descrever a API em formato padrão e gerar documentação através de ferramentas, entre outras funcionalidades: RAML, API Blueprint, OpenAPI Specification (que anteriormente foi chamada Swagger Specification).

### OpenAPI Specification

Padronização para descrever comportamento de Rest API. O resultado final é um arquivo `json` ou `yaml` com as definições da API, descrições dos endpoints, parâmetros de entrada, modelos de representação de entrada e saída, códigos http de retorno e etc. Agnóstica à linguagem de programação (não é apenas pra Java). <br>
Até a versão 2 era chamada Swagger e proprietária da empresa Smart Bear, após isso se tornou de domínio da comunidade. Atualmente Swagger é um conjunto de ferramentas que auxiliam a espeficidação como: Swagger Editor, Swagger UI, Swagger Codegen (gera código de clientes e controllers).<br>
Nesse projeto será utilizado o `SpringFox` para gerar o Json. Observação: o SpringFox tem suporte até a especificação OpenAPI 2.
O Json fica acessível através da url: `http://localhost:8080/v2/api-docs`.

#### [Swagger UI](https://swagger.io/tools/swagger-ui/)
Projeto implementado para exibir documentações com especificação OpenAPI. Arquivos estáticos que montam dinamicamente a documentação através do Json que descreve a API fornecido pelo SpringFox. A documentação fica acessível em `http://localhost:8080/swagger-ui.html`.

```java
@Configuration
@EnableSwagger2
public class SpringFoxConfig {
    @Bean
    public Docket apiDocket() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(Predicates.and( 	// Documenta controllers dos pacotes especificados (mais de um)
                        RequestHandlerSelectors.basePackage("com.kamila.food.api"),
                        RequestHandlerSelectors.basePackage("com.kamila.food.api.outro"))
                )
                .apis(RequestHandlerSelectors.basePackage("com.kamila.food.api")) // Documenta controllers do pacote específico
                .paths(PathSelectors.ant("/restaurantes/*")) // Documenta controllers por path
                .build();
    }
	...
```

 - A descrição dos endpoints tem por padrão o nome do método, para alterar basta utilizar no método a anotação `@ApiOperation("Descrição")`.
 - A descrição de um parâmetro se dá por `@ApiParam(value = "ID de uma cidade", example = "1")`, a propriedade "example" foi inserida para evitar warning message no console.
 - Descrição de um modelo por anotação na classe model: `@ApiModel(value = "Cidade", description = "Representa uma cidade")`.
 - Descrição de propriedade de modelo: `@ApiModelProperty(value = "ID da cidade", example = "1")`.
 - As propriedades são exibidas em ordem alfabética, para customizar a ordem de exibição adicionar o atributo `position`: <br>
 `@ApiModelProperty(example = "400", position = 1)`
 - Descreve códigos de status de resposta, mensagem e objeto de retorno em endpoints específicos: <br>
 `@ApiResponses({
            @ApiResponse(code = 204, message = "Cidade excluída"),
            @ApiResponse(code = 404, message = "Cidade não encontrada", response = Problem.class)
    })`

#### BeanValidation Documentação
Para exibição de obrigatoriedade de propriedades oriundas do BeanValidation deve ser adicionada a dependência no projeto e inserida na classe de configuração `@Import(BeanValidatorPluginsConfiguration.class)`.

```xml
<dependency>
	<groupId>io.springfox</groupId>
	<artifactId>springfox-bean-validators</artifactId>
</dependency>
```

Vale observar que não há plugin para `@NotBlank` logo os campos assim anotados não serão automaticamente grafados com * (asterisco) na documentação Swagger. <br>
Caso a propriedade esteja anotada com `@ApiModelProperty` a propriedade required dessa anotação tem por padrão o valor false, que sobrescreve na documentação o plugin NotNullAnotationPlugin e o asterisco não aparece na documentação. Logo é necessário optar por `@ApiModelProperty(required=true)` ou apenas @NotNull para que a obrigatoriedade seja exibida.<br>
É necesário ter atenção pois a biblioteca por si só não exibe as obrigatoriedades em todos os casos.

#### Customizando códigos de erro
Na classe de configuração da Documentação é necessário adicionar os códigos de erro para cada tipo Rest de método, assim eles serão exibidos em todas as requisições desse tipo:
` .globalResponseMessage(RequestMethod.GET, globalGetResponseMessages())`

#### Parâmetros implícitos
O Squiggly, por exemplo, filtra as requisições e é capaz de inserir um parâmetro implícito, no nosso caso é "campos" configurado em `SquigglyConfig`.<br>
Caso este parâmetro esteja presente globalmente, ou seja, em todo o projeto, pode-se configurar na classe de configuração da documentação através de `globalOperationParameters`. <br>
De forma específica utilizar `@ApiImplicitParams` no endpoint.

#### Descrevendo projeções
Uma projeção do JsonView pode ocultar algumas propriedades do objeto de retorno como em RestauranteController.listarApenasNomes.
Dessa forma o objeto retornado no método não condiz com os atributos retornados de fato, pois a sua projeção foi customizada. <br>
Para descrever isso corretamente foi criado um objeto com os atributos realmente retornados pela projeção, apenas para fins de documentação e foi inserido na anotação *@ApiOperation* na propriedade **response**, sobrescrevendo o retorno do método (apenas na documentação): <br>
`@ApiOperation(value = "Lista restaurantes", response = RestauranteBasicoModelOpenApi.class)`

Ao enviar um arquivo para o endpoint de salvar imagem de um produto verifica-se o erro de que o Swagger não adiciona o header `Content-type: multipart/form-data` e a requisição ocorre com erro. Por este motivo foi adicionado o parâmetro `@RequestPart(required = true) MultipartFile arquivo` ao recurso.


## Capítulo 19 - Discoverability e HATEOAS: A Glória do REST

`HATEOAS` é uma restrição que faz parte da arquitetura de aplicações REST, cujo objetivo é ajudar os clientes a consumirem o serviço sem a necessidade de conhecimento prévio profundo da API. 

O acrônimo HATEOAS vem de Hypermedia As the Engine Of Application State, a API passa a fornecer links que indicarão aos clientes como navegar através dos seus recursos. ([artigo](https://www.treinaweb.com.br/blog/o-que-e-hateoas))
Exemplo:
![exemplo de resposta hateoas](/minhas-anotacoes/images/exemplo-hateoas.png)

#### Discoverability vs HATEOAS
Discoverability é a capacidade que a API dá aos consumidores de navegar por recursos sem conhecer previamente as URIs. Discoverability é um conceito, já o HATEOAS é um componente do REST que torna isso possível, através da inclusão de hypermedia (links) nas respostas através de um ponto de entrada da API (root entry point).

A implantação deve ser analisada, se realmente vai agregar valor ao projeto, ajudar realmente os desenvolvedores e consumidores da API, pois dá muito trabalho, maior custo e deve ser utilizado corretamente.
A **vantagem** é a possibilidade de evoluir a API sem ficar "quebrando" endpoints a cada alteração, inativação de endpoints, ou alteração de parâmetros de entrada, por exemplo. Pois a API altera o link, ou remove e o consumidor o segue, sem links hardcoded, e não acessa links quebrados.

#### Implementação
A especificação HATEOAS utilizada no projeto é o `HAL` (Hypertext Application Language) um dos mais populares. Outras especificações: Uber, JSON:API, JSON-LD, Collection+JSON, Siren.

`RepresentationModel` é semelhante à um container para coleção de links. Possui métodos para adicionar links ao modelo. <br>
`Link relation` indica o relacionamento do recurso destino do link com o recurso atual, self indica que se refere ao mesmo recurso. <br>
Duas maneiras de inserir o mesmo link:

 ```java
 	cidadeModel.add(WebMvcLinkBuilder.linkTo(CidadeController.class)
			.slash(cidadeModel.getId())
			.withSelfRel());

 	cidadeModel.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder
			.methodOn(CidadeController.class)
			.buscar(cidadeModel.getId())
	).withSelfRel());

```

#### Root Entry Point
O consumidor deve poder acessar a API pelo ponto de entrada raiz. Assim, caso algum endpoint tenha alteração na sua URL, os consumidores não sofrerão impacto já que estariam seguindo os links retornados e acessados pela API e pelo `Root Entry Point`.

Caso não seja enviado token de autenticação é retornado objeto vazio, com resposta 200 OK. Caso o usuário esteja autenticado serão retornados os endpoints soa quais ele possui permissão de visualização.

#### Comprimindo respostas HTTP com GZIP
Com um payload maior contendo os links para implantação do HATEOAS pode ser interessante comprimir as respostas da API com GZIP.
Habilita-se a compressão adicionando `spring.compression.enabled=true` em `application.properties`. Vale ressaltar que o servidor utilizará processamento para comprimir.

## Capítulo 20 - Evoluindo e versionando a API

Retrocompatibilidade, consumidores anteriores poderão continuar usando a API após a alteração, vs Quebra de compatibilidade, necessidade de lidar ocm os consumidores que já estão utilizando a API.
Deve-se evitar quebrar os clients da API, se possível.

#### Evitando quebrar clientes:
 - Nova propriedade de modelo.
  Em nova propriedade em modelo de saída há retrocompatibilidade, ao adicionar um atributo em objeto de saída o consumidor ignora-o.
  Em nova propriedade em modelo de entrada (put, post) pode haver quebra de compatibilidade. Caso o atributo seja obrigatório o cliente deve adicioná-lo. Caso seja opcional há retrocompatibilidade.<br>
  Sugestão: alterar atributo para opcional, após estabilidade alterar para obrigatório se necessário.

 - Exclusão de propriedades do modelo.
 Ao remover propriedade em objeto de saída há quebra de compatibilidade, pois os consumidores estão esperand receber este atributo. Sugestão: retirar valor da propriedade, retorná-la com valor null ou zero, informando que a propriedade logo será excluída. <br>
 Ao remover atributo em modelo de representação de entrada é possível que haja quebra de compatibilidade, se a API estiver configurada para identificar erro ao receber propriedade não esperada. Sugestão: retirar da documentação a propriedade, para novos clientes não utilizarem, deixá-la no controller e não utilizá-la.
 
 - Alteração de um tipo amplo para específico.
  Por exemplo, propriedade recebida do tipo String passa a ser do tipo Decimal. É mantida a retrocompatibilidade.

 - Alteração de um tipo específico para amplo.
  Por exemplo, propriedade recebida do tipo Decimal passa a ser do tipo String. Nesse caso há quebra de compatibilidade, e necessidade de conversão do dado.  Sugestão: adicionar nova propriedade do tipo desejado e depreciar a propriedade anterior.

 - Alteração na estrutura de dados do modelo.
 Por exemplo, a saída de um objeto restaurante contendo a propriedade `cozinha: {id: long, nome: string}` passa a possuir as propriedades `cozinhaId`e `cozinhaNome`. Uma alternativa é manter as duas formas de retorno até que os consumidores estejam adaptados ao novo modelo de saída.
 Na entrada, a alternativa para manter compatibilidade seria permitir as duas formas do modelo, depreciando a representação que deve ser descontinuada no futuro.
  
 - Alteração de URL de recurso.
 Deve-se evitar essa alteração. Alternativamente é possível aceitar duas URLs para o recurso, a anterior e a atualizada. Por exemplo:
 > @RequestMapping(value = { "/gastronomias", "/cozinhas" })

 Implementando o HATEOAS, não deve haver problemas de compatibilidade caso os consumidores estejam seguindo os links retornados pela API e seguir os fluxos do Root Entry Point. Nesse caso o link relation deve ser mantido, no caso "cozinhas", até que seja descontinuado pelos clientes e pode ser adicionado o link relation "gastronomia".

#### Versionamento de APIs
Deve-se evitar quebrar a compatibilidade. Caso necessário quebrar a compatibilidade versione a API.
Deve-se evitar alterar muitas versões com brevidade requerendo aos consumidores alterações constantes.
Ao alterar uma versão com quebra de compatibilidade é interessante manter as duas versões sendo executadas. Evite versionar a API ao máximo.

Técnicas de versionamento de APIs:
 - Versionamento por Media Type: 
  Cliente envia o número da versão no header *Accept*: `Accept=application/vnd.food.v1+json`
  Api prepara o controller, no exemplo: `@RequestMapping(path = "/cidades", produces = application/vnd.food.v1+json)`
 - Versionamento por URI: `/v2/cozinhas`

Abordagens para manter a base de código de API versionada:
 - Projeto separado para cada versão. Com servidor de proxy reverso "roteando" para cada versão sendo executada no servidor. Possui a vantagem de possibilitar alterações em uma versão sem impactar a outra, manter aplicações com linguagens diferentes. Desvantagem seria a duplicação de código, já que alguns recursos se mantem nas duas versões.
 - Único projeto e reaproveitar código da camada da API. Controllers com endpoints sem alteração para a segunda versão podem ser acessados tanto por "v1" quanto por "v2", e os endpoints específicos da alteração são adicionados para a v2. A vantagem é evitar duplicar código. A desvantagem é que a manutenção de um código para a v2 pode alterar a versão 1 também, havendo maior risco de quebrar compatibilidade. Outra desvantagem é a utilização da mesma stack, mesmas tecnologias, já que se trata do mesmo projeto.
 - Mesmo projeto com separação total da camada da API. Toda a camada da API é duplicada, sendo uma para v1 e outra para v2. As desvantagens são o código duplicado e utilização da mesma stack. Menor risco de problemas do que a abordagem anterior. Esta será adotada no projeto.


Foi adicionado um componente para habilitar o formato Hateoas `HAL` para Media Type customizado chamado `HalCustomMediaTypeEnabler` no projeto.

#### Versionamento por URI
Neste projeto foi implementada o verisonamento por URI, assim os controllers podem ter "/v1", ou "/v2" no path.
Para documentar as duas versões foram configurados dois Beans de Dockets, com `groupName` diferentes, assim, a versão da documentação pode ser selecionada no canto superior direito da página swagger, em `Select a spec`.

#### Depreciação
Primeiro deve-se depreciar uma versão da API, informando que ela terá uma data limite de funcionament, para apenas posteriormente descontinuá-la. <br>
É importante entrar em contato com os consumidores para que se preparem para a mudança para nova versão. <br>
Na versão do swagger utilizada, ao incluir a anotação `@Deprecated` na controller, não há sinalização na documentação de que o controller está depreciado. Seria necessário adicionar a mesma anotação em cada um dos métodos.

## Capítulo 21 - Logging
[Logback](http://logback.qos.ch/) é um framework de log pra aplicações Java, criado para ser o sucessor do Log4j e já é o framework de log padrão Spring. <br>
SLF4j é uma biblioteca que abstrai diversos frameworks de log, repassando as operações para o framework de log sendo utilizado na aplicação.
Nosso código conversa com SLF4J e este abstrai o Logback.

O Lombok fornece uma instancia de Logger através da anotação `@Slf4j` na classe.

#### Gerenciamento de Logs
Em uma aplicação em nuvem o ideal é não deixar arquivo de logs na própria máquina. Principalmente sendo uma aplicação em container que pode ser descartada, ou dispersa em várias instâncias. <br>
O serviço de gerenciamento de logs utilizada será o [Loggly](https://www.loggly.com/).

Instruções foram capturadas da [documentação do Loggly](https://documentation.solarwinds.com/en/success_center/loggly/content/admin/java-logback.htm). No arquivo `logback-spring.xml` foi configurado um appender com a função de escrever logs em determinado local. Para não remover os logs locais e enviar todos para o Loggly, mas sim replicá-los, foi adicionada a tag `<include />` incluindo o arquivo `base.xml`.

Arquivo *base.xml* padrão de configuração de logback do spring e pode ser localizado na pasta da dependência `spring-boot\2.2.2.RELEASE\org\springframework\boot\logging\logback\base.xml`.<br>
A inicialização da aplicação fica mais lenta pois aguarda o envio de logs para a nuvem, para evitar isso interceptamos o appender com o `AsyncAppender`, então os logs deixam de bloquear a aplicação e são enviados de forma assíncrona.

```xml
<configuration>
	<!-- Incluindo arquivo padrão de log do Spring (console) -->
    <include resource="org/springframework/boot/logging/logback/base.xml" />
	<!-- Configurando envio de registros para Loggly -->
    <appender name="loggly" class="ch.qos.logback.ext.loggly.LogglyAppender">
        <endpointUrl>https://logs-01.loggly.com/inputs/TOKEN/tag/logback</endpointUrl>
        <pattern>%d{"ISO8601", UTC} %p %t %c %M - %m%n</pattern>
    </appender>
	<!-- Configura envio de logs de forma assíncrona, para q a aplicação não seja bloqueada pelo envio de logs para a nuvem -->
    <appender name="logglyAsync" class="ch.qos.logback.classic.AsyncAppender">
        <appender-ref ref="loggly" />
    </appender>
	<!-- Referenciando o appender assíncrono, e este por sua vez referencia o envio para Loggly (nuvem) -->
    <root level="info">
        <appender-ref ref="logglyAsync"/>
    </root>
</configuration>
```

Interessante manter o registros de logs quando em teste ou em produção, porém desnecessário em desenvolvimento. Para isso é útil a tag `<springProfile>` em logback-spring.xml.

## Capítulo 22 - Segurança com Spring Security e OAuth2
Serão abordados Http Basic Authentication e OAuth2, principalmente OAuth2.

### Http Basic Authentication
 Na Autenticação básica do Http, deve ser passado um header chamado `Authorization` com valor no formato nome_de_usuário:senha codificados em Base64. Estes dados estão em todas as requisições e se capturados podem ser facilmente decodificados. Essa fragilidade pode ser amenizada através do Https, porém o cliente ainda deve armazenar o usuário e senha, fornecendo risco à segurança.<br>
 Uma opção mais segura seria uma aplicação protegida por firewal em rede privada, bloqueando requisições externas e implementando Https, porém este não é o cenário mais geral.

 Ao adicionar a dependência Spring Boot Security Http Basic Authentication já é implementada por padrão. Com usuário padrão "user" e senha printada no console ao inicializar. Estes valores podem ser alterados através das propriedades da aplicação:

 ```xml
 spring.security.user.name=username
 spring.security.user.password=password
```
Ao ser autorizado, o cliente recebe uma sessão com tempo determinado para expiração. Essa sessão é identificada pelo cookie `JSESSIONID`. O cliente fica em posse desse cookie e o envia nas requests. Desse modo não é necessário enviar o Authorization (correto) enquanto esse cookie estiver válido.

### OAuth2
O cliente obtém um token de acesso e o envia no header  "Authorization", a API pode verificar as permissões do consumidor.
Permitindo que aplicações terceiras tem acesso limitado a uma API. É um conjunto de regras, um protocolo, uma especificação, descrito na RFC6749.
#### Papéis (Roles)
 - Resource Server: servidor que hospeda os recursos protegidos, a API (Food API)
 - Resource Owner: Usuário final, dono dos recursos. No nosso cenário os donos dos restaurantes realizando atualização dos dados por exemplo.
 - Client: Aplicação (web, mobile) que acessa os recursos protegidos do Resource Server
 - Authorization Server: Servidor que autentica o Resource Owner e garante autorização de acesso para o cliente ao Resource Server (Food Auth). 
 
No cenário de portaria de um prédio, o `Authorization Server` seria o porteiro. O `Resource Owner` seria o proprietário de um apartamento. O `Resource Server` é o recurso no interior do apartamento, uma compartimento. E o `Client` é uma visita, solicitando acesso a entrar no apartamento e pegar um recurso. Assim o Authorization Server solicita permissão ao Resource Owner para que o Client acesse o recurso no Resource Server. Veja o Authorization Code Flow (este é um dos fluxos possíveis):

![Authorization Code Flow](./minhas-anotacoes/images/authorization-flow.png)

#### Soluções Spring OAuth 2
[Spring Security OAuth](https://spring.io/projects/spring-security-oauth) dá suporte para implementar tanto o Resource Server quanto o Authorization Server. Estes podem estar em projetos diferentes até.<br>
Houve um movimento para portar o código para outro projeto, o [Spring Security](https://spring.io/projects/spring-security), não dando suporte de OAuth ao Spring Security OAuth e mantendo apenas no `Spring Security`.
Porém não foi criado suporte ao Authorization Server no Spring Security.
Spring Security OAuth está sendo depreciado. 

Logo o Resource Server será desenvolvido utilizando o Spring Security e o Authorization Server só tem suporte de implementação através do Spring Security OAuth neste momento(2021.2).

#### Grant Types

##### Resource Owner Password Credentials Grant

Fluxo `Resource Owner Password Credentials Grant` é uma forma de obter o token de acesso a partir de um usuário e senha, o Client envia o usuário e senha para o Authorization Server, e este emite um token de acesso. É um fluxo que deve ser utilizado apenas para aplicações desenvolvidas pelo grupo proprietário. É um risco para o usuário fornecer seus dados de acesso para clients desenvolvidos por empresas terceiras.<br>
Hoje ele é [não aconselhável](https://www.scottbrady91.com/OAuth/Why-the-Resource-Owner-Password-Credentials-Grant-Type-is-not-Authentication-nor-Suitable-for-Modern-Applications) pois delega ao cliente a responsabilidade de manipular as credenciais dos seus usuários.

![Password Grant Flow](./minhas-anotacoes/images/password-credentials.png)

##### Client Credentials Grant
Fluxo voltado para a utilização de uma aplicação back-end atuando como cliente de uma API.
O token de acesso não é uma delegação de acesso de um Resource Owner, ela representa uma chave de acesso em nome do Client.
Nesse fluxo não deve haver Refresh Token.

![Password Grant Flow](./minhas-anotacoes/images/client_credentials.png)

##### Authorization Code Grant Type
Uri de redirecionamento deve estar cadastrada no Authorization Server. Authorization Server exibe formulário de login para o Resource Owner autenticar e autorizar o acesso aos recursos. É ideal para a integração de Front-end com o Back-end.

O atributo State é apenas enviado e recebido posteriormente pelo client, verificando igualdade, com o objetivo de envitar ataque crsf.

```javascript
// Client solicita login ao Authorization Server
(GET) https://AUTHORIZATION-SERVER/oauth/authorize?response_type=code&client_id=id_do_cliente&state=abc&redirecet_uri=https://CLIENT/
// Authorization Server redireciona Client para página de login (uri)
https://CLIENT/?code=ZQwg6B&state=abc
// Client envia code e solicita Access Token para o Authorization Server
(POST) http://localhost:8081/oauth/token?code=ZQwg6B&grant_type=authorization_code // Troca o código pelo access token, podendo ser retornado
```

Exemplo de login joao.ger@kfood.com, senha: 123. Link de acesso no navegador para [login](http://localhost:8080/oauth/authorize?response_type=code&client_id=foodanalytics&state=abc&redirect_uri=http://localhost:3000) do usuário.


Link para [configuração de CORS no Authorization Server com CorsFilter](https://gist.github.com/thiagofa/764260dfd8ba21f30f2f79806d734563), na qual adiciona um filtro de servlet que retorna os cabeçalhos para as requisições `OPTIONS`.

##### Authorization Code com PKCE
`Prove Key for Code Exchange`, é uma técnica para reduzir o risco de ataque de interceptação de código de autorização, gerada pelo fluxo Authorization Code.<br>
Objetiva trazer mais segurança, pois havendo um Cliente público no fluxo que recebe o Código de autorização, é possível que um software malicioso intercepte o Código antes do cliente legítimo e o utilize para obter o Access Token.

Nesse fluxo o cliente cria em tempo de execução um código chamado `Code Verifier`, sobre ele são aplicadas duas codificações, o SHA 256 e em seguida o Base64. O código gerado por essas codificações é chamado `Code Challenge`. <br>
O cliente envia para o Authorization Server o Code Challenge no momento em que solicita a autorização (redirect), o Authorization o armazena e gera/retorna o código de autorização.<br>
O cliente então envia o código de autorização recebido e o `Code Verifier`, nesse momento o `AS` (Authorization Server) aplica as codificações correspondentes (SHA 256 e BASE 64) e valida se o Code Verifier codificado corresponde ao Code Challenge armazenado. Verificando que quem está enviando o Code Verifier é realmente o cliente, e não um software malicioso que interceptou o code, retornando o `Access Token`.<br>
Quem decide o método de verificação do Code Challenge é o cliente, sendo eles s256 (SHA256, recomendado) ou Plain (texto comum). <br>
O ideal seria dar opção ao usuário, se usar o PKCE não seria necessário autenticar o usuário com o header Authorization Basic, caso contrario seria necessário autenticar o usuário.

![Authorization Code PKCE](./minhas-anotacoes/images/authorization-flow-pkce.png)

[Implementação de PKCE para Spring Security OAuth2](https://gist.github.com/thiagofa/daca4f4790b5b18fed800b83747127ca)

 - Exemplo 1:
Requisição para receber code: (GET - navegador) `http://localhost:8081/oauth/authorize?response_type=code&client_id=foodanalytics&state=abc&redirect_uri=http://aplicacao-cliente&code_challenge=codeteste123&code_challenge_method=plain`
**Code Verifier** (string alfanumérica de no mínimo 43 e máximo de 128 caracteres): 8a76sd5f098cv7bx98wrtw54e654r123p4oklklm09jd94385jhngfe8fgh7
**Code Challenge** (usando method plain): 8a76sd5f098cv7bx98wrtw54e654r123p4oklklm09jd94385jhngfe8fgh7
**Code Challenge** (usando s256): base64(sha256("8a76sd5f098cv7bx98wrtw54e654r123p4oklklm09jd94385jhngfe8fgh7")) = g6nNGXtOvJTmVan5pklZV99qNyH4JR09TGg0a_AIfCA

##### Implicit Grant
Authorization Server replica o fluxo do Authorization Code Grant Type, porém não retorna um código para obtenção do access token, retorna diretamente o access token.
O uso desse fluxo é desencorajado, pois retornar o access token na URL gera um risco de segurança, é um fluxo legado. É possível ver mais detalhes do fluxo na [imagem](food-api/images/implicit_flow_grant.png).

Para a url acessada no navegador: `http://localhost:8081/oauth/authorize?response_type=token&client_id=webadmin&state=abc&redirect_uri=http://aplicacao-cliente` <br>
Recebemos a resposta com o token: `http://aplicacao-cliente/#access_token=c0b6cfa1-aad1-491c-9e52-f723e6186c73&token_type=bearer&state=abc&expires_in=43199&scope=read%20write`

### Authorization Server

[Dependências para resolver problema com Spring Security OAuth2 e Java 11+](https://gist.github.com/thiagofa/ef9a40d495016cb2581add41b5cbde1b).

Para solicitação do token o client se autentica passando o header Http `Basic Auth` com as suas credenciais configuradas na classe `AuthorizationServerConfig`. Autentica também o usuário enviando as credenciais do Resource owner no body do tipo `x-www-form-unlercoded` com as propriedades:

`"username":"kamila", "password": 123", "grant_type": password"`

RFC7662 especifica implementação da checagem de token.

#### Refresh token
Quando o token expira pode existir um `Refresh Token`, que é utilizado com o objetivo de solicitar outro access token válido. O Authorization Server pode ser configurado para enviar um Refresh Token. <br>
O Client, em posse do Refresh Token, utiliza-o para solicitar outro Access Token e tbm um novo Refresh Token para o Authorization Server.
O tempo de expiração padrão do refresh token é de 30 dias.<br>
Ao solicitar um novo token, o access token anterior desse usuário é revogado, já o refresh_token utilizado permanece ativo enquanto estiver no seu tempo de vida, podendo ser utiizado mais de uma vez para gerar access token essa é a configuração padrão.<br>
Para garantir maior segurança foi **desabilitada a reutilização** do refresh token na aplicação. Fazendo com que o cliente descarte o refresh token após utilizá-lo, pois fica inutilizável, e passe a utilizar o novo refresh-token recebido. <br>
Em caso de captura de um refresh_token por um estranho, quando um cliente legítimo o utiliza para gerar o access token ele fica inválido, não sendo mais de utilidade para um terceiro.

### Decidindo qual fluxo OAuth2 usar
Tipo de cliente:
 - Público: usuário pode visualizar ou modificar o código fonte e acessar o *client secret*, como aplicações javascript executadas no navegador, como Single Page Applications ou aplicações nativas mobile ou desktop.
 - Confidencial: código não pode ser acessado pelo usuário, como aplicação web executada no servidor.
<br>
 - Confiável: aplicação própria, a mesma empresa desenvolveu.
 - Não Confiável: aplicação de terceiro, cujos reais objetivos são desconhecidos.

![OAuth2 Fluxo](./minhas-anotacoes/images/oauth2-fluxo.png)

*Obs.*: Cliente sem usuários finais seria uma aplicação back-end se comportando como cliente de uma API.


| Client  | Password  | Grant Type  | 
|---|---|---|
| faturamento  | faturamento123  | client_credentials  |
| food-web  | web123  | password  |
| foodanalytics  | food123  | authorization_code  |


## Capítulo 23 - OAuth2 avançado com JWT e controle de acesso

No fluxo anterior o Access Token e o Refresh Token estão armazenados na memória do Authorization Server. Desse modo, caso as aplicações estejam implantadas em várias instâncias na nuvem, os tokens são específicos e armazenados em cada instância, caso o balanceador de carga redirecione a requisição de usuário autorizado na instancia 1 para uma instância 2 em que ele não está autorizado, o acesso não será possível.

### Redis
Por isso é aconselhada a criação deum banco armazenando os tokens, `Token Store no Redis`. <br>
**Redis** é um banco de dados Não relacional (NoSQL) para armazenamento de dados em memória, que armazena dados como chave valor, open source. <br>
Download do arquivo [Redis-64-3.0.504.zip](https://github.com/microsoftarchive/redis/releases/tag/win-3.0.504). Executar o arquivo redis-server e em seguida redis-cli. Sempre que precisar executar o Redis, precisará executar estes dois arquivos.

Com as configurações o token será armazenado no Redis, e ao gerar um token, reiniciar a aplicação e checar esse token, ele continuará válido, pois o token não foi periddo na memória local.

Ao acessar `redis-cli` é possível visualizar as chaves armazenadas, no nosso caso os tokens. Comandos:
 > AUTH password // Onde password é a senha caso esta tenha sido configurada
 > keys * // Lista todas as chaves armazenadas
 > flushall // Exclui todos os ddados do banco 

Propriedades a ser adicionadas no projeto Authorization Server:
```properties
spring.redis.host=localhost
spring.redis.password=
spring.redis.port=6379
```

### Stateless vs Statefull
Em tradução livre Stateless significa "sem estado" e Statefull "com estado". Roy Fielding é quem descreve estes conceitos: *"A comunicação deve ser stateless (sem estado) por natureza (...). O estado da sessão deve ser armazenado inteiramente no cliente."* <br>
 - Application State: estado da aplicação, estado da sessão do usuário, é este que deve ser Stateless segundo Roy Fielding. Não deve ser armazenado o estado da aplicação no servidor, no nosso caso o usuário monta todo seu pedido no cliente e envia todos os dados de um pedido em uam requisição apenas. O pedido não é feito passo a passo no servidor.
 - Resource State: estado dos recursos, dados relacionados ao domínio do negócio.

#### Statefull Authentication
Vantagem: revogação da sessão removendo token do Token Store; dados relacionados ao token podem ser alterados a quelquer momento. Desvantagem: maior infraestrutura do lado do Authorization Server (banco de dados); dependência entre todos os Resources Server no Authorization Server; Authorization Server funciona como um Single Point of Failure, se ele falhar para completamente todo o sistema (minimizado com várias instâncias).

#### Stateless Authentication
Dados da sessão do usuário são armazenados do lado do cliente, servidor desconhece os tokens que emitiu, apenas consegue identificar se está válido ou não.
AS (Authorization Server) retorna para o cliente um `Transparent Token`. São aqueles que não são codificados e possuem todas as informações contidas nele, assinados com algoritmo criptográfico. Nesse caso o próprio Resource Server valida o token.
<b>Vantagens</b>: não é necessário infraestrutura do lado do AS para armazenar tokens; uma vez que o token é emitido o AS não é mais um ponto de falha do sistema. <br>
<b>Desvantagens</b>: não é possível revogar um token, necessário esperar a data de expiração; mais dados trafegados nas requisições, token possui mais informações e deve ser enviado em todas as requisições.

<b>Qual utilizar?</b> Caso seja necessário gerenciar tokens já emitidos a solução indicada seria Statefull, caso contrário Stateless pode ser utilizada. Necessário análise de cada caso.

### JSON Web Tokens (JWT)
É um tipo de token transparente JSON especificado na [RFC7519](https://tools.ietf.org/html/rfc7519) utilizado em autenticação Stateless. Resulta em uma string codificada em Base64 assinada por um hash criptográfico. Um token JWT possui três partes separadas por ponto ("."): header, payload e assinatura. 
```json
// Header
{
  "alg": "HS256", // Algoritmo criptográfico para geração do hash
  "typ": "JWT" // Tipo do token
}
```
O Payload contém as `clains`, afirmações chave/valor armazenadas no token. Deve-se evitar enviar dados sensíveis.<br>
A assinatura é um hash criptográfico especificado no header, deve ser verificada através do secret key.
Quem tem a chave secreta pode verificar a integridade do token e também emitir novos tokens.

Nesse fluxo o AS é responsável pela geração do token e a verificação fica a cargo do RS, caso o AS deixe de funcionar, um usuário com token válido continua tendo acesso aos recursos do RS (chave simétrica).

[Ferramenta online para debugging de JWT](https://jwt.io/), permite codificar e decodificar o token, visualizando as claims. Não deve ter informações sensíveis, como senha do usuário. 

#### Chave Simétrica vs Chave Assimétrica
Com a utilização da chave simétrica o Resource Server fica em posse da key de verficação de tokens, que é a mesma chave utilizada para criação de novos tokens. Portanto as aplicações autenticadas com o AS devem ser muito confiáveis para utilização desse método. Aqui foi utiizado o algoritmo HS256.

Para utilização de chave assimétrica, há uma chave privada para a criação de novos tokens e outra pública para verificação de autenticidade. Aqui utilizaremos o algoritmo RS256. Assim não é possível criar tokens com a chave pública.

<img src="./minhas-anotacoes/images/chave-assimetrica.png" width="450"/>

Para **criar um par de chaves** é possível utilizar a ferramenta utilitária proveniente do jdk chamada `keytool`, através do prompt de comando.
Gerando um arquivo JKS com um par de chaves:
> keytool -genkeypair -alias food -keyalg RSA -keypass 123456 -keystore food.jks -storepass 123456

Listando as entradas de um arquivo JKS:
> keytool -list -keystore food.jks

Para **extrair a chave pública** do arquivo .jks inicialmente deve-se extrair o certificado. `-rfc` informa que queremos exportar em formato textual definido na RFC1421.
> keytool -export -rfc -alias food -keystore food.jks -file food-cert.pem
Após isso deve-se executar o comando para ser exibida a chave pública:
> openssl x509 -pubkey -noout -in food-cert.pem > food-pkey.pem

Outra forma possível é realizar a requisição GET no Authorization Server `oauth/token_key`. Estando habilitada a permissão para acessar o endpoint sem autenticação.

Após implantação da autorização com chave assimétrica, ao acessar com o client `foodanalytics` e efetuar login, não haverá mais a opção para aprovar ou negar cada escopo de atuação (write, read, por exemplo). Para habilitar esse comportamento novamente foi adicionado `.approvalStore()` em AuthorizationServerConfig. (Em outras palavras: João realiza login e aprova os escopos que o FoodAnalytics pode usar na API em nome dele)

#### Autorização de usuário com dados do Bando de Dados
Para autenticação dos usuários em banco de dados é necessário enviar o password real e salvá-lo encriptado, pois o Password Encoder selecionado na classe WebSecurityConfig é o `BCryptPasswordEncoder`. Desse modo encriptamos os passwords com a feramenta [Bcrypt Generator](https://bcrypt-generator.com/).

`Scopes`, os escopos apenas limitam acesso, e devem ser checados no Resource Server. A granularidade é definida conforme a necessidade da API. Os escopos inseridos no token são carregados automaticamente como Authorities, com o JwtAuthenticationConverter padrão.

<b>Segurança em métodos com anotações:</b>
 - `@PreAuthorize` - realiza a verificação antes do método anotado ser executado
  `@PreAuthorize("hasAuthority('SCOPE_READ') and isAuthenticated()")`
 - `@PostAuthorize` - realiza a verificação após o método anotado ser executado, deve ser utilizado se a execução do método não gerar efeito colateral. No seu valor é possível acessar a variável `returnObject` que é uma variável implícita com valor do objeto de retorno do método anotado. Veja lista de expressões para Controle de acesso baseado em Expressões: [Expression-Based Access Control](https://docs.spring.io/spring-security/site/docs/4.1.x/reference/html/el-access.html#el-access). Também é possível [estender as expressões disponíveis](https://docs.spring.io/spring-security/site/docs/4.1.x/reference/html/el-access.html#el-access-web-beans), podendo facilmente referir-se a qualquer Spring Bean que expor, como `@beanName.functionName()`.
  
Observação: No fluxo Client Credentials o cliente se autentica, porém o usuário final não, assim o token jwt não possui usuario_id, informação importante para verificações de segurança.

### Inserindo suporte na Documentação para OAuth2
Na classe de configuração da documentação [SpringFoxConfig](food-api/src/main/java/com/kamila/food/core/springfox/SpringFoxConfig.java) foram adicionadas as configurações no docket:

```java
 .securitySchemes(Arrays.asList(securityScheme()))
 .securityContexts(Arrays.asList(securityContext()))
```

Para autenticação na documentação veja imagem do [formulário de autenticação](/minhas-anotacoes/images/security-formulario-autorizacao-documentacao-swagger.png).

### Customizando página de login
Adicionar dependência Thymeleaf, esta é uma template engine para Java, biblioteca que auxilia na criação de templates de páginas web dinâmicas.

### Customizando página OAuth2 Approval
Sobrescrever implementação em `org.springframework.security.oauth2.provider.endpoint.WhitelabelApprovalEndpoint` do endpoint `@GetMapping("/oauth/confirm_access")`.

### JWKS
JWKS é um conjunto de chaves contendo as chaves públicas usadas para verificar o JWT emitido pelo AS, assinado usando o algoritmo SHA256. Especificado na RFC7517.  Possibilita a remoção dos arquivos de chaves públicas e privadas do código.

##### .pem
Utilizado para que o arquivo `.pem` (da chave pública) não esteja presente no projeto, evitando risco de ser acessado por n desenvolvedores ou acessível por repositório. Outra possibilidade é  realizar a troca de utilização de par de chaves, um para cada ambiente por exemplo, configurando a propriedade `spring.security.oauth2.resourceserver.jwt.jwk-set-uri` no arquivo _application.properties_.<br>
Endpoint chamado para verificar token JWT, expõe a chave pública, inicialmente extraindo do arquivo _food.jks_ na função `keyPair()`. Posteriormente extraido do arquivo em bytes criado a partir de codigo base64. Deve possuir a nomenclatura path padrão:
`http://localhost:8080/.well-known/jwks.json`

##### .jks
Comando no local da pasta "resource/keys" é possível obter o arquivo food.jks codificado por Base64:
 > cat food.jks | base64 // unit
 > type food.jks | openssl base64 // windows

Outra maneira é acessar o site [https://www.base64encode.org/](https://www.base64encode.org/), carregar o arquivo e encodar.<br>
Desse modo a keystore fica armazenada dentro do arquivo em bytes resultante da leitura do código base64 gerado. O código fica em `application.properties` é decodificado para geração do arquivo através da classe `Base64ProtocolResolver`.

## Capítulo 24 - Dockerizando a aplicação
Resolver o problema conhecido como "Matrix from Hell", a configuração da pilha de desenvolvimento necessária para construir um aplicativo ponta a ponta. Conforme seu projeto cresce, as complexidades aumentam e todo o sistema leva meses para concluir uma versão, fazendo com que cada desenvolvedor possua ambiente diferente do outro, e dedique tempo para tarefas não relacionadas ao desenvolvimento.

**Container** - é um pacote que executa dentro dele softwares, bibliotecas e dependências com alto nível de portabilidade de forma repetível e confiável. Compartiha o mesmo kernel do S.O. A imagem de um container se torna a unidade para distribuição da aplicação. "Matrix of Containers". Instância executável de uma imagem.
Um container docker não é uma máquina virtual, não levanta um sistema operacional. Em VMs há o Hypervisor, sistema que gerencia máquinas virtuais.

**Imagem** - Template em camadas somente leitura que contém bibliotecas e configurações podendo ser utilizada por um ou mais containers.

![Containers x Máquina Virtual](/minhas-anotacoes/images/maquina-virtualxcontainer.png)

**Por que usar Docker?**
 - Alto nível de portabilidade
 - Compatibilidade de sistemas operacionais
 - Evita problemas em diferentes ambientes
 - Aumenta a velocidade para desenvolver, construir, testar e atualizar aplicação.

<b>Instalação do Docker:</b> https://docs.docker.com/engine/install/
<b>WSL 2</b> - subsistema do Windows para Linux, permitindo executar ambiente Linux diretamente dentro do Windows.

#### Executando um container
Executando container Nginx `docker container run nginx`, verifica se a imagem existe localmente, caso não exista realiza o download da imagem do docker hub.
`Ctrl+C` interrompe o terminal.
`docker container run -p 8080:80 nginx`, ao acessar a porta 8080 localmente em "http://localhost:8080" o docker direciona para a porta http padrão 80 dentro do container nginx.
Executando container Wordpress `docker container run -p 80:80 wordpress`.

#### Command Line
Comandos de ajuda  `docker help`, `docker container help` e 
[documentação sobre commandline](https://docs.docker.com/engine/reference/commandline/docker/).

 - `docker container ls` - lista os containers em execução
 - `docker container ls --all` - lista todos os containers
 - `docker run -p 80:80 -d wordpress`- argumento <b>-d</b> de *detached*, desanexado, executa o container sem bloquear do terminal
 - `docker container logs <ID>` - visualiza logs exitentes até o momento
 - `docker container logs -f <ID>` - visualiza logs exitentes seguindo (-f follow), monitorando o que está acontecendo
 - `docker container start <ID>` - inicializa o container com imagem local
 - `docker container rm <ID|NAME>` - remove imagem de um container por id ou nome
 - `docker container stop <ID>` - para execução de um container
 - `docker container rm <ID> --force` - para o container e remove sua imagem
 - `docker container rm <NAME_CONTAINER> --force --volumes` - para container, remove a imagem e o volume
 - `docker container prune` - remove todos os containers parados
 - `docker container run -p 80:80 -d --name blogfood wordpress` - cria container com nome definido publicando porta local 80 na porta 80 do container
 - `docker container run -p 80:80 -d --rm --name blogfood wordpress`- parâmetro <b>--rm</b> indica que será removido automaticamente quando o container é parado
 - `docker container run --rm -it ubuntu bash` - parâmetro <b>-it</b> abre terminal interativo acessando linha de comando dentro do container. <b>bash</b> indica qual interpretador de linha de comando irá utilizar. <b>Ctrl+D</b> para sair.
 - `docker image ls` - lista todas as imagens prsentes no ambiente.
 - `docker image pull openjdk:8-jre-slim` - baixa imagem sem executar um container.
 - `docker container run --rm -it openjdk:8-jre-slim bash` - inicia um container com a imagem anteriormente baixada.
 - `docker image rm openjdk:8-jre-slim` - remove imagem
 - `docker image prune`- Ao criar uma imagem com o mesmo nome de outra já existente, a imagem antiga permanece existente sem a associação de nome. Esse comando exclui estas imagens antigas ("penduradas"). Com *--all* exclui todas que não estão sendo usadas.
 - `docker container run -d -p 3306:3306 -e MYSQL_ALLOW_EMPTY_PASSWORD=yes --name food-mysql mysql:8.0` - inicializa container MySql, parâmetro <b>-e</b> habilita passar variável de ambiente *MYSQL_ALLOW_EMPTY_PASSWORD* com valor yes, permitindo que usário root tenha senha vazia.
 - `docker container run -d -p 3307:3306 -e MYSQL_ROOT_PASSWORD=root --name food-mysql mysql` - instancia container na ultima versão do MySql, com password `root` acessado localmente na porta 3307
 - `docker image tag <NAME> <NEW-NAME>` - cria outra imagem apontando para mesmo espaço em memória, com novo nome
 - `docker network ls` -  lista redes existentes, já vem com algumas por padrão
 - `docker network create --driver bridge <NOME_DA_REDE>` - cria rede com driver *bridge*, driver de rede padrão
 - `docker volume ls` - lista volumes, um local para container armazenar arquivos.

#### Arquitetura do Docker
Docker usa uma arquitetura cliente/servidor, onde o **Client** é o terminal, a forma nativa de geranciar containers através de comandos.
**Docker Host** é a máquina onde está instalado o Docker, na qual será executada o Docker. **Docker Daemon* é um software em background que gerencia os objetos do docker, como imagens, containers. Ele possui uma reset API para permitir gerenciamento. Client e DOcker Host podem estar em máquinas diferentes.
**Docker Registry** local para armazenamento de imagens. Caso a imagem não exista localmente o docker local irá baixá-la do Docker Hub. Docker Hub é o registry padrão, repositório em nuvem para compartilhar imagens de containers de forma pública ou privada. 
As tags no Docker Hub (https://hub.docker.com/) podem ser utilizadas logo após o nome da imagem, por exemplo `docker container run ubuntu:22.04`.

![Arquitetura do Docker](/minhas-anotacoes/images/arquitetura-docker.png)

### Construindo a imagem da aplicação com Dockerfile
Gerar `.jar` na pasta target da aplicação, executando na pasta food-api o comando: `mvn clean package`.
Criar arquivo Dockerfile com instruções de criação da imagem.
Para criar imagem, na pasta do projeto food-api, executar: `docker image build -t food-api .` Onde:
 - "-t" - parâmetro para indicar nome:tag da imagem criada
 - " ." - diretório em que está o arquivo Dockerfile, como está na mesma pasta apenas o `.` é necessário

#### Criando uma Rede e conectando containers
Para possibilitar comunicação entre containers da API e do banco de dados criamos uma network através do comando:
`docker network create --driver bridge food-network`.

<b>1. Container MySQL</b>
Instancia um container utilizando a última versão do MySql, com password `root` para usuário `root`, acessado localmente através da porta 3307: `docker container run -d -p 3307:3306 -e MYSQL_ROOT_PASSWORD=root --name food-mysql mysql`.
Para utilizar porta padrão 3306 é necessário parar o processo MySql local em execução nesta porta, para que ela fique disponível.
Comando para conectar container MySQL na rede food-network:
`docker container run -d -p 3306:3306 -e MYSQL_ROOT_PASSWORD=root --network food-network --name food-mysql mysql`.

<b>2. Container API</b>
Executando container da API com variável de ambiente *DB_HOST* correspondente ao container MySQL, conectada à rede *food-network*:
`docker container run --rm -p 8080:8080 -e DB_HOST=food-mysql --network food-network food-api`.

Na IDE Intellij a variável de ambiente foi adicionada inserindo a porta: DB_HOST=localhost:3306
Em application.properties pode ser inserido um valor default utilizando "${}": `spring.datasource.url=jdbc:mysql://${DB_HOST:localhost}....`

### Construindo imagem Docker pelo Maven
[Plugin](https://github.com/spotify/dockerfile-maven) utilizado. Para evitar de sempre que fizermos build do nosso projeto também seja construída uma imagem, criamos um novo `profile` no pom.xml. Onde:

```xml
<configuration>
	<repository>food-api</repository> <!-- Nome da Imagem -->
	<tag>${project.version}</tag> <!-- Nome da tag, sem essa configuração utiliza o padrão 'latest' -->
	<buildArgs> <!-- Argumentos de tempo de compilação -->
		<JAR_FILE>${project.build.finalName}.jar</JAR_FILE> <!-- Nome do arquivo .jar criado pela aplicação na pasta target -->
	</buildArgs>
</configuration>
```

Realiza build do .jar e constrói imagem com `mvn package -Pdocker`, onde "-P" faz referência ao profile do *pom.xml*. Para que uma alteração no projeto seja refletida na imagem docker é necessário realizar o empacotamento Maven.

#### Disponibilizando a imagem da aplicação no Docker Hub
No Docker Hub (https://hub.docker.com/) selecionar Criar Repositório.
Localmente:
 - Alterando a tag da imagem local para adicionar o DockerID, para que o Docker Hub identifique o user - `docker image tag food-api:latest <dockerID>/food-api:latest`.
 - Login - `docker login`
 - Envia a aimagem para DockerHub - `docker push kamilaserpa/food-api:latest`
 - Baixando e executando imagem do Docker Hub - `docker container run --rm -p 8080:8080 -e DB_HOST=food-mysql --network food-network kamilaserpa/food-api`

### Docker Compose
O controle de imagens e containers através da linha de comando pode ter comandos muito longos, passando parâmetros como *n* variáveis, por exemplo. O Docker Compose centraliza configurações possibilitando gerenciamento de vários containers através de arquivo *.yml* ([documentação](https://docs.docker.com/compose/compose-file)). 
 - `docker compose up`
 - `docker compose down --volumes` - para todos os containers e remove volumes criados

#### Controlando ordem de inicialização
Vale ressaltar que `depends on` controla a ordem em que um container inicia ou para em relação a outro, porém o Compoese não espera até um container esteja completamente em execução para iniciar outro, sendo possível que este segundo complete sua inicialização antes do primeir, ainda que sendo iniciado posteriormente.
Na [documentação](https://docs.docker.com/compose/startup-order/) o DOcker indica a utilizaçao da ferramenta `wait-for-it`.
A seguinte linha no docker-compose substitui o `CMD` no Dockerfile:
 > command: ["/wait-for-it.sh", "food-mysql:3306", "-t", "30", "--", "java", "-jar", "api.jar"]

### Escalando um serviço com Docker Compose | Balanceamento de carga
Load Balancer é um software que realiza o balanceamento de carga, distribuindo requisições entre containers. Adiciona segurança caso ocorra problema em um container, outros permanecem em execução. Possibilita *Zero Downtime*, tempo em que um sistema fica inoperacional, quando necessário realizar alguma atualização, um container permanece em execução enquanto outro é atualizado.

![Load Balancer](./minhas-anotacoes/images/load-balance.png)

 - Inicializando duas instâncias de um container:
```yml
    # docker-compose.yml
	food-api:
		image: food-api
		...
		deploy:
		replicas: 2
```
Ou startar o compose com o comando abaixo, com o numero de containers:
>  docker compose up --scale food-api=2

A publicação da porta foi removida do docker-compose pois ocorreria erro de concorrência de porta, com dois containers tentando publicar na mesma porta.

O servidor DNS que tem o Docker Engine implementa o balanceamento de carga com <b>Poor Man's Load Balancer (DNS Round Robin)</b>.

Para verificar isso podemos instanciar um container com distribuição Linux Alpine na mesma rede, para que possamos acessá-lo pelo seu terminal, a fim de acessar os containers do food-api, onde `food-api_food-network` é a rede criada pelo docker Compose:
`docker container run --rm -it --network food-api_food-network alpine sh`. 

 - No terminal iterativo, consultar o servidor DNS por nome: `nslookup food-api`
 - Instalar o curl: `apk add curl`
 - Acessar endpoint e verificar IPs diferentes sendo acessados: `curl http://food-api:8080/hostcheck`.

#### Proxy reverso com Nginx
Um proxy reverso fica na comunicação entre um cliente e um grupo de servidores, onde estes ficam atrás de um mesmo nome:porta, aparecendo como se fossem uma única unidade.
Pode usar uma técnica chamada DNS de revezamento para direcionar solicitações por meio de uma lista rotativa de servidores internos. O <b>Nginx</b> é um servidor Http que realiza proxy reverso.

Para utilizá-lo, configuramos o Dockerfile principal para instanciar um container com a imagem Nginx, utilizando as configurações contidas nos arquivos na pasta [./nginx](/food-api/nginx). Dessa maneira a API fica acessível na **porta 80**.

#### Container Redis
Porém fluxo Authorization Code Grant Type não funciona com esta configuração, pois o Authorization Server utiliza o Http Session na memória do servidor para funcionar, ou seja, fica isolada em um container específico.
Será utilizado o Redis para compartilhar os objetos que estão na sessão entre todos os containers, armazenando estrutura de dados em memória.

Iniciando container cliente do Redis para acessá-lo: `docker container run --rm -it --network food-api_food-network redis:6.2.1-alpine sh`. Em seguida, o comando `redis-cli -h food-redis` dá acesso ao prompt redis-cli, sendo possível visualizar suas chaves armazenadas.

#### Spring Session
[Spring Session](https://spring.io/projects/spring-session) fornece implementações para gerenciar as informações de sessão de um usuário. Spring Session Data Redis, provê suporte para comunicação da API com container Redis.
No docker-compose as seguintes variáveis de ambiente substituem `spring.session.store-type=none` e `spring.redis.host=food-redis` localizadas em *application.properties*.
>   SPRING_SESSION_STORE_TYPE: redis
>   SPRING_REDIS_HOST: food-redis


**Obs.:** Ainda assim o *code* do fluxo Authorization Code Grant Type fica armazenado em memória em um container específico, pois utiliza a implementaçaõ padrão que é *InMemoryAuthorizationCodeServices*. Por isso alteramos a classe AuthorizationServerConfig para implementar <b>JdbcAuthorizationCodeServices</b> persistindo o code em banco de dados na tabela `oauth_code`, podendo ser acessado por quaisquer containers.

## Capítulo 25 - Deploy em containers Docker na Amazon
Adicionar mais organização das propriedades com Spring Profies e remover chaves e senhas que não devem ser versionados por motivos de segurança.

#### Propriedade Condicional
Na classe *StorageConfig* inserimos uma condição, apenas se `food.storage.tipo=s3` são carregadas as demais (food.storage.s3.id-chave-acesso e food.storage.s3.chave-acesso-secreta), assim é possível que em desenvolvimento estas propriedades estejam ausentes.

```java
  @Bean
  @ConditionalOnProperty(name = "food.storage.tipo", havingValue = "s3")
    public AmazonS3 amazonS3() {
```

https://aws.amazon.com/pt/free/
#### Amazon Web Services
[Amazon Web Services (AWS)](https://aws.amazon.com/pt/) é um provedor de nuvem líder do mercado com infraestrutura global. Utilizaremos [nível gratuito](https://aws.amazon.com/pt/free/). Recomendado escolher *região* próxima ao cliente, consultando se os serviços desejados estão disponíveis na região.

Uma [zona de disponibilidade (AZ)](https://aws.amazon.com/pt/about-aws/global-infrastructure/regions_az/) é um ou mais datacenters distintos com energia, rede e conectividade redundantes em uma *região* da AWS. Proporcionam alta disponibilidade.

[Virtual Private Cloud (VPC)](https://docs.aws.amazon.com/pt_br/vpc/latest/userguide/what-is-amazon-vpc.html) rede virtual privada isolada logicamente dentro da nuvem da Amazon, em uma região ela abrange todas as zonas de disponibilidade.

Depois de criar uma VPC, você pode adicionar uma ou mais [sub-redes ou subnets](https://docs.aws.amazon.com/pt_br/vpc/latest/userguide/VPC_Subnets.html#vpc-subnet-basics) em cada zona de disponibilidade. Uma sub-rede é uma gama de endereços IP na VPC.

![AWS conceitos](./minhas-anotacoes/images/aws-conceitos.png)

No AWS Console é possível gerenciar os gastos clicando no nome de usuário, "Biling Dashboard". No menu lateral esquerdo em "Preferências de Faturamento" é possível habilitar as opções para receber alertas de gastos. Em "Budgets" podemos configurar um orçamento para receber um alerta quando os custos estiverem próximos ao limite estabelecido.

Adicionar Bucket e Usuário como descrito no capítulo 14 [Amazon S3](####-amazon-S3).

#### Amazon RSD
Foi utilizado o [Relational Databse Service (Amazon RDS)](https://aws.amazon.com/pt/rds/) para configuração, a operação e o dimensionamento de um **banco de dados relacional na nuvem**. Single-AZ única zona de disponibilidade, Multi-AZ réplica do banco em outra zona de disponibilidade, no caso de acontecimento de uma catástrofe o banco de dados continuar disponível, para fornecer redundância de dados, eliminar congelamentos de E/S e minimizar os picos de latência durante os backups do sistema, manutenção mais cara.

Ao selecionar o serviço `RDS`, acessar no menu lateral "Banco de Dados" e "Criar banco de dados". Na opção Modelos escolher "Free tier" ou "Nível gratuito". Em "Identificador da instância de banco de dados" insira o nome do banco de dados: food-mysql.

"Public Access", ao selecionar *SIM* as instâncias e os dispositivos do Amazon EC2 fora da VPC podem se conectar ao banco de dados por meio de um IP externo, sendo acessível por Dbeaver ou Workbench por exemplo.

"Grupo de segurança" funciona como um firewall virtual, controlando entrada e saída, por exemplo podemos configurar IPs que podem acessar o DB. Selecione Criar novo e identifique com "food-mysql-sg".

#### VPC
Selecionar o serviço VPC, Security, Security Groups é possível verificar o `food-mysql-sg`, e que o Ip de entrada permitido é o IP do meu usuário ao criar o banco de dados. É possível alterar essa configuração em "Edit Inbound rules" ou "Editar regras de entrada".

Acessando o banco de dados via Dbeaver, criamos o schema "food", e o usuário "food-api" com todos os provilégios para o banco *food-api*. O host e porta ficam visíveis ao acessar o serviço RDS > Banco de Dados, clicar sobre o nome do banco é possível visualizar Endpoint e porta.


#### Memória Redis
Servidor Redis utilizado é o [Redislabs](https://redislabs.com/). Outra possibilidade é o [Amazon ElastiCache](https://aws.amazon.com/pt/elasticache/), porém ele dificulta o acesso de fora da VPC.

Acessando banco de dados criado através de um cliente Redis instanciado em um container local: `docker container run --rm -it redis:6.0.10-alpine redis-cli -h <public endpoint> -p 12609`

Dentro do container cliente redis: `auth <password>`, para autenticação.

#### Amazon Elastic Container Service
É um serviço gerenciado de orquestração de coitainers. Necessário configurar um cluster (conjunto de resursos computacionais, servidores) para executar os containers, há dois tipos de clusters, clusters de instância do EC2 e clusters do Fargate. Com o EC2 a cobrança é realizada pelas máquinas virtuais, necessário realizar o gerenciamento dos servidores. Já pelo Fargate o valor é por tempo de execução e recursos do container, mecanismo de execução dos containers sem servidores, gerenciado pela Amazon. O item selecionado no curso foi o Fargate, porém não está incluso no nível gratuito.

![ECS Diagrama](/minhas-anotacoes/images/aws-ecs-diagram.PNG)

Acessar serviço *ECS*, selecionar `Task Definition`, que descreve configurações de um ou mais containers, como a imagem utilizada, mapeamento de portas, variáveis de ambiente, etc. Em *Task memory*, selecionar menor memória necessária pois é cobrada. Uma task definition pode ter um ou mais containers. Adicionamos um container, com o nome `nginx-container`, com imagem *nginx:1.19.8-alpine*. Em *Port Mapping* inserimos a porta que desejamos expor: 80. Selecionamos criar container e criar Task Definition. Nesse momento o container ainda não está em execução, uma *task* é uma instância em execução de uma *task definition*, instância de um container em execução, para executá-la é necessário um cluster.

Acessar no menu lateral `Amazon ECS > Cluster`, create cluster, Network Only. EM cluster name inserimos *food-cluster*, não é necessário criar a VPC, selecionar *create*. `Services` no contexto do AWS EC2, é um serviço que mantém uma task continuamente em execução, caso ocorra um problema no container e ele caia, o service o reinicia. Ao visualizar o cluster selecionar a tab Service e criar um service. Selecione o cluster VPC que aparece nas opções e editar  `Security Group`, alteramos o nome para "nginx-sg". "Auto-assign public IP" enabled pois desejamos acessar o container de forma externa através da Internet.

Ao visualizar um cluster em execução vemos a tag "Services" o item "Desired tasks" onde consta o número desejado de task, ou seja, de containers em eexecução. <b>Para parar um container</b> selecione o service, clique em `Update`, e insira "Number of task" valor zero (0), pois se o usuário parar a task e o valor desejado (number of task) for 1, por exemplo, o próprio ECS irá levantar novamente para ficar com uma instância sendo executada.

![AWS Service](/minhas-anotacoes/images/aws-cluster-service.PNG)

Caso a porta esteja padrão TCP 80, altere para Custom TCP e insira a porta desejada, 8080, por exemplo.

![Configurando porta no Security Group](/minhas-anotacoes/images/aws-security-group-food-api.PNG)

#### Amazon Elastic Container Registry
Para utilizar uma imagem é necessário tê-la em algum Registry. Para isso será utilizado o [Amazon Elastic Container Registry (ECR)](https://aws.amazon.com/pt/ecr/). É possível utilizar o Docker Hub, porém espera-se que a integração com serviços da Amazon e gerenciamento sejam mais simples com o ECR.

Criar novo repositório, privado, nomeamos como `food-api`, Create repository. Para inserir imagem selecione o repositório criado e "View push commands", serão exibidos comandos para inserir imagens no Registry. Necessário instalar <b>AWS Command Line Interface</b>. 

Com Aws Cli instalado digitamos `aws configure`, e nos é solicitado *access key id*. Para isso acessamos o serviço "IAM" no Aws Console, <b>adicionamso outro usuário</b> apenas para a finalidade de utilização do Aws Cli. Habilitamos "Chave de acesso: acesso programático". Em "Anexar políticas existentes de forma direta", busque e adicione "AmazonEC2ContainerRegistryPowerUser", assim damos permissão para este usuário gerenciar os serviços EC2. Assim obtemos o Secret Access Key e Access Key Id. Inserimos no prompt e teclamos Enter nas demais opções (region name, output format). Após isso visualizamos novamente "View push commands".

> aws ecr get-login-password --region us-east-2 | docker login --username AWS --password-stdin ************

Criamos a imagem docker do projeto com `mvn package -Pdocker` para realizar upload dela no ECR. Vamos tagea-lá segundo comando disponível no ECR:
 > docker tag food-api:latest ************.dkr.ecr.us-east-2.amazonaws.com/food-api:latest

 E, seguida realizamos o push da imagem para Amazon ECR:
 > docker push ********.dkr.ecr.us-east-2.amazonaws.com/food-api:latest

Ao clicar no nome do repositório poderemos ver a imagem disponível.

Criamos um arquivo jks para produção (123456@prod)

#### Systems Manager - Gerenciamento de senhas e configurações
Podemos inserir os valores das variáveis de ambiente ao criar uma task, no formato chave valor. Para qualquer alteração deveríamos alterar a task, e para qualquer usuário com acesso ficariam visíveis valores mensíveis. Para gerenciar configurações e senhas a Amazon fornece a ferramenta **Systems Manager > Parameter Store**, ou "Armazenamento de parâmetros".

Ao criar um parâmetro vamos editar a `ECS/Task Definition`, inserir a key da variável de ambiente, e no value inserimos o path do parâmetro criado anteriormente. No campo selection do meio selecionamos `ValueFrom`.

Criamos uma Task Definition para o container de Food API. Adicionamos a imagem `695220093988.dkr.ecr.us-east-2.amazonaws.com/food-api`. Em *Port mappings* inserimos 8080. Deve ser inserida também a variável `SPRING_PROFILES_ACTIVE = production`.

![Variáveis de ambiente no ECS](./minhas-anotacoes/images/ecs-environment-variables.PNG)

##### Habilitando acesso aos parâmetros
Acessando clusters, criamos um novo service para englobar a Task Definition do Food API. `AccessDeniedException` foi recebido pois o service ECS não tinha permissão para acessar o recurso `Parameters Store`.
Para dar essa permissão acessamos o serviço <b>IAM</b>, Roles (Funções), e buscamos a role *"ecsTaskExecutionRole"*. Essa é a role que o service utiliza para acessar outros recursos na Amazon. Clicando na role podemos ver mais detalhes, selecionamos "Add inline policy". Na aba "Serviço" selecionamos "Systems Manager", e em Ações> Níveis de acesso> Leitura, habilitamos: GetParameter, GetParameters, GetParametersByPath. Em "Recursos" adicionaos "ARN" e na opção "Parameter name without leading slash" inserimos o valor `prod/*`, para permitir acesso apenas aos parâmetros inseridos nesse path.

Para ver os Logs acesse *ECS > CLusters*, clique no id da *Task*, na aba *Containers*, clique na seta e expanda o container, será exibido link `View logs in CloudWatch`, clique e acesse os logs.

#### Permitindo o acesso ao MySQL pelo Security Group do serviço do Amazon ECS
`CommunicationsException` indica que a aplicação não está conseguindo se conectar com o banco MySQL. Algumas possibilidades são: erro de variável host, porta, password; ou bloqueio de permissão de acesso ao banco de dados.

Para dar permissão de acesso ao banco selecione o serviço da AWS "VPC", aba no menu lateral "Security Groups", Editar regras de entrada, adicionar nova regra. No campo "Origem" buscamos e selecionamos "food-api-sg", desse modo damos permissão para o grupo `food-api-sg` de acesso ao banco de dados.

Para testar nossa API clicamos no ID da task, e visualizamos o **Public IP**. Através do acesso remoto do banco de dados inserimos dados no banco de produção e acessamos os endpoints através do IP publico de produção.

#### Amazon Elastic Load Balancing - Balanceamento de carga
O balanceamento de carga é importante porque precisamos distribuir o processamento das requisições em diversos containers. Pois o ideal é ter no mínimo dois containers em zonas de disponibilidade diferentes, distribuindo a carga para que nenhum dos containers fique sobrecarregado, possibilizando proteção de falhas, escalabilidade, Zero Downtime Deployment ("Deploy sem interrupção").

[Amazon Elastic Load Balancing](https://aws.amazon.com/pt/elasticloadbalancing/) é uma funcionalidade do produto <b>EC2</b>. ELB é um único ponto de contato com os clientes e distribui as requisições para os vários containers através de um algoritmo. <b>Listeners</b> são processos que aguardam as requisições de determinado protocolo e porta, neles estão Rules, regras que definem o <b>Target Group</b> para qual será roteada a requisição. O Target Group envia para um container saudável, apto a responder, essa verificação é feita através da configuração *Health Check*, podemos configurar um endpoint para essa verificação.

![ELB](./minhas-anotacoes/images/aws-elastic-load-balancing.png)

##### Configurando Load Balancer na Amazon
<b>EC2 > Load Balancers > Criar novo</b>. Como nossas requisições utilizam o HTTP selecionamos "Application Load Balancer". Como nome inserimos "food-lb". *Scheme* selecionamos *Internet-facing* pois desejamos que os load balancer esteja exposto na internet para os clientes da API. Em "Listeners and routing" deixamos HTTP 80 como padrão. Selecionamos pelo menos duas zonas de disponibilidade em "Network mapping" para habilitar o Multi-AZ.

Criamos novo <b>Target Group</b> (Grupo de Destino) chamado "food-api-service-tg", para agrupar as instâncias em execução dentro do serviço food-service. Em "target type" selecionamos IP, pois a forma com que o ECS s eintegra com o Load Balancer é através do IP, caso fossem instâncias do EC2 deveria ser selecionado "Instance". Selecionamos Http, 80.
Em "Health Check" inserimos um path em que ele vai acessar e obter resposta de sucesso, sem autenticação, inserimos "/v1". Habilitamos "Trafic port", "Healthy threshold" selecionamos 2, pois é o número de checagens com sucesso com que o Target será considerado saudável. Em "Unhealthy threshold" deixamos 2 também, caso com duas requests com falha o target é considerado não saudável. Em Timeout selecionamos 10 segundos para ser considerado erro. "Interval" selecionamos o intervalo de tempo de frequência de testes. Em next seremos encaminhados para "Register Targets", porém as instâncias serão adicionadas pelo próprio Amazon ECS, por isso não iremos adicionar nenhum target nesse momento. Em "Listeners and routing" selecionamso o target group criado.

"Create new security group" e criamos "food-lb-sg", com regra de entrada TCP, porta 80, de qualquer lugar. Create! Podemos acessar através do nome do DNS, porém recebemos 503 pois ainda não há targets no grupo.

##### Configurando o balanceamento de carga no Service do Amazon ECS
Para adicionar um Load Balancer no Service, devemos fazer isso na criação do Service. Excluímod o "food-aservice" e criamos o `food-api-service`, com duas tasks para utilizar o balanceamento. Selecionando pelo menos duas subnets (zonas de disponibilidade). Editamos o Security group e selecionamos o previamente criado `food-api-service-sg`.

"Em Load Balancer type" selecionamos "Application Load Balancer", e em "Health check grace period" inserimos um tempo de intervalo de verificação de saúde da task, deve ter um tempo suficiente pra aplicação levantar e estar pronta para responder. Inserimos 150s. 

Na aba referente ao container selecionamos "Add to load balancer" para adicionar o container ao balanceamento de carga.
Em `Production listener port`selecionamos *80:HTTP*, e selecionamos o Target group "food-api-service-tg". Next > create service.
Acessando `EC2 > Load Balancer`acessamos o balancer e capturamos o endereço DNS para acesso via http, que já pode ser acessado na Web.

Para <b>parar uma task</b> podemos acessar o ECS, cluster, visualizar e clicar no Id do target, clicar em `Stop`no canto direito superior. O service indica a quantidade desejada e starta automaticamente um target caso esteja configurado para isso.

<b>Removendo acesso direto ao Container</b>
Para que a comunicação fique limitada ao acesso via Load balancer vamos remover a possibilidade de acessar diretamente os containers. Acessamos `VPC > Security Groups > food-api-service` > Regras de entrada, e excluímos a regra de entrada anterior e adicionamos a regra de entrada apenas para o Balancer `TCP:8080, food-lb-sg`.

##### Registrando um domínio de internet no Registro.br
A instituição de reserva de nomes de Domínios (DNSs) selecionada foi `Registro.br` (https://registro.br/). É uma aquisição financeira e o valor de disponibilização de 1 ano nesta data é de R$ 40,00. Outras opções são o Cloudflare, e Aws Route 53.

##### Configurando o domínio para o Application Load Balancer
Após o domínio registrado é necessário um serviço DNS, é o responsável por converter nomes de sites em IPs numéricos. Um desses produtos é o `AWS Route 53`, o Registro.br tbm possui esta funcionalidade.
Na página de configuração do domínio no registro.br, selecionamos "Editar zona" > "Nova entrada", e adicionamos o Ip do Load Balancer da AWS. Na configuração inicial deve ser mais rápida a criação, para alteração pode demorar dias:

![Configuração do DNS no RegistroBr para Load Balancer DNS](./minhas-anotacoes/images/dns-registro-br.png)
 Já podemos acessar as requests via Postman através do DNS.
 Vamos retirar o acesso pelo ip do Load Balancer. Para isso acessamos EC2 > LoadBalancer > Listeners > Visualizar/editar regras > Adicionar nova regra. EM *"Host header"* inserimos o dns (p.e "kamilafood.com.br"), *"Forward to"* target group do load Balancer "food-api-service-tg".
 E a regra anterior alteramos para retornar 503, removendo o acesso que não utilize o DNS.

![Acesso via DNS no Load Balancer](./minhas-anotacoes/images/load-balancer-acesso-via-dns.png)

*Obs.: Não removemos o acesso via DNS do Load Balancer pois não adquirimos um DNS privado.*

### TLS (HTTPS) com AWS Certificate Manager
Para configurar o protocolo HTTPS, e deixar a aplicação mais protegida contra ataques, precisamos emitir um certificado certificado TLS (HTTPS), utilizaremos o `AWS Certificate Manager`. Solicitar um certificado público (o privado é pago).

No nome de domínio inserimos: *.kamilafood.com.br.
Selecionar método de validação: Validação de email
Após isso será enviado um e-mail com link para aprovação do certificado.

<b>Adicionando Certificado no Load Balancer</b>
Em EC2 > LoadBalancer > Listeners, vamos adicionar listener. Protocolo HTTPS, Return fixed response, Default SSL certificate, selecione o certificado criado anteriormente nas opções de busca.
Clicamos no listener a adicionamos nova Regra (new rule). Host header: kamilafood.com.br, target group:food-api-service-tg.
Após isso temos dois listeners, na porta 80 e outro na 443, caso não usado o na porta 80 pode sre excluído.

Editar o security group de food-lb-sg, editamos a regra de entrada para permitir acesso ao protocolo HTTPS, TCP, 443 de qualquer lugar.

<b>Ajuste no HATEOAS</b>

![Fluxo básico de comunicação](./minhas-anotacoes/images/header-protocolo-load-balancer.png)

Um problema provocado foram os links do HATEOAS sendo recebidos pelo consumidor sem https, por isso não funcionam. Apesar de o Load Balancer encaminhar ao container, através do header `X-Forwarded-Proto`, o protocolo HTTPS recebido pelo cliente, a aplicação não está configurada para retornar o protocolo recebido do cabeçalho nos links do HATEOAS. Então retorna o protocolo utilizado na comunicação entre Load Balancer e Container, que é HTTP.

Adicionamos a propriedade abaixo para utilizar o suporte do Spring pra tratar os cabeçalhos forwarded, permitindo que ele identifique os *headers*  `X-Forwarded-Host` e `X-Forwarded-Proto`. Desse modo a aplicação passa a utilizar estes atributos para montar os links do HATEOAS.

Sem essa propriedade era recebido sempre http nos links, p.e. `http://localhost:8080`.

> server.forward-headers-strategy=framework

Após isso devemos empacotar novamente a aplicação com `mvn package -Pdocker`, acessar o <b>AWS Elastic Container Registry</b>, em Repositories, e capturar o comandos push, executar localmente para fazer upload da imagem. Em seguida acessamos os clusters em ECS no menu lateral, selecionamos o service `food-service`, habilitamos Force new deployment, para que seja atualizado para a nova imagem do container.


---

### Notas

##### Spring Cloud Sleuth
O Spring Cloud Sleuth fornece configuração automática do Spring Boot para rastreamento distribuído, enriquecendo os logs da aplicação.
Tabela de compatibilidade de versões do Spring Cloud com o Spring Boot em https://spring.io/projects/spring-cloud#overview.

##### Eclipse

###### UTF-8 (9.18)
Acessar Window > Preferences > Content Types. Para arquivos `.properties` selecione Text > Java Properties File / Spring Properties File. Em Default encoding inserir "UTF-8", para evitar caracteres especiais não reconhecidos nas mensagens em `messages.properties`.

Sobrescrevendo propriedades (application.properties), clique com lado direito sobre o projeto no console (Boot Dashboard), Open Config, adicione a propriedade e o valor no bloco "Override properties".

##### Intellij
Inserindo profile: Clicar na seta ao lado do nome do executável, Editar as configurações de build (Edit Configurations), Modify Options, Add VM options, inserir "-Dspring.profiles.active=production".
Outra forma seria, na mesma janela de configuração, adicionar a variável de ambiente `spring.profiles.active=production`.

![Intellij Profiles](./minhas-anotacoes/images/intellij-profiles.png)


###### Importação Estática
Para importação estática de métodos insira o local do arquivo em:
> Window > Preferences > Java > Editor > Content Assist > Favorites > New Type

Por exemplo, adicionando a biblioteca `org.hamcrest.Matchers` em types, é possível fazer a importação estática de métodos dessa maneira, como o método hasSize, por exemplo, da seguinte maneira:

`import static org.hamcrest.Matchers.hasSize;`


