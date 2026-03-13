# 📚 BookCase API

![Java](https://img.shields.io/badge/Java-21-orange?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-8.x-4479A1?style=for-the-badge&logo=mysql&logoColor=white)

## Sobre o Projeto

**BookCase** é uma API RESTful para gerenciamento de biblioteca pessoal. A API permite que usuários se cadastrem, façam login e gerenciem sua própria estante virtual de livros. Cada usuário possui uma estante privada onde pode adicionar livros, definir o status de leitura e atribuir avaliações.

## 🛠️ Tecnologias Utilizadas

- **Java 21**
- **Spring Boot** — framework principal da aplicação
- **Spring MVC** — camada web e roteamento REST
- **Spring Security** — autenticação via HTTP Basic Auth
- **Spring Data JPA / Hibernate** — persistência e mapeamento objeto-relacional
- **MySQL** — banco de dados relacional
- **Maven** — gerenciamento de dependências e build
- **Bean Validation** — validação de dados nos DTOs
- **Lombok** — redução de boilerplate nos modelos

## ✅ Pré-requisitos

Antes de executar a aplicação, certifique-se de ter instalado:

- [Java 21+](https://adoptium.net/)
- [MySQL 8+](https://dev.mysql.com/downloads/mysql/)
- [Maven 3.8+](https://maven.apache.org/download.cgi) (ou use o Maven Wrapper incluído no projeto)

## 🚀 Como Executar

### 1. Clone o repositório

```bash
git clone https://github.com/PabloTzeliks/bookcase-api.git
cd bookcase-api
```

### 2. Configure o banco de dados

Crie o banco de dados no MySQL:

```sql
CREATE DATABASE simple_db;
CREATE USER 'db_user'@'localhost' IDENTIFIED BY 'db_pass';
GRANT ALL PRIVILEGES ON simple_db.* TO 'db_user'@'localhost';
FLUSH PRIVILEGES;
```

### 3. Configure o `application.yaml`

Edite o arquivo `src/main/resources/application.yaml` com suas credenciais:

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/simple_db
    username: db_user
    password: db_pass
    driver-class-name: com.mysql.cj.jdbc.Driver
  jpa:
    hibernate:
      ddl-auto: update
```

> ⚠️ A propriedade `spring.jpa.hibernate.ddl-auto=update` faz com que o Hibernate crie e atualize automaticamente as tabelas do banco de dados com base nas entidades da aplicação.

### 4. Execute a aplicação

Usando o Maven Wrapper:

```bash
# Linux / macOS
./mvnw spring-boot:run

# Windows
mvnw.cmd spring-boot:run
```

A API estará disponível em: `http://localhost:8080`

## 📋 Endpoints Principais

### Autenticação

| Método | Endpoint           | Auth Necessária | Descrição               |
|--------|--------------------|-----------------|-------------------------|
| POST   | `/users/register`  | Não             | Cadastra um novo usuário |
| POST   | `/users/login`     | Basic Auth      | Autentica o usuário      |

**Exemplo de body para `/users/register`:**
```json
{
  "name": "Seu Nome",
  "email": "email@exemplo.com",
  "password": "sua_senha"
}
```

### Livros

| Método | Endpoint | Auth Necessária | Descrição                                  |
|--------|----------|-----------------|--------------------------------------------|
| GET    | `/books` | Basic Auth      | Lista todos os livros da estante do usuário |
| POST   | `/books` | Basic Auth      | Adiciona um novo livro à estante            |

**Exemplo de body para `POST /books`:**
```json
{
  "isbn": "978-3-16-148410-0",
  "title": "Nome do Livro",
  "authors": "Nome do Autor",
  "thumbnail_url": "https://exemplo.com/capa.jpg",
  "status": "READING",
  "rating": 4
}
```

> **Status disponíveis:** `READING`, `READ`, `WANT_READ`  
> **Rating:** valor inteiro de `0` a `5`

## 🔒 Segurança

As rotas de livros são protegidas e exigem autenticação via **HTTP Basic Authentication**. Para acessá-las, inclua o cabeçalho `Authorization` em cada requisição com as credenciais do usuário codificadas em Base64:

```
Authorization: Basic <base64(email:senha)>
```

**Exemplo com curl:**
```bash
curl -u "email@exemplo.com:sua_senha" http://localhost:8080/books
```

A senha do usuário é armazenada de forma segura utilizando o algoritmo de hash **BCrypt**.
