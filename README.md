# 🚀 ZG Hero Project - ToDo List (Backend)

> Projeto da trilha de Java (K1-T3) do programa Acelera ZG.
> Uma aplicação de gerenciamento de tarefas focada em lógica de backend, validações e ordenação inteligente.

![Badge Concluído](http://img.shields.io/static/v1?label=STATUS&message=CONCLUIDO&color=GREEN&style=for-the-badge)
![Badge Java](http://img.shields.io/static/v1?label=LANGUAGE&message=JAVA&color=RED&style=for-the-badge)
![Badge Gradle](http://img.shields.io/static/v1?label=BUILD&message=GRADLE&color=02303A&style=for-the-badge)
![Badge Spock](http://img.shields.io/static/v1?label=TESTS&message=SPOCK_FRAMEWORK&color=blue&style=for-the-badge)

## 🎯 Sobre o Projeto

Este projeto consiste em um **MVP (Minimum Viable Product)** de um sistema de gerenciamento de tarefas (ToDo List) rodando via Terminal. 

O objetivo principal foi implementar a lógica de negócios e estruturação de dados utilizando **Java Puro (Vanilla)**, sem o uso de frameworks (como Spring), aplicando conceitos de Orientação a Objetos, tratamento de exceções e estruturas de dados.

## 🛠 Funcionalidades

O sistema atende aos requisitos obrigatórios do desafio **ZG-Hero**:

* **CRUD Completo de Tarefas:** Criação, Leitura (Listagem), Atualização de Status e Remoção de tarefas.
* **Rebalanceamento Automático:** Ao inserir uma nova tarefa, a lista é automaticamente reordenada baseada na Prioridade.
* **Cobertura de Testes (Novo!):** Testes unitários cobrindo caminhos felizes e de exceção utilizando BDD (Behavior-Driven Development).
* **Filtros Avançados...** (Mantenha o resto que já estava)

## ⏰ Nova Feature: Alarmes Inteligentes (v2.0)

Agora o sistema conta com um monitoramento em tempo real para prazos de tarefas.

### Como funciona:
1. Ao criar uma tarefa, o usuário define a **Data e Hora** de término (ex: `20/10/2023 15:00`).
2. O usuário pode optar por ativar um **Alarme** e definir a **antecedência** (ex: avisar 30 minutos antes).
3. Uma *Thread* em background verifica periodicamente as tarefas. Se o horário atual entrar na zona de aviso, uma notificação visual é disparada no console: `🚨 [ALARME] A tarefa X vence em breve!`.

### Tecnologias aplicadas:
- **LocalDateTime:** Para precisão de minutos.
- **Java Threads:** Para execução concorrente do monitoramento sem travar o menu principal.

## 💻 Tecnologias e Padrões Utilizados

* **Java JDK:** Linguagem principal (com uso intensivo de Streams API).
* **Groovy & Spock Framework:** Utilizados para a suíte de testes unitários automatizados.
* **Gradle:** Gerenciador de dependências e build do projeto.
* **Clean Code:** Refatoração de métodos para garantir o Princípio da Responsabilidade Única (SRP).
* **Arquitetura MVC:** Camadas bem definidas (`controller`, `model`, `service`, `view`).
* **Design Patterns:** * **Builder:** Na criação do objeto `Task` para flexibilidade.
    * **Singleton:** No gerenciamento do leitor de inputs (`InputReader`).

## 📂 Estrutura do Projeto (Standard Directory Layout)

```text
TodoList/
├── build.gradle                 # Configurações do Gradle e dependências
├── src/
│   ├── main/
│   │   └── java/
│   │       ├── controller/      # Intermediário entre View e Service
│   │       ├── model/           # Entidades e Enums (Task, Category, Status)
│   │       ├── service/         # Regras de negócio e armazenamento em memória
│   │       ├── utils/           # Utilitários (InputReader)
│   │       ├── view/            # Menus e interação com usuário
│   │       └── Main.java        # Ponto de entrada
│   └── test/
│       └── groovy/__
│           └── service/
│               └── TaskServiceSpec.groovy # Testes automatizados em Spock
