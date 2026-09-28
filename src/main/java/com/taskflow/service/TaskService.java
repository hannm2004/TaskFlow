package com.taskflow.service;

import com.taskflow.entity.TaskEntity;
import com.taskflow.repository.TaskRepository;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public java.util.List<TaskEntity> getAllTasks() {
        return taskRepository.findAll();
    }
}