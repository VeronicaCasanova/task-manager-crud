package com.taskmanager.task_manager_crud.repository;

import com.taskmanager.task_manager_crud.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {

}