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
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

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

/**
 * Integration Tests using Testcontainers with real MySQL 8.4.
 *
 * A dedicated MySQL container is spun up by Testcontainers for each test run
 * and destroyed afterwards. It is completely isolated from the development
 * database (taskflow-mysql / taskflow_db).
 */
@Testcontainers
@SpringBootTest
class MySQLIntegrationTest {


    // Shared MySQL 8.4 container — created once per test class, destroyed after all tests
    @Container
    static MySQLContainer<?> mysql = new MySQLContainer<>("mysql:8.4")
            .withDatabaseName("taskflow_test_tc")
            .withUsername("testuser")
            .withPassword("testpass");

    /**
     * Override Spring datasource properties with the dynamic port/host
     * that Testcontainers assigned to the container.
     * This prevents any connection to the development MySQL instance.
     */
    @DynamicPropertySource
    static void overrideDataSourceProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url",     mysql::getJdbcUrl);
        registry.add("spring.datasource.username", mysql::getUsername);
        registry.add("spring.datasource.password", mysql::getPassword);
        registry.add("spring.datasource.driverClassName", () -> "com.mysql.cj.jdbc.Driver");
        registry.add("spring.jpa.hibernate.ddl-auto", () -> "create-drop");
        registry.add("spring.jpa.database-platform", () -> "org.hibernate.dialect.MySQLDialect");
    }

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
    void tc_healthEndpoint_shouldBePublicAndReturnOk() throws Exception {
        // /api/health is permitAll — no authentication needed
        mockMvc.perform(get("/api/health"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("TaskFlow API is running")));
    }

    // ──────────────────────────────────────────────────────────────────────────
    // Security: Protected endpoints — unauthenticated must be rejected
    // ──────────────────────────────────────────────────────────────────────────

    @Test
    void tc_getTasks_withoutAuth_shouldReturn401() throws Exception {
        mockMvc.perform(get("/api/tasks"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void tc_createTask_withoutAuth_shouldReturn401() throws Exception {
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
    void tc_updateTask_withoutAuth_shouldReturn401() throws Exception {
        mockMvc.perform(put("/api/tasks/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void tc_deleteTask_withoutAuth_shouldReturn401() throws Exception {
        mockMvc.perform(delete("/api/tasks/1"))
                .andExpect(status().isUnauthorized());
    }

    // ──────────────────────────────────────────────────────────────────────────
    // Authenticated CRUD tests (using @WithMockUser)
    // ──────────────────────────────────────────────────────────────────────────

    @Test
    @WithMockUser
    void tc_getAllTasks_shouldReturnTasksFromDatabase() throws Exception {
        TaskEntity task1 = new TaskEntity("TC Task 1", "Description 1", "HIGH", "TODO", 0);
        TaskEntity task2 = new TaskEntity("TC Task 2", "Description 2", "MEDIUM", "IN_PROGRESS", 40);
        taskRepository.save(task1);
        taskRepository.save(task2);

        mockMvc.perform(get("/api/tasks"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].title", is("TC Task 1")))
                .andExpect(jsonPath("$[1].title", is("TC Task 2")));
    }

    @Test
    @WithMockUser
    void tc_getTaskById_shouldReturnTask_whenTaskExists() throws Exception {
        TaskEntity task = new TaskEntity("TC Existing Task", "Detail info", "HIGH", "TODO", 10);
        TaskEntity savedTask = taskRepository.save(task);

        mockMvc.perform(get("/api/tasks/" + savedTask.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(savedTask.getId().intValue())))
                .andExpect(jsonPath("$.title", is("TC Existing Task")))
                .andExpect(jsonPath("$.priority", is("HIGH")))
                .andExpect(jsonPath("$.status", is("TODO")))
                .andExpect(jsonPath("$.progress", is(10)));
    }

    @Test
    @WithMockUser
    void tc_getTaskById_shouldReturn404_whenTaskDoesNotExist() throws Exception {
        mockMvc.perform(get("/api/tasks/999999"))
                .andExpect(status().isNotFound())
                .andExpect(content().string(containsString("Task not found with id: 999999")));
    }

    @Test
    @WithMockUser
    void tc_createTask_shouldPersistAndReturnCreatedTask() throws Exception {
        String requestJson = """
                {
                    "title": "TC New Integration Task",
                    "description": "Created during Testcontainers integration testing",
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
                .andExpect(jsonPath("$.title", is("TC New Integration Task")))
                .andExpect(jsonPath("$.status", is("TODO")))
                .andExpect(jsonPath("$.progress", is(0)));

        assertEquals(1, taskRepository.count());
        TaskEntity saved = taskRepository.findAll().get(0);
        assertEquals("TC New Integration Task", saved.getTitle());
    }

    @Test
    @WithMockUser
    void tc_createTask_shouldReturn400_whenValidationFails() throws Exception {
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
    void tc_updateTask_shouldModifyAndReturnUpdatedTask() throws Exception {
        TaskEntity initialTask = new TaskEntity("TC Original Title", "TC Original Desc", "LOW", "TODO", 0);
        TaskEntity savedTask = taskRepository.save(initialTask);

        String updateJson = """
                {
                    "title": "TC Updated Title",
                    "description": "TC Updated Desc",
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
                .andExpect(jsonPath("$.title", is("TC Updated Title")))
                .andExpect(jsonPath("$.priority", is("URGENT")))
                .andExpect(jsonPath("$.status", is("IN_PROGRESS")))
                .andExpect(jsonPath("$.progress", is(75)));

        TaskEntity updatedInDb = taskRepository.findById(savedTask.getId()).orElseThrow();
        assertEquals("TC Updated Title", updatedInDb.getTitle());
        assertEquals("URGENT", updatedInDb.getPriority());
        assertEquals("IN_PROGRESS", updatedInDb.getStatus());
        assertEquals(75, updatedInDb.getProgress());
    }

    @Test
    @WithMockUser
    void tc_deleteTask_shouldRemoveTaskFromDatabase() throws Exception {
        TaskEntity task = new TaskEntity("TC To Delete", "Will be removed", "LOW", "DONE", 100);
        TaskEntity savedTask = taskRepository.save(task);

        mockMvc.perform(delete("/api/tasks/" + savedTask.getId()))
                .andExpect(status().isNoContent());

        assertFalse(taskRepository.existsById(savedTask.getId()));
    }
}
