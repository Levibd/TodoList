import model.Category;
import model.Status;
import model.Task;
import service.TaskService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        TaskService taskService = new TaskService();
        taskService.startAlarmMonitoring();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");


        while (true) {
            System.out.println("\n--- TODO LIST (Nível ZG) ---");
            System.out.println("1. Adicionar Tarefa");
            System.out.println("2. Listar Todas");
            System.out.println("3. Listar por Categoria");
            System.out.println("4. Listar por Status");
            System.out.println("5. Listar por Prioridade");
            System.out.println("6. Atualizar Tarefa");
            System.out.println("7. Deletar Tarefa");
            System.out.println("0. Sair");

            int option = input.nextInt();
            input.nextLine();

            switch (option) {
                case 0:
                    System.out.println("Saindo... Foco nos estudos! 🚀");
                    System.exit(0);
                    break;
                case 1:
                    System.out.println("\n--- Nova Tarefa ---");

                    System.out.print("Nome: ");
                    String name = input.nextLine();

                    System.out.print("Descrição: ");
                    String desc = input.nextLine();

                    System.out.print("Prioridade (1-5): ");
                    int prioridade = input.nextInt();
                    input.nextLine();

                    System.out.print("Categoria (TRABALHO, ESTUDOS, LAZER...): ");
                    String catStr = input.nextLine().toUpperCase();
                    Category categoria = Category.valueOf(catStr); // Converte String pra Enum

                    System.out.print("Status (TODO, DOING, DONE): ");
                    String statStr = input.nextLine().toUpperCase();
                    Status status = Status.valueOf(statStr);

                    System.out.print("Data de Término (dd/MM/yyyy HH:mm): ");
                    String dataStr = input.nextLine();
                    LocalDateTime endDate = null;

                    try {
                        endDate = LocalDateTime.parse(dataStr, formatter);
                    } catch (DateTimeParseException e) {
                        System.out.println("⚠️ Data inválida! Definindo para hoje + 7 dias.");
                        endDate = LocalDateTime.now().plusDays(7);
                    }

                    System.out.print("Deseja ativar alarme? (S/N): ");
                    String respAlarm = input.nextLine();
                    boolean hasAlarm = respAlarm.equalsIgnoreCase("S");
                    int minutesBefore = 0;

                    if (hasAlarm) {
                        System.out.print("Quantos minutos antes deseja ser avisado? (ex: 30): ");
                        minutesBefore = Integer.parseInt(input.nextLine());
                    }

                    Task newTask = Task.builder()
                            .name(name)
                            .description(desc)
                            .priority(prioridade)
                            .category(categoria)
                            .status(status)
                            .endDate(endDate)
                            .withAlarm(hasAlarm, minutesBefore)
                            .build();

                    taskService.createTask(newTask);
                    System.out.println("Tarefa criada com sucesso!");
                    break;

                case 2:
                    System.out.println("\n--- Todas as Tarefas ---");
                    taskService.showAllTasks().forEach(System.out::println);
                    break;

                case 3:
                    System.out.print("Digite a Categoria para filtrar: ");
                    String catFilter = input.nextLine().toUpperCase();
                    try {
                        Category catEnum = Category.valueOf(catFilter);
                        taskService.showTaskByCategory(catEnum).forEach(System.out::println);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Categoria inválida!");
                    }
                    break;

                case 4:
                    System.out.print("Digite o Status para filtrar (TODO, DOING...): ");
                    String statFilter = input.nextLine().toUpperCase();
                    try {
                        Status statEnum = Status.valueOf(statFilter);
                        taskService.showTasksByStatus(statEnum).forEach(System.out::println);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Status inválido!");
                    }
                    break;

                case 5:
                    System.out.print("Digite a Prioridade (1-5): ");
                    int prio = input.nextInt();
                    input.nextLine(); // Limpa buffer
                    taskService.showTaskByPriority(prio).forEach(System.out::println);
                    break;

                case 7:
                    System.out.print("Digite o NOME da tarefa para deletar: ");
                    String nomeDeletar = input.nextLine();
                    taskService.deleteTask(nomeDeletar);
                    break;

                default:
                    System.out.println("Opção inválida, tente de novo.");
            }


        }


    }
}