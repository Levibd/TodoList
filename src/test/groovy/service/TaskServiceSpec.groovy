package service

import model.Task
import model.Category
import model.Status
import spock.lang.Specification

class TaskServiceSpec extends Specification {

    TaskService service

    void setup(){
        service = new TaskService()
    }

    void "Cria uma nova tarefa e adiciona no repo"(){
        given: "Cria uma nova tarefa válida"
        Task task = Task.builder()
                .name("Estudar Spock")
                .description("Fazer os testes do Acelera ZG")
                .priority(5)
                .category(Category.ESTUDOS)
                .status(Status.TODO)
                .build()

        when: "Chama o método de criação da tarefa"
            service.createTask(task)

        then: "A tarefa deve ser adicionada na lista de tarefas e ser única"
            def savedTask = service.showAllTasks()
            savedTask.size() == 1
            savedTask.get(0).getName() == "Estudar Spock"
    }

    void "Lança uma exceção se criar uma tarefa sem um nome"() {
        given: "Criando uma tarefa sem nome"
        Task task = Task.builder()
                .name("")
                .description("Tarefa fantasma")
                .priority(1)
                .build()

        when: "Tento criar a tarefa"
        service.createTask(task)

        then: "Um exceção do tipo IllegalArgumentException deve ser lançada"
        def erro = thrown(IllegalArgumentException)

        and: "A mensagem de erro deve ser exata"
        erro.message == "O nome da tarefa é obrigatório."
    }

    void "deve deletar uma tarefa existente e retornar true"() {
        given: "que existe uma tarefa na lista"
        service.createTask(Task.builder().name("Comprar cafe").priority(1).build())

        when: "eu tento deletar pelo nome exato"
        boolean sucesso = service.deleteTask("Comprar cafe")

        then: "o retorno deve ser verdadeiro"
        sucesso == true

        and: "a lista deve ficar vazia"
        service.showAllTasks().size() == 0
    }

    void "nao deve deletar nada se o nome da tarefa nao existir e retornar false"() {
        given: "que existe uma tarefa na lista"
        service.createTask(Task.builder().name("Comprar cafe").priority(1).build())

        when: "eu tento deletar um nome que nao existe"
        boolean sucesso = service.deleteTask("Estudar Grails")

        then: "o retorno deve ser falso"
        sucesso == false

        and: "a tarefa original continua intacta na lista"
        service.showAllTasks().size() == 1
    }

    void "deve atualizar o status de uma tarefa existente"() {
        given: "uma tarefa com status TODO"
        service.createTask(Task.builder().name("Ler livro").status(Status.TODO).priority(1).build())

        when: "atualizo para DONE"
        boolean sucesso = service.updateTaskStatus("Ler livro", Status.DONE)

        then: "retorna sucesso e o status muda"
        sucesso == true
        service.showAllTasks().get(0).getStatus() == Status.DONE
    }

    void "deve filtrar tarefas corretamente por categoria"() {
        given: "duas tarefas de categorias diferentes"
        service.createTask(Task.builder().name("Trabalhar").category(Category.TRABALHO).priority(1).build())
        service.createTask(Task.builder().name("Jogar").category(Category.LAZER).priority(1).build())

        when: "eu busco apenas as de LAZER"
        def resultado = service.showTaskByCategory(Category.LAZER)

        then: "apenas 1 tarefa deve ser retornada"
        resultado.size() == 1
        resultado.get(0).getName() == "Jogar"
    }

    void "deve retornar lista vazia se nao houver tarefas no filtro de status"() {
        given: "uma tarefa DOING"
        service.createTask(Task.builder().name("Codar").status(Status.DOING).priority(1).build())

        when: "eu busco por tarefas DONE"
        def resultado = service.showTasksByStatus(Status.DONE)

        then: "a lista retornada deve ser vazia"
        resultado.isEmpty()
    }

}