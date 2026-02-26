package view;

import controller.TaskController;
import utils.InputReader;

public class Menu {

    private final TaskController controller;
    private final InputReader input = InputReader.getInstance();

    public Menu(TaskController controller) {
        this.controller = controller;
    }

    public void start() {
        while (true) {
            showMenu();

            int option = input.readInt();

            switch (option) {
                case 0:
                    System.out.println("Saindo... Foco nos estudos! 🚀");
                    System.exit(0);
                    break;
                case 1:
                    System.out.println("\n--- Nova Tarefa ---");
                    controller.createTask();
                    break;
                case 2:
                    controller.listAll();
                    break;
                case 3:
                    controller.filterByCategory();
                    break;

                case 4:
                    controller.filterByStatus();
                    break;

                case 5:
                    controller.filterByPriority();
                    break;

                case 6:

                case 7:
                    controller.deleteTask();
                    break;

                default:
                    System.out.println("Opção inválida, tente de novo.");
            }
        }
    }


    public void showMenu() {
        System.out.println("\n--- TODO LIST (Nível ZG) ---");
        System.out.println("1. Adicionar Tarefa");
        System.out.println("2. Listar Todas");
        System.out.println("3. Listar por Categoria");
        System.out.println("4. Listar por Status");
        System.out.println("5. Listar por Prioridade");
        System.out.println("6. Atualizar Tarefa");
        System.out.println("7. Deletar Tarefa");
        System.out.println("0. Sair");
    }


}
