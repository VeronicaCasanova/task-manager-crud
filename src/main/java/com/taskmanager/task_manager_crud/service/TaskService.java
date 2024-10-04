package com.taskmanager.task_manager_crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.taskmanager.task_manager_crud.entity.Task;
import com.taskmanager.task_manager_crud.exception.TaskNotFoundException;
import com.taskmanager.task_manager_crud.repository.TaskRepository;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task create(Task task) {
        return taskRepository.save(task); // Retorna a task criada
    }

    public List<Task> list() {
        Sort sort = Sort.by(Direction.DESC, "prioridade")
                .and(Sort.by(Direction.ASC, "id"));
        return taskRepository.findAll(sort);
    }

    public Task update(Task task) {
        if (!taskRepository.existsById(task.getId())) {
            throw new TaskNotFoundException(task.getId()); // Passar o id como Long
        }
        return taskRepository.save(task); // Retorna a task atualizada
    }

    public void delete(Long id) {
        if (!taskRepository.existsById(id)) {
            throw new TaskNotFoundException(id); // Passar o id como Long
        }
        taskRepository.deleteById(id); // Deleta a task
    }
}