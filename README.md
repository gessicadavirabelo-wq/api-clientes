# API de Clientes

API REST desenvolvida em Java com Spring Boot para realizar o gerenciamento de clientes, permitindo cadastrar, consultar, atualizar e excluir registros.

Este projeto faz parte do meu portfólio de estudos em desenvolvimento Back-end Java.

## Tecnologias utilizadas

- Java 24
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

## Endpoints

| Método HTTP | Endpoint | Descrição |
|---|---|---|
| GET | `/clientes` | Lista todos os clientes |
| GET | `/clientes/{id}` | Busca um cliente por ID |
| POST | `/clientes` | Cadastra um novo cliente |
| PUT | `/clientes/{id}` | Atualiza um cliente |
| DELETE | `/clientes/{id}` | Exclui um cliente |

## Exemplo de cadastro

Requisição `POST /clientes`:

```json
{
  "nome": "Maria Silva",
  "email": "maria@email.com",
  "telefone": "34999999999"
}
```

## Como executar o projeto

1. Instale o Java e o MySQL.
2. Crie um banco de dados chamado `api_clientes`.
3. Configure as credenciais de acesso ao MySQL no arquivo local `src/main/resources/application-local.properties`.
4. Execute a aplicação pela classe `ApiClientesApplication` na sua IDE.

**Importante:** o arquivo `application-local.properties` contém configurações locais e não deve ser publicado no GitHub.

## Aprendizados

Durante o desenvolvimento, pratiquei conceitos de:

- Programação Orientada a Objetos com Java
- Criação de APIs REST
- Organização em camadas: Controller, Service, Repository e Model
- Persistência de dados com Spring Data JPA
- Integração com banco de dados MySQL
- Validação de dados e tratamento de erros
- Versionamento de código com Git e GitHub

## Autora

Estudante de Sistemas de Informação, em transição para a área de Tecnologia, com foco em desenvolvimento Back-end Java.