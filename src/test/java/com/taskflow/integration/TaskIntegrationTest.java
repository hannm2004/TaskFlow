package com.taskflow.integration;

import com.taskflow.entity.TaskEntity;
import com.taskflow.repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class TaskIntegrationTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Autowired
    private TaskRepository taskRepository;

    @BeforeEach
    void setUp() {
        // Apply Spring Security filter chain via SecurityMockMvcConfigurers
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .apply(SecurityMockMvcConfigurers.springSecurity())
                .build();
        taskRepository.deleteAll();
    }

    // ──────────────────────────────────────────────────────────────────────────
    // Security: Public endpoint
    // ──────────────────────────────────────────────────────────────────────────

    @Test
    void healthEndpoint_shouldBePublicAndReturnOk() throws Exception {
        // /api/health is permitAll — no authentication needed
        mockMvc.perform(get("/api/health"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("TaskFlow API is running")));
    }

    // ──────────────────────────────────────────────────────────────────────────
    // Security: Protected endpoints — unauthenticated must be rejected
    // ──────────────────────────────────────────────────────────────────────────

    @Test
    void getTasks_withoutAuth_shouldReturn401() throws Exception {
        mockMvc.perform(get("/api/tasks"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void createTask_withoutAuth_shouldReturn401() throws Exception {
        String requestJson = """
                {
                    "title": "Unauthorized Task",
                    "description": "Should be rejected",
                    "priority": "LOW",
                    "status": "TODO",
                    "progress": 0
                }
                """;
        mockMvc.perform(post("/api/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void updateTask_withoutAuth_shouldReturn401() throws Exception {
        mockMvc.perform(put("/api/tasks/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void deleteTask_withoutAuth_shouldReturn401() throws Exception {
        mockMvc.perform(delete("/api/tasks/1"))
                .andExpect(status().isUnauthorized());
    }

    // ──────────────────────────────────────────────────────────────────────────
    // Authenticated CRUD tests (using @WithMockUser)
    // ──────────────────────────────────────────────────────────────────────────

    @Test
    @WithMockUser
    void getAllTasks_shouldReturnTasksFromDatabase() throws Exception {
        TaskEntity task1 = new TaskEntity("Task 1", "Description 1", "HIGH", "TODO", 0);
        TaskEntity task2 = new TaskEntity("Task 2", "Description 2", "MEDIUM", "IN_PROGRESS", 40);
        taskRepository.save(task1);
        taskRepository.save(task2);

        mockMvc.perform(get("/api/tasks"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].title", is("Task 1")))
                .andExpect(jsonPath("$[1].title", is("Task 2")));
    }

    @Test
    @WithMockUser
    void getTaskById_shouldReturnTask_whenTaskExists() throws Exception {
        TaskEntity task = new TaskEntity("Existing Task", "Detail info", "HIGH", "TODO", 10);
        TaskEntity savedTask = taskRepository.save(task);

        mockMvc.perform(get("/api/tasks/" + savedTask.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(savedTask.getId().intValue())))
                .andExpect(jsonPath("$.title", is("Existing Task")))
                .andExpect(jsonPath("$.priority", is("HIGH")))
                .andExpect(jsonPath("$.status", is("TODO")))
                .andExpect(jsonPath("$.progress", is(10)));
    }

    @Test
    @WithMockUser
    void getTaskById_shouldReturn404_whenTaskDoesNotExist() throws Exception {
        mockMvc.perform(get("/api/tasks/999999"))
                .andExpect(status().isNotFound())
                .andExpect(content().string(containsString("Task not found with id: 999999")));
    }

    @Test
    @WithMockUser
    void createTask_shouldPersistAndReturnCreatedTask() throws Exception {
        String requestJson = """
                {
                    "title": "New Integration Task",
                    "description": "Created during integration testing",
                    "priority": "HIGH",
                    "status": "TODO",
                    "progress": 0
                }
                """;

        mockMvc.perform(post("/api/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.title", is("New Integration Task")))
                .andExpect(jsonPath("$.status", is("TODO")))
                .andExpect(jsonPath("$.progress", is(0)));

        assertEquals(1, taskRepository.count());
        TaskEntity saved = taskRepository.findAll().get(0);
        assertEquals("New Integration Task", saved.getTitle());
    }

    @Test
    @WithMockUser
    void createTask_shouldReturn400_whenValidationFails() throws Exception {
        String invalidJson = """
                {
                    "title": "",
                    "description": "Missing title test",
                    "priority": "LOW",
                    "status": "TODO",
                    "progress": 0
                }
                """;

        mockMvc.perform(post("/api/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidJson))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("Title must not be blank")));

        assertEquals(0, taskRepository.count());
    }

    @Test
    @WithMockUser
    void updateTask_shouldModifyAndReturnUpdatedTask() throws Exception {
        TaskEntity initialTask = new TaskEntity("Original Title", "Original Desc", "LOW", "TODO", 0);
        TaskEntity savedTask = taskRepository.save(initialTask);

        String updateJson = """
                {
                    "title": "Updated Title",
                    "description": "Updated Desc",
                    "priority": "URGENT",
                    "status": "IN_PROGRESS",
                    "progress": 75
                }
                """;

        mockMvc.perform(put("/api/tasks/" + savedTask.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updateJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(savedTask.getId().intValue())))
                .andExpect(jsonPath("$.title", is("Updated Title")))
                .andExpect(jsonPath("$.priority", is("URGENT")))
                .andExpect(jsonPath("$.status", is("IN_PROGRESS")))
                .andExpect(jsonPath("$.progress", is(75)));

        TaskEntity updatedInDb = taskRepository.findById(savedTask.getId()).orElseThrow();
        assertEquals("Updated Title", updatedInDb.getTitle());
        assertEquals("URGENT", updatedInDb.getPriority());
        assertEquals("IN_PROGRESS", updatedInDb.getStatus());
        assertEquals(75, updatedInDb.getProgress());
    }

    @Test
    @WithMockUser
    void deleteTask_shouldRemoveTaskFromDatabase() throws Exception {
        TaskEntity task = new TaskEntity("To Delete", "Will be removed", "LOW", "DONE", 100);
        TaskEntity savedTask = taskRepository.save(task);

        mockMvc.perform(delete("/api/tasks/" + savedTask.getId()))
                .andExpect(status().isNoContent());

        assertFalse(taskRepository.existsById(savedTask.getId()));
    }
}
