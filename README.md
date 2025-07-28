# Conecta Doações - Back-end

API REST para gerenciar doações e usuários, facilitando a comunicação entre doadores e donatários.

## Como clonar o repositório

```bash
git clone https://github.com/guilhermeesales/conecta-doacoes-back-api.git
```

---

## Instruções para rodar a aplicação localmente

> Pré-requisitos: Java 11+, Maven, Docker

1. Navegue até a pasta do back-end:

```bash
cd conecta-doacoes-back-api
```

2. Suba os serviços com Docker Compose (o repositório já possui um arquivo `docker-compose.yml` configurado):

```bash
docker-compose up -d
```

3. Instale as dependências:

```bash
mvn clean install
```

4. Execute o projeto:

```bash
./mvnw spring-boot:run
```

---

## Tecnologias Utilizadas

### Back-end

- Java 11
- Spring Boot
    - Spring Web
    - Spring Data JPA
    - Spring Security
- MapStruct
- ModelMapper
- Swagger
- Lombok

### Banco de Dados

- PostgreSQL
- JPA / Hibernate
- Docker (ambiente de desenvolvimento)

---

## Link para a aplicação publicada

> https://conecta-doacoes-back-api.onrender.com/

---

## Link para a documentação da API

> https://conecta-doacoes-back-api.onrender.com/swagger-ui.html

---

## Link para o repositório front-end

> https://github.com/Caioledan/Conecta-Doacoes-FrontEnd.git

---

## Membros do grupo

- Caio Lemos Dantas
- Diego da Silva Ribeiro
- Guilherme Silva Sales
- Lydiana Rodrigues de Oliveira
- Mahatma Gandhi Perreira Leite
- Victor Ravel Santos Cavalcante

---
