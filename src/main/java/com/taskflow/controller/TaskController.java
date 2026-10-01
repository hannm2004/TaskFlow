package com.taskflow.controller;

import com.taskflow.dto.TaskRequest;
import com.taskflow.dto.TaskResponse;
import com.taskflow.entity.TaskEntity;
import com.taskflow.mapper.TaskMapper;
import com.taskflow.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Tasks", description = "CRUD operations for task management")
@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;
    private final TaskMapper taskMapper;

    public TaskController(TaskService taskService, TaskMapper taskMapper) {
        this.taskService = taskService;
        this.taskMapper = taskMapper;
    }

    @Operation(summary = "Get all tasks", description = "Returns a list of all tasks")
    @ApiResponse(responseCode = "200", description = "List of tasks retrieved successfully",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = TaskResponse.class)))
    @GetMapping
    public List<TaskResponse> getAllTasks() {
        return taskService.getAllTasks()
                .stream()
                .map(taskMapper::toResponse)
                .toList();
    }

    @Operation(summary = "Get task by ID", description = "Returns a single task by its ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Task found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TaskResponse.class))),
            @ApiResponse(responseCode = "404", description = "Task not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(example = "Task not found with id: 1")))
    })
    @GetMapping("/{id}")
    public TaskResponse getTaskById(
            @Parameter(description = "ID of the task to retrieve", required = true, example = "1")
            @PathVariable Long id) {
        TaskEntity task = taskService.getTaskById(id);
        return taskMapper.toResponse(task);
    }

    @Operation(summary = "Create a new task", description = "Creates a new task with the provided data")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Task created successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TaskResponse.class))),
            @ApiResponse(responseCode = "400", description = "Validation error – one or more fields are invalid",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(example = "Title must not be blank")))
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TaskResponse createTask(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Task data to create", required = true,
                    content = @Content(schema = @Schema(implementation = TaskRequest.class)))
            @Valid @RequestBody TaskRequest request) {
        TaskEntity task = taskMapper.toEntity(request);
        TaskEntity createdTask = taskService.createTask(task);
        return taskMapper.toResponse(createdTask);
    }

    @Operation(summary = "Update an existing task", description = "Updates task fields by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Task updated successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TaskResponse.class))),
            @ApiResponse(responseCode = "400", description = "Validation error – one or more fields are invalid",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(example = "Progress must be at least 0"))),
            @ApiResponse(responseCode = "404", description = "Task not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(example = "Task not found with id: 1")))
    })
    @PutMapping("/{id}")
    public TaskResponse updateTask(
            @Parameter(description = "ID of the task to update", required = true, example = "1")
            @PathVariable Long id,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Updated task data", required = true,
                    content = @Content(schema = @Schema(implementation = TaskRequest.class)))
            @Valid @RequestBody TaskRequest request) {
        TaskEntity task = taskMapper.toEntity(request);
        TaskEntity updatedTask = taskService.updateTask(id, task);
        return taskMapper.toResponse(updatedTask);
    }

    @Operation(summary = "Delete a task", description = "Deletes a task by its ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Task deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Task not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(example = "Task not found with id: 1")))
    })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTask(
            @Parameter(description = "ID of the task to delete", required = true, example = "1")
            @PathVariable Long id) {
        taskService.deleteTask(id);
    }
}