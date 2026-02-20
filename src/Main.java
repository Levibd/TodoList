import controller.TaskController;
import model.Category;
import model.Status;
import model.Task;
import service.TaskService;
import utils.InputReader;
import view.Menu;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        TaskService taskService = new TaskService();
        taskService.startAlarmMonitoring();

        TaskController controller = new TaskController(taskService);

        Menu menu = new Menu(controller);
        menu.start();


    }
}