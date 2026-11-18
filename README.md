# API de Avaliação Física

API REST desenvolvida com **Java 17 e Spring Boot 4.1.2** para gerenciamento de alunos, treinos, exercícios e avaliações físicas. Utiliza Spring Data JPA para persistência de dados, MySQL como banco de dados e Spring Security com JWT para autenticação e autorização.

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

Configure as credenciais de acesso ao MySQL no arquivo:

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

### 4. Gerar a chave secreta JWT

A aplicação utiliza uma chave secreta codificada em Base64 para assinar e validar os tokens JWT.

Cada pessoa deve gerar sua própria chave secreta.

**Windows (PowerShell):**

Execute os comandos abaixo:

```powershell
$bytes = New-Object byte[] 32
$rng = [System.Security.Cryptography.RandomNumberGenerator]::Create()
$rng.GetBytes($bytes)
[Convert]::ToBase64String($bytes)
$rng.Dispose()
```

**Linux/macOS:**

```bash
openssl rand -base64 32
```

Após gerar a chave, configure-a na variável de ambiente `JWT_SECRET`.

**Windows (PowerShell):**

```powershell
$env:JWT_SECRET="COLE_A_CHAVE_GERADA_AQUI"
```

**Linux/macOS:**

```bash
export JWT_SECRET="COLE_A_CHAVE_GERADA_AQUI"
```

Configure também a variável `DATABASE_PASSWORD` com a senha do seu banco MySQL.

**Importante:**

- Gere uma chave própria para cada ambiente.
- Não compartilhe sua chave secreta.
- Nunca envie a chave para o GitHub.
- A chave deve possuir pelo menos 256 bits (32 bytes) antes da codificação Base64.

A propriedade `security.jwt.expiration` define o tempo de validade do token em milissegundos. O valor `900000` corresponde a 15 minutos.

### 5. Executar a aplicação

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

### 6. Acessar a documentação da API

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
- Autenticação baseada em JWT.
- Cadastro e autenticação de usuários.
- Geração de tokens JWT assinados.
- Validação da assinatura e expiração dos tokens.
- Filtro para autenticação das requisições.
- Controle de acesso baseado em roles.
- Configuração de chave secreta por variável de ambiente.

### Tratamento de exceções

- Exceções personalizadas para erros de negócio.
- Tratamento global de exceções.
- Padronização das respostas de erro.
- Retorno de códigos HTTP apropriados.

## Endpoints

Base URL:

```text
http://localhost:8080
```

### Autenticação

| Método | Endpoint | Descrição |
|---|---|---|
| POST | `/v1/auth/**` | Endpoints de autenticação |

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
├── AvaliacaoFisicaApplication.java
│
├── config/
│   ├── JwtAuthenticationFilter.java
│   ├── SecurityConfiguration.java
│   └── TokenProvider.java
│
├── controller/
│   ├── AlunoController.java
│   ├── AuthController.java
│   ├── AvaliacoesFisicarController.java
│   ├── ExerciciosController.java
│   └── TreinoController.java
│
├── database/
│   ├── model/
│   │   ├── AlunosEntity.java
│   │   ├── AvaliacoesFisicasEntity.java
│   │   ├── ExerciciosEntity.java
│   │   ├── RolesEntity.java
│   │   └── TreinosEntity.java
│   │
│   └── repository/
│       ├── AlunosRepository.java
│       ├── AvaliacoesFisicasRepository.java
│       ├── ExerciciosRepository.java
│       ├── RolesRepository.java
│       └── TreinosRepository.java
│
├── dto/
│   ├── AlunoDto.java
│   ├── AvaliacaoFisicaDto.java
│   ├── AvaliacoesFisicasProjection.java
│   ├── ExerciciosDto.java
│   ├── LoginRequestDto.java
│   ├── RegisterRequestDto.java
│   ├── TokenResponseDto.java
│   └── TreinoDto.java
│
├── enums/
│   └── RoleTypeEnum.java
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
    ├── AuthService.java
    ├── AvaliacaoFisicaService.java
    ├── ExerciciosService.java
    ├── TreinoService.java
    └── UserDetailsServiceImp.java
```

### Responsabilidades das camadas

| Camada | Responsabilidade |
|---|---|
| Config | Configurações da aplicação e componentes de segurança. |
| Controller | Receber e responder às requisições HTTP. |
| Service | Implementar as regras de negócio. |
| Model | Representar as entidades do banco de dados. |
| Repository | Realizar operações de persistência. |
| DTO | Transportar e validar dados entre as camadas. |
| Enums | Definir constantes e tipos enumerados. |
| Exceptions | Definir exceções personalizadas. |
| Handler | Centralizar o tratamento de exceções. |

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
- Autenticação e autorização com Spring Security.
- JSON Web Token (JWT).
- Controle de acesso baseado em roles.

## Autor

Desenvolvido como projeto de estudo e prática de desenvolvimento Back-End com Java e Spring Boot.
