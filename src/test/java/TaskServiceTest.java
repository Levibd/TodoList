package test.java;

import model.Status;
import model.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.TaskService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TaskServiceTest {

    private TaskService service;

    @BeforeEach
    void createTask() {
        this.service = new TaskService();
    }

    @Test
    void testCreateTask() {
        Task task = Task.builder()
                .name("Estudar Java")
                .priority(5)
                .status(Status.TODO)
                .build();

        service.createTask(task);

        List<Task> lista = service.showAllTasks();
        assertEquals(1, lista.size());
        assertEquals("Estudar Java", lista.get(0).getName());

        System.out.println("Teste CREATE: Tarefa criada com sucesso! ✅");
    }

    @Test
    void deleteTask() {
        Task task = Task.builder().name("Tarefa Remover").build();
        service.createTask(task);

        service.deleteTask("Tarefa Remover");

        assertTrue(service.showAllTasks().isEmpty());

        System.out.println("Teste DELETE: Tarefa removida com sucesso! ✅");
    }

    @Test
    void testUpdateTaskStatus(){
        Task task = Task.builder().name("Tarefa Update").status(Status.TODO).build();
        service.createTask(task);

        service.updateTaskStatus("Tarefa Update", Status.DONE);

        Task tarefaAtualizada = service.showAllTasks().get(0);
        assertEquals(Status.DONE, tarefaAtualizada.getStatus());

        System.out.println("Teste UPDATE: Status atualizado de TODO para DONE! ✅");
    }
}