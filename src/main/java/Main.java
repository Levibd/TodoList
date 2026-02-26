import controller.TaskController;
import service.TaskService;
import view.Menu;

public class Main {
    public static void main(String[] args) {

        TaskService taskService = new TaskService();
        taskService.startAlarmMonitoring();

        TaskController controller = new TaskController(taskService);

        Menu menu = new Menu(controller);
        menu.start();


    }
}