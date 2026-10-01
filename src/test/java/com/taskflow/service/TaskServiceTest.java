package com.taskflow.service;

import com.taskflow.entity.TaskEntity;
import com.taskflow.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.taskflow.exception.TaskNotFoundException;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskService taskService;

    @Test
    void getTaskById_shouldReturnTask_whenTaskExists() {

        TaskEntity task = new TaskEntity(
                "Test Task",
                "Test description",
                "HIGH",
                "TODO",
                0
        );

        when(taskRepository.findById(1L))
                .thenReturn(Optional.of(task));

        TaskEntity result = taskService.getTaskById(1L);

        assertEquals("Test Task", result.getTitle());
    }

    @Test
    void getTaskById_shouldThrowException_whenTaskDoesNotExist() {

        when(taskRepository.findById(999L))
                .thenReturn(Optional.empty());

        assertThrows(
                TaskNotFoundException.class,
                () -> taskService.getTaskById(999L)
        );
    }

    @Test
    void createTask_shouldSaveTask() {

        TaskEntity task = new TaskEntity(
                "Create Test",
                "Testing create task",
                "HIGH",
                "TODO",
                0
        );

        when(taskRepository.save(task))
                .thenReturn(task);

        TaskEntity result = taskService.createTask(task);

        assertEquals("Create Test", result.getTitle());

        verify(taskRepository).save(task);
    }

    @Test
    void updateTask_shouldUpdateExistingTask() {

        TaskEntity existingTask = new TaskEntity(
                "Old Task",
                "Old description",
                "LOW",
                "TODO",
                0
        );

        TaskEntity updatedTask = new TaskEntity(
                "Updated Task",
                "Updated description",
                "HIGH",
                "IN_PROGRESS",
                50
        );

        when(taskRepository.findById(1L))
                .thenReturn(Optional.of(existingTask));

        when(taskRepository.save(existingTask))
                .thenReturn(existingTask);

        TaskEntity result = taskService.updateTask(1L, updatedTask);

        assertEquals("Updated Task", result.getTitle());
        assertEquals("Updated description", result.getDescription());
        assertEquals("HIGH", result.getPriority());
        assertEquals("IN_PROGRESS", result.getStatus());
        assertEquals(50, result.getProgress());

        verify(taskRepository).save(existingTask);
    }

    @Test
    void deleteTask_shouldDeleteExistingTask() {

        when(taskRepository.existsById(1L))
                .thenReturn(true);

        taskService.deleteTask(1L);

        verify(taskRepository).existsById(1L);
        verify(taskRepository).deleteById(1L);
    }

    @Test
    void deleteTask_shouldThrowException_whenTaskDoesNotExist() {

        when(taskRepository.existsById(999L))
                .thenReturn(false);

        assertThrows(
                TaskNotFoundException.class,
                () -> taskService.deleteTask(999L)
        );

        verify(taskRepository).existsById(999L);
    }
}