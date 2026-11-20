# Plano de desenvolvimento

**Status:** Pendente, Em andamento e Concluído. Uma tarefa só passa para Concluído após a verificação do critério de aceite. A presença dos arquivos iniciais do Spring Boot não comprova que o ambiente ou o build estejam prontos.

**Sequência prevista:** fundação e clientes na semana 1, rate limiting e proxy na semana 2, histórico, painel e entrega na semana 3.

## Fundação

| Tarefa | Critério de aceite | Status |
|---|---|---|
| Validar decisões iniciais | Regras e arquitetura aprovadas registradas no contexto do projeto | Pendente |
| Verificar ambiente e build | Java 21, Maven Wrapper e acesso ao Docker verificados, com build executado | Pendente |
| Configurar PostgreSQL e esquema inicial | Aplicação conecta usando configuração por ambiente e aplica o esquema em um banco vazio | Pendente |
| Padronizar validação e erros | Entradas inválidas retornam uma resposta de erro consistente e testada | Pendente |

## Gerenciamento de clientes e API Keys

| Tarefa | Critério de aceite | Status |
|---|---|---|
| Cadastrar e consultar clientes | Cadastro válido pode ser recuperado com sua configuração de cota | Pendente |
| Proteger acesso administrativo | Acesso autorizado funciona e acesso indevido é rejeitado conforme a regra aprovada | Pendente |
| Emitir e validar API Keys | Chave emitida autentica e o segredo não é persistido em texto puro | Pendente |
| Desativar clientes | Cliente desativado deixa de acessar as rotas protegidas | Pendente |

## Rate Limiting

| Tarefa | Critério de aceite | Status |
|---|---|---|
| Aplicar janela fixa por cliente | Cotas são independentes por cliente e a contagem reinicia na virada da janela | Pendente |
| Retornar status e headers de cota | Chamada acima do limite recebe `429` e os headers refletem o estado da janela | Pendente |
| Verificar concorrência | Chamadas simultâneas não admitem mais requisições que a cota configurada | Pendente |

## HTTP Proxy

| Tarefa | Critério de aceite | Status |
|---|---|---|
| Preparar API de demonstração | Destino HTTP de teste pode ser chamado pelo Gatekeeper | Pendente |
| Encaminhar requisições autorizadas | Destino recebe método, caminho, query, corpo quando aplicável e headers permitidos, sem credenciais do gateway | Pendente |
| Preservar resposta e tratar falhas | Status e corpo são preservados. Timeout e falha de conexão produzem erros testados | Pendente |

## Histórico

| Tarefa | Critério de aceite | Status |
|---|---|---|
| Registrar utilização | Chamadas admitidas e bloqueadas geram registros com os campos previstos e sem segredos | Pendente |
| Consultar histórico e consumo | É possível consultar chamadas e bloqueios por cliente e ver seu consumo atual | Pendente |

## Painel

| Tarefa | Critério de aceite | Status |
|---|---|---|
| Exibir clientes e cotas | Painel lê da API os clientes e seus limites | Pendente |
| Exibir uso e bloqueios | Painel mostra consumo e histórico real de requisições | Pendente |

## Testes, Docker e documentação

| Tarefa | Critério de aceite | Status |
|---|---|---|
| Executar testes unitários e de integração | Suíte cobre acessos válidos e inválidos, cotas, encaminhamento e falhas, com PostgreSQL via Testcontainers | Pendente |
| Empacotar a execução local | Docker Compose sobe o Gatekeeper, o banco e a API de demonstração | Pendente |
| Documentar a API com OpenAPI | Contratos de administração e acesso ao gateway podem ser consultados | Pendente |
| Validar e documentar a demonstração | README descreve comandos verificados para subir o ambiente e reproduzir acesso aceito e bloqueio por cota | Pendente |
