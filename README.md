# API de Avaliação Física

API REST desenvolvida com **Java 17 e Spring Boot 4.1.2** para gerenciamento de alunos, treinos, exercícios e avaliações físicas. Utiliza Spring Data JPA para persistência de dados, MySQL como banco de dados e Spring Security com JWT para autenticação.

## Como executar o projeto

### Pré-requisitos

Antes de executar a aplicação, é necessário ter instalado:

- Java JDK 17 ou superior.
- Maven.
- MySQL.

### 1. Clonar o repositório

```bash
git clone https://github.com/MarcuSantosDev/avaliacao-fisica-Spring-JPA-JWT.git
```

### 2. Acessar o diretório do projeto

```bash
cd avaliacao-fisica-Spring-JPA-JWT
```

### 3. Configurar o banco de dados

Crie um banco de dados MySQL e configure as credenciais de acesso no arquivo:

```text
src/main/resources/application.properties
```

Exemplo de configuração:

```properties
spring.application.name=avaliacao-fisica
server.port=8080

# Database configuration
spring.datasource.url=jdbc:mysql://localhost:3306/gym?createDatabaseIfNotExist=true
spring.datasource.username=${DATABASE_USER:root}
spring.datasource.password=${DATABASE_PASSWORD}

# JPA configuration
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

# JWT Configuration
security.jwt.secret=${JWT_SECRET}
security.jwt.expiration=900000
```

Configure as variáveis de ambiente `DATABASE_PASSWORD` e `JWT_SECRET` de acordo com seu ambiente.

**Importante:** a variável `JWT_SECRET` deve conter uma chave secreta codificada em Base64, com tamanho adequado para o algoritmo de assinatura utilizado. Não compartilhe essa chave nem a envie para o repositório.

A propriedade `security.jwt.expiration` define o tempo de validade do token em milissegundos. O valor `900000` corresponde a 15 minutos.

### 4. Executar a aplicação

Utilizando o Maven:

```bash
mvn spring-boot:run
```

Ou, utilizando o Maven Wrapper:

**Windows:**

```powershell
.\mvnw.cmd spring-boot:run
```

**Linux/macOS:**

```bash
./mvnw spring-boot:run
```

Após a inicialização, a API estará disponível em:

```text
http://localhost:8080
```

### 5. Acessar a documentação da API

A documentação interativa dos endpoints está disponível por meio do Swagger UI:

```text
http://localhost:8080/swagger-ui/index.html
```

## Tecnologias utilizadas

- Java 17
- Spring Boot 4.1.2
- Spring Web MVC
- Spring Data JPA
- Hibernate
- Spring Security
- JSON Web Token (JWT)
- MySQL
- Maven
- Lombok
- Jakarta Bean Validation
- Springdoc OpenAPI

## Funcionalidades

### Gerenciamento de alunos

- Cadastro de alunos.
- Listagem de alunos.
- Consulta da avaliação física de um aluno.
- Remoção de alunos.
- Validação dos dados de cadastro.
- Verificação de e-mail já cadastrado.

### Gerenciamento de avaliações físicas

- Cadastro de avaliações físicas.
- Listagem de avaliações.
- Consulta de avaliações com paginação.
- Associação de avaliações aos alunos.
- Registro de peso, altura e percentual de gordura corporal.

### Gerenciamento de treinos

- Cadastro de treinos.
- Associação de treinos aos alunos.
- Associação de exercícios aos treinos.

### Gerenciamento de exercícios

- Cadastro de exercícios.
- Listagem de exercícios.
- Consulta de exercícios por grupo muscular.

### Segurança e autenticação

- Integração com Spring Security.
- Implementação de autenticação baseada em JWT.
- Geração de tokens assinados.
- Validação da assinatura e expiração dos tokens.
- Configuração de chave secreta por variável de ambiente.

## Endpoints

Base URL:

```text
http://localhost:8080
```

### Alunos

| Método | Endpoint | Descrição |
|---|---|---|
| GET | `/v1/alunos` | Listar alunos |
| POST | `/v1/alunos` | Cadastrar aluno |
| GET | `/v1/alunos/{alunoId}/avaliacao` | Consultar avaliação física de um aluno |
| DELETE | `/v1/alunos/{alunoId}` | Remover aluno |

### Avaliações físicas

| Método | Endpoint | Descrição |
|---|---|---|
| GET | `/v1/avaliacoes` | Listar avaliações físicas |
| POST | `/v1/avaliacoes` | Cadastrar avaliação física |
| GET | `/v1/avaliacoes/page/{page}/size/{size}` | Listar avaliações com paginação |

### Treinos

| Método | Endpoint | Descrição |
|---|---|---|
| POST | `/v1/treinos` | Cadastrar treino |

### Exercícios

| Método | Endpoint | Descrição |
|---|---|---|
| GET | `/v1/exercicios` | Listar exercícios |
| POST | `/v1/exercicios` | Cadastrar exercício |
| GET | `/v1/exercicios/grupos/{grupoMuscular}` | Buscar exercícios por grupo muscular |

## Exemplos de requisições

### Cadastrar aluno

`POST /v1/alunos`

```json
{
  "nome": "Marcus Santos",
  "email": "marcus@gmail.com"
}
```

### Cadastrar avaliação física

`POST /v1/avaliacoes`

```json
{
  "alunoId": 1,
  "peso": 75.50,
  "altura": 1.75,
  "porcentagemGorduraCorporal": 18.50
}
```

## Arquitetura do projeto

O projeto utiliza arquitetura em camadas, separando as responsabilidades da aplicação.

```text
src/main/java/com/marcusantosproject/avaliacaofisica
│
├── config/
│   └── TokenProvider.java
│
├── controller/
│   ├── AlunoController.java
│   ├── AvaliacoesFisicarController.java
│   ├── ExerciciosController.java
│   └── TreinoController.java
│
├── database/
│   ├── model/
│   │   ├── AlunosEntity.java
│   │   ├── AvaliacoesFisicasEntity.java
│   │   ├── ExerciciosEntity.java
│   │   ├── TreinosEntity.java
│   │   └── RolesEntity.java
│   │
│   └── repository/
│       ├── AlunosRepository.java
│       ├── AvaliacoesFisicasRepository.java
│       ├── ExerciciosRepository.java
│       ├── TreinosRepository.java
│       └── RolesRepository.java
│
├── dto/
│   ├── AlunoDto.java
│   ├── AvaliacaoFisicaDto.java
│   ├── AvaliacoesFisicasProjection.java
│   ├── ExerciciosDto.java
│   └── TreinoDto.java
│
├── exceptions/
│   ├── BadRequestException.java
│   └── NotFoundException.java
│
├── handler/
│   ├── GlobalExceptionHandler.java
│   └── StandardError.java
│
└── service/
    ├── AlunosService.java
    ├── AvaliacaoFisicaService.java
    ├── ExerciciosService.java
    └── TreinoService.java
```

### Responsabilidades das camadas

| Camada | Responsabilidade |
|---|---|
| Controller | Receber e responder às requisições HTTP. |
| Service | Implementar as regras de negócio. |
| Model | Representar as entidades do banco de dados. |
| Repository | Realizar operações de persistência. |
| DTO | Transportar e validar dados entre as camadas. |
| Exceptions | Definir exceções personalizadas. |
| Handler | Centralizar o tratamento de exceções. |
| Config | Concentrar configurações da aplicação e componentes de segurança. |

## Conceitos aplicados

- Programação Orientada a Objetos.
- Desenvolvimento de APIs REST.
- Arquitetura em camadas.
- Mapeamento objeto-relacional (ORM).
- Relacionamentos entre entidades JPA.
- DTOs e Projections.
- Consultas SQL nativas.
- Validação de dados.
- Tratamento global de exceções.
- Gerenciamento de transações.
- Paginação com `Pageable` e `Page`.
- Java Streams e Lambda Expressions.
- Autenticação e segurança com Spring Security.
- JSON Web Token (JWT).

## Autor

Desenvolvido como projeto de estudo e prática de desenvolvimento Back-End com Java e Spring Boot.
