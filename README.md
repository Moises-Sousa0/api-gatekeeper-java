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

## Desenvolvimento

- [Contexto, escopo e decisões pendentes](PROJECT_CONTEXT.md)
- [Plano de tarefas e critérios de aceite](TASKS.md)
- [Instruções para agentes de desenvolvimento](AGENTS.md)
