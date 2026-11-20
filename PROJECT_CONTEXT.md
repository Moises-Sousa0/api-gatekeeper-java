# Contexto do Gatekeeper

Gatekeeper é um projeto de portfólio Java Backend para demonstrar controle de acesso e limitação de tráfego em APIs. Ele centraliza a identificação dos clientes, a aplicação de cotas e o registro de uso antes que as chamadas cheguem à API protegida.

## MVP confirmado

- Cadastrar, consultar e desativar clientes, emitir API Keys individuais e configurar cotas por cliente.
- Aplicar rate limiting por janela fixa, devolver informações de cota em headers HTTP e retornar HTTP `429` quando o limite for excedido.
- Encaminhar requisições autorizadas a uma API de destino configurável. Preservar status e corpo da resposta, filtrar headers e tratar falhas do destino.
- Registrar cliente, caminho, método, status, horário e indicação de bloqueio pelo rate limiter. Mostrar consumo, bloqueios e histórico em um painel simples.
- Usar uma API de demonstração pequena para validar chamadas aceitas e bloqueadas. A meta inicial é entregar o MVP em três semanas.

## Regras e decisões confirmadas

- Cada chamada protegida é identificada por API Key. O cliente precisa estar ativo e atender às regras de acesso e de cota antes do encaminhamento.
- O segredo da chave deve ser armazenado de forma segura, sem persistência em texto puro nem envio ao destino.
- O limite é configurável por cliente. O contador reinicia conforme a janela configurada e deve respeitar o limite sob requisições simultâneas.
- Requisições bloqueadas por cota recebem `429` e são registradas no histórico.
- O encaminhamento preserva método, caminho e parâmetros de consulta, com controle explícito dos headers permitidos e proteção de credenciais internas.

### Administração

- As rotas administrativas ficam sob `/admin/**` e exigem autenticação com usuário e senha. As credenciais são fornecidas por variáveis de ambiente.
- O MVP fica restrito à autenticação administrativa e exclui um sistema completo de usuários, cadastro e gerenciamento de permissões.

### API de destino

- Todos os clientes ativos usam uma única API de destino compartilhada. A URL é definida no servidor por variável de ambiente ou configuração equivalente.
- Os clientes acessam o destino configurado pelo servidor, sem poder escolher a URL.

## Fluxo geral

1. O cliente envia uma requisição HTTP com sua API Key.
2. O Gatekeeper identifica o cliente, verifica o acesso e avalia a cota da janela atual.
3. Se a cota acabou, o Gatekeeper registra o bloqueio e responde `429`. Ao autorizar a chamada, encaminha a requisição ao destino.
4. O Gatekeeper devolve a resposta ou o erro de encaminhamento e registra o resultado das chamadas identificadas.

## Stack e diretrizes confirmadas

- A base existente declara Java 21, Spring Boot, Maven, Spring Web (MVC), Spring Data JPA, Spring Security, Validation e o driver PostgreSQL.
- JUnit 5, Mockito e Testcontainers fazem parte do plano de testes. Docker e Docker Compose estão previstos para execução local, e OpenAPI para documentação da API.
- Organizar a aplicação em camadas, com DTOs, validação, tratamento global de exceções, configuração por ambiente e transações quando necessárias. O MVP fica fora de uma arquitetura de microsserviços e adia Redis.

## Propostas pendentes de validação

| Decisão | Proposta inicial |
|---|---|
| Permissões e rotas do gateway | As mesmas rotas disponíveis para todos os clientes ativos. Tráfego protegido em `/proxy/**`. |
| Painel | Painel servido pela aplicação Gatekeeper. |
| Ciclo de vida da API Key | Uma chave ativa por cliente, segredo exibido na emissão e reemissão que revoga a anterior sem reiniciar a cota. |
| Janela e consumo | Alinhar janelas ao relógio. Contar chamadas admitidas mesmo com falha ou timeout no destino, registrar bloqueios sem incrementar a cota e aplicar mudanças de configuração na janela seguinte. |
| Modelagem e concorrência | Separar cliente, chave, janela de consumo e histórico. Usar JPA para cadastro e histórico, com atualização atômica da cota no PostgreSQL por cliente e janela. |

As propostas acima aguardam confirmação antes da implementação das partes afetadas.
