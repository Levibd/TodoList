package controller;

import formatters.DateFormatter;
import model.Category;
import model.Status;
import model.Task;
import service.TaskService;
import utils.InputReader;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class TaskController {


    DateFormatter formatter = new DateFormatter();
    private final TaskService taskService;

    private final InputReader input = InputReader.getInstance();

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    public void createTask() {
        System.out.print("Nome: ");
        String name = input.readString();

        System.out.print("Descrição: ");
        String desc = input.readString();

        System.out.print("Prioridade (1-5): ");
        int prioridade = input.readInt();

        System.out.print("Categoria (TRABALHO, ESTUDOS, LAZER...): ");
        String catStr = input.readString().toUpperCase();
        Category categoria = Category.valueOf(catStr);

        System.out.print("Status (TODO, DOING, DONE): ");
        String statStr = input.readString().toUpperCase();
        Status status = Status.valueOf(statStr);

        System.out.print("Data de Término (dd/MM/yyyy HH:mm): ");
        String dataStr = null;
        try {
            dataStr = input.readValidDate();
        } catch (IllegalArgumentException e) {
            e.getMessage();
        }

        LocalDateTime finalDate;
        if (dataStr != null) {
            finalDate = formatter.dateFormatter(dataStr);
        } else {
            finalDate = LocalDateTime.now().plusDays(7);
            System.out.println("⚠️ Tarefa criada com prazo automático de 7 dias.");
        }




        var hasAlarm = askHasAlarm();
        int minutesBefore = hasAlarm ? askAlarmMinutes() : 0;

        Task newTask = Task.builder()
                .name(name)
                .description(desc)
                .priority(prioridade)
                .category(categoria)
                .status(status)
                .endDate(finalDate)
                .withAlarm(hasAlarm, minutesBefore)
                .build();

        taskService.createTask(newTask);
        System.out.println("Tarefa criada com sucesso!");

    }

    private boolean askHasAlarm() {
        System.out.print("Deseja ativar alarme? (S/N): ");
        return input.readString()
                .equalsIgnoreCase("S");
    }

    private int askAlarmMinutes() {
        System.out.print("Quantos minutos antes do vencimento deseja ser alertado? ");
        return input.readInt();
    }

    public void filterByCategory() {
        System.out.print("Digite a Categoria para filtrar: ");
        String catFilter = input.readString().toUpperCase();
        try {
            Category catEnum = Category.valueOf(catFilter);
            taskService.showTaskByCategory(catEnum).forEach(System.out::println);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    public void filterByStatus() {
        System.out.print("Digite o Status para filtrar (TODO, DOING...): ");
        String statFilter = input.readString().toUpperCase();
        try {
            Status statEnum = Status.valueOf(statFilter);
            taskService.showTasksByStatus(statEnum).forEach(System.out::println);
        } catch (IllegalArgumentException e) {
            System.out.println("Status inválido!");
        }
    }

    public void listAll() {
        System.out.println("\n--- Todas as Tarefas ---");
        taskService.showAllTasks().forEach(System.out::println);
    }

    public void deleteTask() {
        System.out.print("Digite o NOME da tarefa para deletar: ");
        String nomeDeletar = input.readString();
        taskService.deleteTask(nomeDeletar);
    }

    public void filterByPriority() {
        System.out.print("Digite a Prioridade (1-5) para filtrar: ");
        int prioFilter = input.readInt();
        taskService.showTaskByPriority(prioFilter).forEach(System.out::println);
    }

    public void updateTaskStatus() {
        System.out.print("Digite o NOME da tarefa para atualizar: ");
        String nomeAtualizar = input.readString();

        System.out.print("Digite o NOVO STATUS (TODO, DOING, DONE): ");
        String novoStatusStr = input.readString().toUpperCase();

        try {
            Status novoStatus = Status.valueOf(novoStatusStr);
            taskService.updateTaskStatus(nomeAtualizar, novoStatus);
            System.out.println("Status atualizado com sucesso!");
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

}
