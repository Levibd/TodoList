package service;

import model.Category;
import model.Status;
import model.Task;

import java.util.Objects;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TaskService {

    private List<Task> repository = new ArrayList<>();
    private Long countId = 1L;

    public void createTask(Task task) {
        repository.add(task);

        repository.sort((t1, t2) -> t2.getPriority().compareTo(t1.getPriority()));
    }

    public List<Task> showAllTasks() {
        return repository;
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


    public void deleteTask(String name) {
        boolean remove = repository.removeIf(x -> x.getName().equalsIgnoreCase(name));
        if (remove) {
            System.out.println("Tarefa removida com sucesso!");
        } else {
            System.out.println("Tarefa não encontrada para remoção.");
        }
    }


}
