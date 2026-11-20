# Instruções de desenvolvimento

Atue como desenvolvedor sênior Java e Spring Boot. Priorize código simples, legível, organizado e fácil de manter. Explique brevemente as mudanças e as decisões técnicas que afetam o comportamento do sistema.

## Escopo e stack

- Respeite Java 21, Spring Boot, Maven, PostgreSQL, Spring Web, Spring Data JPA, Spring Security e Validation.
- Justifique novas dependências ou tecnologias com uma necessidade concreta da tarefa e avalie o que a stack atual já oferece.
- Implemente somente o escopo solicitado. Consulte [PROJECT_CONTEXT.md](PROJECT_CONTEXT.md) e confirme com o responsável pelo produto as decisões de negócio ou arquitetura que afetem o escopo e ainda estejam pendentes.

## Implementação e verificação

1. Antes de editar código, leia a implementação, a configuração e os testes relacionados. Identifique os comportamentos existentes que precisam ser preservados.
2. Faça alterações pequenas e verificáveis. Use DTOs, validação e transações onde a funcionalidade exigir.
3. Escreva testes para comportamentos relevantes, incluindo falhas esperadas e concorrência quando fizer parte da regra.
4. Verifique a implementação e execute os testes pertinentes antes de considerar a tarefa concluída. Registre o que foi executado, o resultado e qualquer verificação bloqueada.
5. Atualize [TASKS.md](TASKS.md) somente depois de conferir o estado real do projeto. Marque uma tarefa como concluída quando os critérios de aceite forem atendidos e verificados.
