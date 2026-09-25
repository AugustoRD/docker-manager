# Docker Manager API - Agent Context

## 1. Regras Globais e Tech Stack

- **Natureza do Projeto:** Este é um projeto exclusivamente de backend (API RESTful). Não gere ou sugira código de frontend (HTML/CSS/JS/React).
- **Linguagem e Framework:** Java 21 e Spring Boot 3 (Web).
- **Gerenciador de Dependências:** Maven (utilize o ficheiro `pom.xml`).
- **Integração Principal:** A comunicação com o Docker Daemon é feita estritamente através da biblioteca oficial `docker-java` via Unix Socket.
- **Qualidade e Testes:** Todo o código novo ou alterado deve ser coberto por testes unitários utilizando JUnit 5 e Mockito.

## 2. Arquitetura e Estrutura de Diretórios

O pacote base da aplicação é `com.augustord.docker_manager`. O projeto segue uma arquitetura em camadas rígida na pasta `src/main/java`:

- `/config`: Apenas classes de configuração do Spring e instâncias do cliente Docker (ex: `DockerClientConfig.java`).
- `/controllers`: Apenas endpoints REST (ex: `DockerContainersController.java`, `DockerImagesController.java`). A lógica de negócio não deve ficar aqui.
- `/dtos`: Apenas objetos de transferência de dados (Records ou Classes) para padronizar as respostas da API (ex: `ContainerResponseDto.java`).
- `/service`: Apenas a lógica de negócio e a interação direta com o `docker-java` (ex: `DockerService.java`).

## 3. Padrões de Teste

- Os testes residem em `src/test/java/com/augustord/docker_manager`.
- Mantenha a organização por contexto: testes de controladores na pasta `/controller` (ex: `DockerControllerTest.java`) e testes de serviço na pasta `/service` (ex: `DockerServiceTest.java`).

## 4. Funcionalidades de Domínio

A API foca-se no ciclo de vida de contentores locais: Listar, Criar, Iniciar, Parar e Deletar (com exclusão forçada habilitada). Qualquer nova funcionalidade deve alinhar-se com este escopo.
