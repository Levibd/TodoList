package service;

import model.Category;
import model.Status;
import model.Task;

import java.time.LocalDateTime;
import java.util.Objects;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TaskService {

    private List<Task> repository = new ArrayList<>();


    public void createTask(Task task) {

        if (task == null || task.getName() == null || task.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("O nome da tarefa é obrigatório.");
        }
        repository.add(task);

        repository.sort((t1, t2) -> t2.getPriority().compareTo(t1.getPriority()));
    }

    public List<Task> showAllTasks() {
        return new ArrayList<>(repository);
    }

    public List<Task> showTasksByStatus(Status status) {
        return repository.stream().filter(x -> x.getStatus() == status).collect(Collectors.toList());
    }

    public List<Task> showTaskByCategory(Category category) {
        return repository.stream()
                .filter(x -> x.getCategory() == category)
                .collect(Collectors.toList());
    }

    public List<Task> showTaskByPriority(Integer priority) {
        return repository.stream()
                .filter(x -> Objects.equals(x.getPriority(),priority))
                .collect(Collectors.toList());
    }


    public boolean deleteTask(String name) {
        return repository.removeIf(task -> task.getName().equalsIgnoreCase(name));
    }

    public void startAlarmMonitoring() {
        Thread watcher = new Thread(() -> {
            while (true) {
                try {
                    checkAlarms();
                    Thread.sleep(60000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    e.printStackTrace();
                }
            }
        });
        watcher.setDaemon(true);
        watcher.start();
    }

    private void checkAlarms() {
        LocalDateTime now = LocalDateTime.now();

        for (Task task : repository) {
            if (task.hasAlarm() && task.getEndDate() != null) {

                LocalDateTime triggerTime = task.getEndDate().minusMinutes(task.getMinutesBefore());

                if (now.isAfter(triggerTime) && now.isBefore(task.getEndDate())) {
                    System.out.println("\n\n🚨 [ALARME] A tarefa '" + task.getName() +
                            "' vence em breve! (" + task.getEndDate() + ")\n");
                }
            }
        }
    }

    public boolean updateTaskStatus(String name, Status newStatus) {
        for (Task task : repository) {
            if (task.getName().equalsIgnoreCase(name)) {
                task.setStatus(newStatus);
                return true;
            }
        }
        return false;
    }
}



