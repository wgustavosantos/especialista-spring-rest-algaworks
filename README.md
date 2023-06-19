# Curso Especialista Spring Rest - REST API desenvolvida com Spring Boot seguindo as melhoras práticas do mercado.

Neste curso partimos do básico como os CRUDS, e vamos muito além, com tópicos avançados como:

## Funcionamento do Spring e o seu Ecossistema
> Funcionamento do IOC Container e todo o Ecossistema Spring

> Maven como gerenciador de dependência do projeto

> Criação e gerenciamento(propriedade) de Beans, pontos de injeção, ambiguidade e injeção de listas de Beans, Metódos callback e ciclos de vida de Beans

> Spring Profiles, propriedades customizadas e acessando via @Value e classes de propriedades(@ConfigurationProperties), variáveis de ambiente

## JPA e Hibernate
> Relacionamentos, Entity Manager, @Transactional, Estados de uma entidade

>Classes Embedded e Embeddable, fetching com Eager e Lazy Loading, soluções de problemas N+1 com fetch join em JPQL

## Modelo arquitetural REST com Spring
 > Níveis do REST (Richardson Maturity Model)Protocolo HTTP, Singleton Resource, Collection Resource
 
 >Content Negotiation JSON ou XML, Verbos HTTP, anotações da biblioteca Jackson, ResponseEntity, payload de requisições

## Spring Data JPA
> Consultas JPQL, keywords e prefixos Query Methods, JPQL em arquivos xml

> Repositório do Spring Data JPA customizado, Criteria API, Specficications pattern (DDD) com S.D.JPA, Spec Factory, repositório base customizado (JpaRepository)

## Flyway e Pool de Conexões
>Configurando e gerenciando o Pool de Conexões do Spring HikariCP, Flyway e migrações do banco de dados

## Tratamento e Modelagem de Erros
>Exceções customizadas com @ResponseStatus, granularidade e hierarquia de exceptions de negócio

>Exceções em nível de controlador com @ExceptionHandler, globais com @ControllerAdvice

>Exception global estendendo ResponseEntityExceptionHandler, exceções padronizadas com a RFC 7807 (Problem Details for HTTP APIs)

## Validações com Bean Validation
> @Valid para constraints de validações e grupos de validações com @ConvertGroup, mensagens de validação na resposta customizadas usando Resource Bundle

## Boas práticas e técnicas para APIs
> Payload de request refinadas com @JsonIgnoreProperties, boas práticas com Data/Hora em apis usando UTC

> Domain Model isolado da camada de controlador com DTO, conversões de DTO para domain model com ModelMapper, conceito e assembler e disassembler

# Modelagem Avançada e Implementação da API
> mapeamento endpoints para subrecursos, granularidade de recursos: Chatty vs Chunky APIs, conceitos abstratos não-CRUD

> Querys otimizadas em recursos específicos, UUIDs em URIs de recursos

## Modelagem de Projeções, Pesquisas e Relatórios
> Ordenação e paginação de recursos e Projeção com @JsonView, @JsonFilter e Squiggly

> Pesquisas complexas na API, tratamento de parâmetros inválidos em URL

> Dados agregados, layout de relatórios de recursos com JasperReports, populando relatório com JasperReports

## Upload e Download de Arquivos
> SOluções para upload de arquivos, upload multipart/form-data, max size files

> Implementação de Content Negotiation para endpoints retornarem JSON ou arquivos, criação e gerenciamento de buckets na Amazon S3

> Separação de criação de bean para cada tipo de armazenamento (storage local ou nuvem S3)

## Envio de Emails Transacionais e Domain Events
> Implementação de serviço SMTP sendgrid, separação de bean para envio de mocks e emails reais

> Observando e reagindo a Domain Events para envio de email a partir de Aggregate Root

## CORS e Consumo da API
> Conceitos de CrossOrigin e Same Origin Policy, criação de clients consumidores de endpoints em JS

## Cache de HTTP

> Cache Control, Shallow ETags e Deep ETags, diretiva max-age

## HATEOAS e Discoverability
> Spring HATEOAS, Discoverability, HAL e adicionando links na própria documentação SpringFox(Alterada posteriormente para SpringDoc)

> Root Entry Point, formatos hypermedia e links de endpoints

> Comprimindo responses HTTP com Gzip

## Documentação da API com OpenAPI, Swagger UI, SpringFox e SpringDoc
> Boas práticas de documentação e modelagem de modelos nas representações, uso da especificação OpenAPI

> Descrevendo Tags na documentação, parâmetros obrigatórios e respostas

## Evoluindo e Versionando a API
> Gestão de Mudanças, práticas para evitar quebrar clientes consumidores

> Uso de Retrocompatibilidade e criação de versões de endpoints

>Versionamento por Media Type e URI, depreciado endpoints e desligando versão de API

## Segurança com Spring Security(Stack antiga) e OAuth2
> Introdução ao OAuth2, Autenticação de Usuários em Memória, Bas64

> Authorization Server e Resource Server acoplados e desacoplados, endpoint de introspecção de tokens, estrutura de tokens JWT, Refresh Tokens

> Fuxos de Segurança: Resource Owner Password Credentials, Client Credentials, Authorization Code, Implicit

> Authorization Code + PKCE método plain e SHA-256

## OAuth2 avançado com JWT e controle de acesso a endpoints
> Armazenamento de token jwt no Redis, RedisTokenStore

> Aplicações Stateful e Stateless

> Transparent Tokens JWT, JWT com chave assimétrica (HMAC SHA-256), par de chaves Keytool, assinando JWT com RSA-256, chaves públicas formato PEM

> Claims customizadas, permissões de acesso, Granted Authorities, @PreAuthorize e SpEL

## testes de integração
> JUnit, Teste de Integração com RestAssured

## Logging
> Logback e SLF4J, registrando logs na nuvem com Loggly

## Dockerizando a aplicação
> Principais conceitos de imagens e containers e sua arquitetura

>Criação de networks e gerencia de vários containers, build de image pelo Maven, escalando serviços com Docker Compose e ordenação de inicialização com wait-for-it.sh

> Poor Man's Load Balancer (DNS Round Robin), Proxy reverso com Nginx, Spring Data Redis, Mysql, fatjar (java), múltiplas instâncias

## Deploy em containers Docker na Amazon
> Deployment em produção, organização de projetos com Spring Profiles

> Bucket no S3, instância do MySQL no Amazon RDS, Instância do Redis no Redislabs, ECS e AWS Fargate, imagem docker no ECR, criação de Security Groups

> Variáveis de ambiente no AWS System Manager Parameter Store, Elastic Load Balancing, configurando certificado TLS (HTTPS)

## Spring Authorization Server

> Troca da antiga stack do Spring Security Oauth para Spring Authorization Server

> fluxos de segurança, Authorization Code + PKCE + S256, ajustando Spring Boot 3 e a nova stack de segurança

> Configuração do Auth Server e Resource Server com token JWT

## Spring Boot 3
> As principais mudanças do Spring Boot 3

> Atualização do Spring Doc e Authorization Server

## Conteúdo programático
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
