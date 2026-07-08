#  Docker Manager API

![Java](https://img.shields.io/badge/Java-21-orange?style=flat-square&logo=java)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3-brightgreen?style=flat-square&logo=spring-boot)
![Docker](https://img.shields.io/badge/Docker-Engine_API-blue?style=flat-square&logo=docker)

##  Sobre o Projeto
O **Docker Manager** é uma API RESTful desenvolvida em Java com Spring Boot, criada para gerenciar contêineres Docker locais. A aplicação se comunica diretamente com o *Docker Daemon* através do Unix Socket, permitindo o controle da infraestrutura sem a necessidade de interagir com o terminal.

Este projeto foi desenvolvido como um desafio de integração de sistemas, focando na aplicação de padrões de projeto, arquitetura em camadas e cobertura de testes unitários.

##  Funcionalidades
A API expõe endpoints para o gerenciamento completo do ciclo de vida dos contêineres:
* **Listar:** Visualizar todos os contêineres (ativos e inativos) ou filtrar apenas pelos que estão em execução.
* **Criar:** Subir novos contêineres a partir do nome de uma imagem.
* **Iniciar:** Dar *start* em um contêiner específico através do seu ID.
* **Parar:** Enviar comando de *stop* para um contêiner.
* **Deletar:** Remover contêineres da infraestrutura (com exclusão forçada habilitada).

##  Tecnologias e Ferramentas
* **Linguagem:** Java 21
* **Framework:** Spring Boot 3 (Web)
* **Integração:** `docker-java` (Cliente oficial do Docker para Java)
* **Testes:** JUnit 5 e Mockito
* **Gerenciador de Dependências:** Maven

