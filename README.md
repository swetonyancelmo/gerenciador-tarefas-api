# 📋 Gerenciador de Tarefas - API RESTful

![Java](https://img.shields.io/badge/Java-17-red?logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.0.1-brightgreen?logo=springboot)
![Maven](https://img.shields.io/badge/Maven-Build-blue?logo=apachemaven)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-15-blue?logo=postgresql)
![Spring Security](https://img.shields.io/badge/Spring%20Security-Auth-green?logo=springsecurity)
![JWT](https://img.shields.io/badge/JWT-Token-orange?logo=jsonwebtokens)
![Lombok](https://img.shields.io/badge/Lombok-Reduce%20Boilerplate-pink?logo=lombok)
![Status](https://img.shields.io/badge/Status-Em%20Desenvolvimento-yellow)

Sistema de gerenciamento de tarefas desenvolvido com Spring Boot, oferecendo uma API REST completa para criação, atualização, listagem e exclusão de tarefas com autenticação JWT.

## 🚀 Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 4.0.1**
- **Spring Security** - Autenticação e autorização
- **Spring Data JPA** - Persistência de dados
- **PostgreSQL 15** - Banco de dados relacional
- **JWT (JSON Web Token)** - Autenticação stateless
- **SpringDoc OpenAPI** - Documentação da API (Swagger)
- **Lombok** - Redução de boilerplate
- **Maven** - Gerenciamento de dependências
- **Docker Compose** - Orquestração de containers

## ✨ Funcionalidades

- ✅ **Autenticação JWT**: Sistema seguro de login com tokens
- ✅ **CRUD de Usuários**: Criação, leitura, atualização e exclusão de usuários
- ✅ **CRUD de Tarefas**: Gerenciamento completo de tarefas
- ✅ **Associação de Tarefas**: Cada tarefa pertence a um usuário
- ✅ **Conclusão de Tarefas**: Marcação de tarefas como concluídas
- ✅ **Validação de Dados**: Validação automática de entrada
- ✅ **Documentação Swagger**: Interface interativa para testar a API
- ✅ **Segurança**: Proteção de endpoints com Spring Security

## 📋 Pré-requisitos

Antes de começar, certifique-se de ter instalado:

- **Java 17** ou superior
- **Maven 3.6+**
- **Docker** e **Docker Compose** (para executar o banco de dados)
- **Git** (opcional)

## 🔧 Instalação e Configuração

### 1. Clone o repositório

```bash
git clone <url-do-repositorio>
cd Task-Manager
```

### 2. Configure o banco de dados com Docker

O projeto utiliza Docker Compose para facilitar a configuração do PostgreSQL:

```bash
docker-compose up -d
```

Isso irá:
- Criar um container PostgreSQL na porta `5433`
- Configurar o banco de dados `taskdb`
- Criar um usuário `postgres` com senha `password123`

### 3. Configure as variáveis de ambiente

O arquivo `application.properties` já está configurado com as seguintes propriedades:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5433/taskdb
spring.datasource.username=postgres
spring.datasource.password=password123
api.security.token.secret=amF2YS1zcHJpbmctYm9vdC1zZWN1cml0eS1qd3Qtc2VjcmV0LWtleS1mb3ItZ2VyZW5jaWFtZW50by1kZS10YXJlZmFz
```

**⚠️ Importante**: Em produção, altere o `api.security.token.secret` para uma chave segura e única.

### 4. Compile o projeto

```bash
mvn clean install
```

## 🏃 Como Executar

### Executar a aplicação

```bash
mvn spring-boot:run
```

Ou execute diretamente a classe principal:

```bash
java -jar target/Task-Manager-0.0.1-SNAPSHOT.jar
```

A aplicação estará disponível em: `http://localhost:8080`

### Acessar a documentação Swagger

Após iniciar a aplicação, acesse:

- **Swagger UI**: http://localhost:8080/swagger-ui.html
- **OpenAPI JSON**: http://localhost:8080/v3/api-docs

## 📁 Estrutura do Projeto

```
Task-Manager/
├── src/
│   ├── main/
│   │   ├── java/com/swetonyancelmo/taskManager/
│   │   │   ├── config/              # Configurações (Security, Swagger)
│   │   │   ├── controller/          # Controllers REST
│   │   │   ├── dtos/                # Data Transfer Objects
│   │   │   │   ├── request/         # DTOs de requisição
│   │   │   │   └── response/        # DTOs de resposta
│   │   │   ├── exceptions/          # Exceções customizadas
│   │   │   ├── mapper/              # Mappers para conversão de objetos
│   │   │   ├── models/              # Entidades JPA
│   │   │   ├── repository/          # Repositórios JPA
│   │   │   ├── service/             # Lógica de negócio
│   │   │   └── TaskManagerApplication.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/                        # Testes unitários
├── docker-compose.yml               # Configuração do PostgreSQL
├── pom.xml                          # Dependências Maven
└── README.md
```

## 🔐 Autenticação

A API utiliza autenticação JWT (JSON Web Token). Para acessar os endpoints protegidos:

1. **Cadastre um usuário** (endpoint público):
   ```http
   POST /users
   ```

2. **Faça login** (endpoint público):
   ```http
   POST /login
   Content-Type: application/json
   
   {
     "email": "usuario@email.com",
     "password": "senha123"
   }
   ```

3. **Use o token retornado** no header de todas as requisições:
   ```http
   Authorization: Bearer <seu-token-jwt>
   ```

## 📡 Endpoints da API

### 🔓 Endpoints Públicos

#### Autenticação

- **POST** `/login` - Realiza login e retorna token JWT
  ```json
  {
    "email": "usuario@email.com",
    "password": "senha123"
  }
  ```

#### Usuários

- **POST** `/users` - Cadastra um novo usuário
  ```json
  {
    "username": "João Silva",
    "email": "joao@email.com",
    "password": "senha123"
  }
  ```

### 🔒 Endpoints Protegidos (requerem autenticação)

#### Usuários

- **GET** `/users` - Lista todos os usuários
- **GET** `/users/{id}` - Busca usuário por ID
- **PUT** `/users/{id}` - Atualiza um usuário
- **DELETE** `/users/{id}` - Deleta um usuário

#### Tarefas

- **GET** `/tasks` - Lista todas as tarefas
- **GET** `/tasks/{id}` - Busca tarefa por ID
- **POST** `/tasks` - Cria uma nova tarefa
  ```json
  {
    "title": "Título da tarefa",
    "description": "Descrição da tarefa"
  }
  ```
- **PUT** `/tasks/{id}` - Atualiza uma tarefa completamente
  ```json
  {
    "title": "Título atualizado",
    "description": "Descrição atualizada"
  }
  ```
- **PATCH** `/tasks/{id}` - Marca uma tarefa como concluída
- **DELETE** `/tasks/{id}` - Deleta uma tarefa

## 📊 Modelos de Dados

### User (Usuário)

```java
{
  "id": UUID,
  "username": String (3-100 caracteres),
  "email": String (único, formato email),
  "password": String (8-20 caracteres, criptografado)
}
```

### Task (Tarefa)

```java
{
  "id": UUID,
  "title": String (3-100 caracteres, obrigatório),
  "description": String (máximo 255 caracteres),
  "completed": Boolean (default: false),
  "user": User (relacionamento ManyToOne)
}
```

## 📝 Exemplos de Uso

### 1. Cadastrar um usuário

```bash
curl -X POST http://localhost:8080/users \
  -H "Content-Type: application/json" \
  -d '{
    "username": "Maria Silva",
    "email": "maria@email.com",
    "password": "senha123"
  }'
```

### 2. Fazer login

```bash
curl -X POST http://localhost:8080/login \
  -H "Content-Type: application/json" \
  -d '{
    "email": "maria@email.com",
    "password": "senha123"
  }'
```

Resposta:
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
}
```

### 3. Criar uma tarefa (com autenticação)

```bash
curl -X POST http://localhost:8080/tasks \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..." \
  -d '{
    "title": "Estudar Spring Boot",
    "description": "Revisar conceitos de segurança e JWT"
  }'
```

### 4. Listar todas as tarefas

```bash
curl -X GET http://localhost:8080/tasks \
  -H "Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
```

### 5. Marcar tarefa como concluída

```bash
curl -X PATCH http://localhost:8080/tasks/123e4567-e89b-12d3-a456-426614174000 \
  -H "Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
```

## 🛠️ Desenvolvimento

### Executar testes

```bash
mvn test
```

### Gerar JAR executável

```bash
mvn clean package
```

O arquivo JAR será gerado em: `target/Task-Manager-0.0.1-SNAPSHOT.jar`

### Parar o banco de dados

```bash
docker-compose down
```

Para remover também os volumes (dados):

```bash
docker-compose down -v
```

## 🔒 Segurança

- **Senhas**: Criptografadas com BCrypt
- **JWT**: Tokens com expiração configurável
- **Spring Security**: Proteção de endpoints
- **Validação**: Validação de entrada com Bean Validation
- **CORS**: Configurável (atualmente desabilitado para desenvolvimento)

## 📚 Documentação Adicional

- [Spring Boot Documentation](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/)
- [Spring Security Reference](https://docs.spring.io/spring-security/reference/index.html)
- [JWT.io](https://jwt.io/) - Para decodificar e testar tokens JWT
- [PostgreSQL Documentation](https://www.postgresql.org/docs/)

## 🤝 Contribuindo

1. Faça um fork do projeto
2. Crie uma branch para sua feature (`git checkout -b feature/AmazingFeature`)
3. Commit suas mudanças (`git commit -m 'Add some AmazingFeature'`)
4. Push para a branch (`git push origin feature/AmazingFeature`)
5. Abra um Pull Request

## 📄 Licença

Este projeto está sob a licença MIT. Veja o arquivo `LICENSE` para mais detalhes.

## 👤 Autor

**Swetony Ancelmo**

---

## ⚠️ Notas Importantes

- O token JWT deve ser incluído no header `Authorization` de todas as requisições protegidas
- A senha deve ter entre 8 e 20 caracteres
- O email deve ser único no sistema
- O título da tarefa é obrigatório e deve ter entre 3 e 100 caracteres
- A descrição da tarefa é opcional e pode ter até 255 caracteres

## 🐛 Troubleshooting

### Erro de conexão com o banco de dados

Certifique-se de que o Docker Compose está rodando:
```bash
docker-compose ps
```

### Porta já em uso

Se a porta 8080 estiver em uso, altere no `application.properties`:
```properties
server.port=8081
```

### Erro de autenticação

Verifique se:
- O token JWT está sendo enviado corretamente no header
- O token não expirou
- O formato do header está correto: `Authorization: Bearer <token>`

---

**Desenvolvido com ❤️ usando Spring Boot**

