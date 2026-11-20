# Gatekeeper

Gatekeeper é um projeto de portfólio Java Backend para controlar o acesso e limitar o volume de requisições a APIs. O serviço ficará entre o cliente e a API protegida, identificando quem chama, verificando a cota e encaminhando chamadas autorizadas.

**Estado atual:** estrutura inicial do Spring Boot, Maven Wrapper e um teste de carregamento do contexto. As funcionalidades do MVP estão planejadas.

## Funcionalidades planejadas

- Cadastro e desativação de clientes, emissão de API Keys e configuração de cotas.
- Rate limiting por janela fixa, com informações de cota nos headers e HTTP `429` quando o limite for excedido.
- Encaminhamento HTTP com filtragem de headers e tratamento de falhas do destino.
- Histórico de uso e painel para acompanhar cotas, consumo e bloqueios.
- API de demonstração para reproduzir o fluxo do gateway.

## Tecnologias

Java 21, Spring Boot, Maven, Spring Web, Spring Data JPA, Spring Security, Validation e PostgreSQL. Estão previstos JUnit 5, Mockito e Testcontainers para testes, Docker Compose para execução local e OpenAPI para documentação dos endpoints.

## PostgreSQL local

1. No PowerShell, copie `.env.example` para `.env` com `Copy-Item .env.example .env`. Preencha `DB_PASSWORD` no arquivo local.
2. Com o Docker em execução, suba o banco com `docker compose up -d postgres`.
3. Na configuração de execução do Gatekeeper no IntelliJ, defina as variáveis abaixo. Use o mesmo usuário e a mesma senha do `.env`.

| Variável | Valor local |
|---|---|
| `DB_URL` | `jdbc:postgresql://localhost:5432/gatek` |
| `DB_USER` | `postgres` |
| `DB_PASSWORD` | A senha definida no `.env` |

Ao preencher uma variável em um campo separado do IntelliJ, informe apenas o valor. O `.env` é lido pelo Compose. O Spring Boot, executado fora do container, recebe as variáveis do IntelliJ ou do terminal que o inicia. Se mudar `POSTGRES_PORT`, ajuste também a porta na URL JDBC.

O PostgreSQL aplica o nome do banco e as credenciais na primeira inicialização do volume. Editar o `.env` depois disso não altera as credenciais que já estão no banco.

## Desenvolvimento

- [Contexto, escopo e decisões pendentes](PROJECT_CONTEXT.md)
- [Plano de tarefas e critérios de aceite](TASKS.md)
- [Instruções para agentes de desenvolvimento](AGENTS.md)
