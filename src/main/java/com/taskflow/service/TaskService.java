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

    public TaskEntity createTask(TaskEntity task) {
        return taskRepository.save(task);
    }

    public TaskEntity getTaskById(Long id) {
        return taskRepository.findById(id).orElse(null);
    }
}