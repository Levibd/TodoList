# 🚀 ZG Hero Project - ToDo List (Backend)

> Projeto da trilha de Java (K1-T3) do programa Acelera ZG.
> Uma aplicação de gerenciamento de tarefas focada em lógica de backend, validações e ordenação inteligente.

![Badge Concluído](http://img.shields.io/static/v1?label=STATUS&message=CONCLUIDO&color=GREEN&style=for-the-badge)
![Badge Java](http://img.shields.io/static/v1?label=LANGUAGE&message=JAVA&color=RED&style=for-the-badge)

## 🎯 Sobre o Projeto

Este projeto consiste em um **MVP (Minimum Viable Product)** de um sistema de gerenciamento de tarefas (ToDo List) rodando via Terminal. 

O objetivo principal foi implementar a lógica de negócios e estruturação de dados utilizando **Java Puro (Vanilla)**, sem o uso de frameworks (como Spring), aplicando conceitos de Orientação a Objetos, tratamento de exceções e estruturas de dados.

## 🛠 Funcionalidades

O sistema atende aos requisitos obrigatórios do desafio **ZG-Hero**:

* **CRD de Tarefas:** Criação, Leitura (Listagem) e Remoção de tarefas.
* **Rebalanceamento Automático:** Ao inserir uma nova tarefa, a lista é automaticamente reordenada baseada na Prioridade (Nível 5 aparece primeiro).
* **Filtros Avançados:**
    * Listar por Categoria (Trabalho, Estudos, etc).
    * Listar por Status (Todo, Doing, Done).
    * Listar por Prioridade.
* **Menu Interativo:** Interface via terminal amigável e tratada contra erros de digitação.
* **Validação de Datas:** Conversão e validação de datas (LocalDate).

## 💻 Tecnologias e Padrões Utilizados

* **Java JDK:** Linguagem principal.
* **Arquitetura MVC (Simplificada):** Separação de responsabilidades em:
    * `model`: Representação dos dados (Task, Enums).
    * `service`: Regras de negócio, ordenação e filtros.
    * `view/main`: Interação com o usuário via console.
* **Design Pattern - Builder:** Utilizado na criação do objeto `Task` para manter o código limpo e flexível, evitando construtores gigantes.
* **Java Streams API:** Utilizada para filtrar as listas de forma eficiente e declarativa.

## 📂 Estrutura do Projeto

```text
src/
├── Main.java  # Ponto de entrada (Menu e Interação)         
├── model/
│   ├── Task.java          # Classe com Builder Pattern
│   ├── Category.java      # Enum de Categorias
│   └── Status.java        # Enum de Status
└── service/
    └── TaskService.java   # Lógica (CRUD, Filtros e Ordenação)
