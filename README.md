# API de Clientes

API REST desenvolvida em Java com Spring Boot para gerenciar clientes, permitindo cadastrar, consultar, atualizar e excluir registros.

Este projeto faz parte do meu portfólio de estudos em desenvolvimento Back-end Java.

## Tecnologias utilizadas

- Java 21
- Spring Boot
- Spring Data JPA
- MySQL
- Maven
- Bean Validation
- Git e GitHub

## Funcionalidades

- Cadastro de clientes
- Listagem de clientes
- Consulta de cliente por ID
- Atualização de dados
- Exclusão de clientes
- Validação dos dados recebidos
- Tratamento de erros para clientes não encontrados e dados inválidos
- Testes automatizados dos endpoints

## Endpoints

| Método HTTP | Endpoint | Descrição |
|---|---|---|
| GET | `/clientes` | Lista todos os clientes |
| GET | `/clientes/{id}` | Busca um cliente por ID |
| POST | `/clientes` | Cadastra um novo cliente |
| PUT | `/clientes/{id}` | Atualiza um cliente |
| DELETE | `/clientes/{id}` | Exclui um cliente |

## Exemplo de cadastro

**Requisição:** `POST /clientes`

**Cabeçalho:** `Content-Type: application/json`

**Corpo da requisição:**

```json
{
  "nome": "Maria Silva",
  "email": "maria@email.com",
  "telefone": "34999999999"
}
```

## Pré-requisitos

- Java 21
- MySQL
- Git (opcional, para clonar o repositório)

## Como executar o projeto

1. Clone este repositório.
2. Crie o banco de dados `api_clientes` no MySQL.
3. Configure as credenciais de acesso ao banco no arquivo local `src/main/resources/application-local.properties`.
4. Confira se o serviço do MySQL está em execução.
5. Abra o terminal na pasta raiz do projeto e execute:

```powershell
.\mvnw.cmd spring-boot:run
```

A aplicação será iniciada na porta `8080`.

Como alternativa, abra o projeto no IntelliJ IDEA e execute a classe `ApiClientesApplication`.

## Configuração do banco de dados

O arquivo `application-local.properties` contém configurações locais e não deve ser publicado no GitHub.

Não compartilhe senhas ou outras credenciais. Confira se esse arquivo está listado no `.gitignore`.

## Documentação da API — Swagger

A API possui documentação interativa com Swagger UI, permitindo visualizar e testar os endpoints diretamente pelo navegador.

Com a aplicação em execução, acesse:

http://localhost:8080/swagger-ui/index.html

A documentação permite testar as operações de cadastro, consulta, atualização e exclusão de clientes.

## Executando os testes

No terminal, na pasta raiz do projeto, execute:

```powershell
.\mvnw.cmd clean test
```

O comando limpa os arquivos de compilação anteriores e executa os testes automatizados.

## Aprendizados

Durante o desenvolvimento, pratiquei conceitos de:

- Programação Orientada a Objetos com Java
- Criação de APIs REST
- Organização em camadas: Controller, Service, Repository e Model
- Persistência de dados com Spring Data JPA
- Integração com banco de dados MySQL
- Validação de dados e tratamento de erros
- Testes automatizados
- Versionamento de código com Git e GitHub

## Autora

Estudante de Sistemas de Informação, em transição para a área de Tecnologia, com foco em desenvolvimento Back-end Java.