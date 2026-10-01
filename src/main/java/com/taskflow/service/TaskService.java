package com.taskflow.service;

import com.taskflow.entity.TaskEntity;
import com.taskflow.repository.TaskRepository;
import org.springframework.stereotype.Service;
import com.taskflow.exception.TaskNotFoundException;

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
        return taskRepository.findById(id)
                .orElseThrow(() ->
                        new TaskNotFoundException("Task not found with id: " + id));
    }

    public TaskEntity updateTask(Long id, TaskEntity updatedTask) {
        TaskEntity existingTask = taskRepository.findById(id)
                .orElseThrow(() ->
                        new TaskNotFoundException("Task not found with id: " + id));

        existingTask.setTitle(updatedTask.getTitle());
        existingTask.setDescription(updatedTask.getDescription());
        existingTask.setPriority(updatedTask.getPriority());
        existingTask.setStatus(updatedTask.getStatus());
        existingTask.setProgress(updatedTask.getProgress());

        return taskRepository.save(existingTask);
    }

    public void deleteTask(Long id) {
        if (!taskRepository.existsById(id)) {
            throw new TaskNotFoundException(
                    "Task not found with id: " + id
            );
        }

        taskRepository.deleteById(id);
    }
}