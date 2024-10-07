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
        return taskRepository.save(task);
    }

    public List<Task> list() {
        Sort sort = Sort.by(Direction.DESC, "prioridade")
                .and(Sort.by(Direction.ASC, "id"));
        return taskRepository.findAll(sort);
    }

    public Task update(Task task) {
        if (task.getId() == null) {
            throw new IllegalArgumentException("O ID da tarefa não pode ser nulo para a atualização.");
        }

        if (!taskRepository.existsById(task.getId())) {
            throw new TaskNotFoundException(task.getId());
        }

        return taskRepository.save(task);
    }

    public void delete(Long id) {
        if (!taskRepository.existsById(id)) {
            throw new TaskNotFoundException(id);
        }
        taskRepository.deleteById(id);
    }
}