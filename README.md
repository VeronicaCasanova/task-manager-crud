# Task Manager API

Uma API REST para gerenciamento de tarefas, permitindo operações CRUD (Create, Read, Update, Delete) e organização das tarefas por prioridade.

## Tecnologias Utilizadas

- **Spring Boot**: Framework para desenvolvimento rápido de aplicações Java.
- **Spring MVC**: Padrão Model-View-Controller para lidar com rotas e controladores.
- **Spring Data JPA**: Persistência de dados utilizando JPA (Java Persistence API).
- **SpringDoc OpenAPI 3**: Geração automática da documentação da API com Swagger.
- **MySQL**: Banco de dados relacional para persistir as tarefas.
- **H2**: Banco de dados em memória para testes.
- **JUnit 5**: Framework de testes para validar as funcionalidades da API.

## Práticas de Desenvolvimento

- **SOLID, DRY, YAGNI, KISS**
- **API REST**
- **Injeção de Dependências**
- **Tratamento de erros**
- **Geração automática do Swagger com a OpenAPI 3**

## Como Executar o Projeto

### Pré-requisitos

- **Java 17** ou superior
- **Maven** para gerenciamento de dependências
- **MySQL** (ou use o H2, para testes)

### Passos para execução

1. Clone o repositório
2. Instale as dependências com o Maven
3. Execute a aplicação

## Acesso à API

- A API poderá ser acessada em: [localhost:8080/tasks](http://localhost:8080/tasks)
- O Swagger poderá ser visualizado em: [localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

## API Endpoints

### Criar Tarefa - POST `/tasks`

Cria uma nova tarefa.  
**Exemplo de corpo da requisição:**

```json
{
  "nome": "Estudar Java",
  "descricao": "Estudar conceitos de Spring Boot",
  "realizado": false,
  "prioridade": 1
}

```
### Listar Tarefas - GET `/tasks`

Lista todas as tarefas, ordenadas por prioridade (decrescente) e ID (crescente).

---

### Atualizar Tarefa - PUT `/tasks`

Atualiza uma tarefa existente.  
**Exemplo de corpo da requisição:**

```json
{
  "id": 1,
  "nome": "Estudar Spring Boot",
  "descricao": "Finalizar estudo sobre Spring Boot",
  "realizado": true,
  "prioridade": 2
}

```
### Deletar Tarefa - DELETE `/tasks/{id}`
Exclui uma tarefa por ID.
  
