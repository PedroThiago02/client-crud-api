# Client CRUD API

Projeto desenvolvido como desafio do módulo de API REST, camadas, CRUD, exceções e validações do curso Java Spring Professional da DevSuperior.

## Sobre o projeto

A aplicação consiste em uma API RESTful para gerenciamento de clientes, contendo operações completas de CRUD.

O sistema permite:

- Buscar clientes por ID
- Buscar clientes de forma paginada
- Inserir novos clientes
- Atualizar clientes
- Remover clientes

---

## Entidade Client

A entidade Client possui os seguintes atributos:

- id
- name
- cpf
- income
- birthDate
- children

---

## Funcionalidades implementadas

### CRUD completo

- GET /clients
- GET /clients/{id}
- POST /clients
- PUT /clients/{id}
- DELETE /clients/{id}

### Paginação

Busca paginada com suporte a:

- page
- size
- sort

Exemplo:

```http
GET /clients?page=0&size=6&sort=name
```

---

## Tratamento de exceções

A API possui tratamento global de exceções para:

### Recurso não encontrado

Retorna:

```http
404 Not Found
```

### Erros de validação

Retorna:

```http
422 Unprocessable Entity
```

Com mensagens customizadas para cada campo inválido.

---

## Validações

### Nome

- Não pode ser vazio

### Data de nascimento

- Não pode ser uma data futura

---

## Tecnologias utilizadas

- Java
- Spring Boot
- Spring Data JPA
- Hibernate
- Banco de dados H2
- Maven

---

## Banco de dados

O projeto utiliza banco H2 em memória com seeding automático de clientes.

H2 Console:

```text
http://localhost:8080/h2-console
```

---

## Como executar o projeto

```bash
# clonar repositório
git clone https://github.com/seuusuario/client-crud-api.git

# entrar na pasta
cd client-crud-api

# executar aplicação
./mvnw spring-boot:run
```

---

## Endpoints principais

### Buscar cliente por ID

```http
GET /clients/1
```

### Buscar clientes paginados

```http
GET /clients?page=0&size=6&sort=name
```

### Inserir cliente

```http
POST /clients
```

### Atualizar cliente

```http
PUT /clients/1
```

### Remover cliente

```http
DELETE /clients/1
```

---

## Objetivos do desafio

- Desenvolver API RESTful
- Trabalhar com arquitetura em camadas
- Implementar operações CRUD
- Aplicar validações
- Implementar tratamento de exceções
- Utilizar paginação
- Trabalhar com JPA e Hibernate
