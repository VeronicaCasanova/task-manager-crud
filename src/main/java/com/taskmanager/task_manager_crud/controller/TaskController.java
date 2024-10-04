package com.taskmanager.task_manager_crud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taskmanager.task_manager_crud.entity.Task;
import com.taskmanager.task_manager_crud.service.TaskService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public Task create(@RequestBody @Valid Task task) { // Alterado para retornar uma Task
        return taskService.create(task);
    }

    @GetMapping
    public List<Task> list() {
        return taskService.list();
    }

    @PutMapping
    public ResponseEntity<Task> update(@RequestBody @Valid Task task) {
        if (task.getId() == null) {
            throw new IllegalArgumentException("O campo 'id' é obrigatório para atualizar a task.");
        }

        Task updatedTask = taskService.update(task); // Alterado para retornar uma Task
        return ResponseEntity.ok(updatedTask);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        taskService.delete(id); // Apenas chama delete, sem verificar retorno
        return ResponseEntity.ok().build(); // Retorna 200 OK
    }
}