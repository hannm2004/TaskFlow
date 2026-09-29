package com.taskflow.controller;

import com.taskflow.entity.TaskEntity;
import com.taskflow.service.TaskService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.List;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import jakarta.validation.Valid;
import com.taskflow.dto.TaskRequest;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public List<TaskEntity> getAllTasks() {
        return taskService.getAllTasks();
    }

    @PostMapping
    public TaskEntity createTask(@Valid @RequestBody TaskRequest request) {

        TaskEntity task = new TaskEntity(
                request.getTitle(),
                request.getDescription(),
                request.getPriority(),
                request.getStatus(),
                request.getProgress()
        );

        return taskService.createTask(task);
    }

    @PutMapping("/{id}")
    public TaskEntity updateTask(
            @PathVariable Long id,
            @Valid @RequestBody TaskRequest request) {

        TaskEntity task = new TaskEntity(
                request.getTitle(),
                request.getDescription(),
                request.getPriority(),
                request.getStatus(),
                request.getProgress()
        );

        return taskService.updateTask(id, task);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
    }
}