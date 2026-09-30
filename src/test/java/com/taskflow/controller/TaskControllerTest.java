package com.taskflow.controller;

import com.taskflow.dto.TaskResponse;
import com.taskflow.entity.TaskEntity;
import com.taskflow.mapper.TaskMapper;
import com.taskflow.service.TaskService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.taskflow.dto.TaskRequest;
import java.util.List;
import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TaskControllerTest {

    @Mock
    private TaskService taskService;

    @Mock
    private TaskMapper taskMapper;

    @InjectMocks
    private TaskController taskController;

    @Test
    void getAllTasks_shouldReturnTaskResponses() {

        TaskEntity task = new TaskEntity(
                "Test Task",
                "Test description",
                "HIGH",
                "TODO",
                0
        );

        TaskResponse response = new TaskResponse(
                1L,
                "Test Task",
                "Test description",
                "HIGH",
                "TODO",
                0
        );

        when(taskService.getAllTasks())
                .thenReturn(List.of(task));

        when(taskMapper.toResponse(task))
                .thenReturn(response);

        List<TaskResponse> result = taskController.getAllTasks();

        assertEquals(1, result.size());
        assertEquals("Test Task", result.get(0).getTitle());
    }

    @Test
    void getTaskById_shouldReturnTaskResponse() {

        TaskEntity task = new TaskEntity(
                "Test Task",
                "Test description",
                "HIGH",
                "TODO",
                0
        );

        TaskResponse response = new TaskResponse(
                1L,
                "Test Task",
                "Test description",
                "HIGH",
                "TODO",
                0
        );

        when(taskService.getTaskById(1L))
                .thenReturn(task);

        when(taskMapper.toResponse(task))
                .thenReturn(response);

        TaskResponse result = taskController.getTaskById(1L);

        assertEquals(1L, result.getId());
        assertEquals("Test Task", result.getTitle());
    }

    @Test
    void createTask_shouldReturnCreatedTaskResponse() {

        TaskRequest request = new TaskRequest();
        request.setTitle("New Task");
        request.setDescription("New description");
        request.setPriority("HIGH");
        request.setStatus("TODO");
        request.setProgress(0);

        TaskEntity task = new TaskEntity(
                "New Task",
                "New description",
                "HIGH",
                "TODO",
                0
        );

        TaskResponse response = new TaskResponse(
                1L,
                "New Task",
                "New description",
                "HIGH",
                "TODO",
                0
        );

        when(taskMapper.toEntity(request))
                .thenReturn(task);

        when(taskService.createTask(task))
                .thenReturn(task);

        when(taskMapper.toResponse(task))
                .thenReturn(response);

        TaskResponse result = taskController.createTask(request);

        assertEquals(1L, result.getId());
        assertEquals("New Task", result.getTitle());
    }

    @Test
    void updateTask_shouldReturnUpdatedTaskResponse() {

        TaskRequest request = new TaskRequest();
        request.setTitle("Updated Task");
        request.setDescription("Updated description");
        request.setPriority("URGENT");
        request.setStatus("IN_PROGRESS");
        request.setProgress(80);

        TaskEntity task = new TaskEntity(
                "Updated Task",
                "Updated description",
                "URGENT",
                "IN_PROGRESS",
                80
        );

        TaskResponse response = new TaskResponse(
                1L,
                "Updated Task",
                "Updated description",
                "URGENT",
                "IN_PROGRESS",
                80
        );

        when(taskMapper.toEntity(request))
                .thenReturn(task);

        when(taskService.updateTask(1L, task))
                .thenReturn(task);

        when(taskMapper.toResponse(task))
                .thenReturn(response);

        TaskResponse result = taskController.updateTask(1L, request);

        assertEquals(1L, result.getId());
        assertEquals("Updated Task", result.getTitle());
        assertEquals("IN_PROGRESS", result.getStatus());
        assertEquals(80, result.getProgress());
    }

    @Test
    void deleteTask_shouldDeleteTask() {

        taskController.deleteTask(1L);

        verify(taskService).deleteTask(1L);
    }
}