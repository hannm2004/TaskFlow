package com.taskflow.integration;

import com.taskflow.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * RED Phase Integration Test for Authentication Flow & Security Filters (Security Checkpoint 02).
 * Verifies public accessibility of /api/auth/**, protection of /api/tasks/**, registration,
 * login, validation error handling, and response payload security.
 */
@SpringBootTest
class AuthIntegrationTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Autowired(required = false)
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .apply(SecurityMockMvcConfigurers.springSecurity())
                .build();

        if (userRepository != null) {
            userRepository.deleteAll();
        }
    }

    // ──────────────────────────────────────────────────────────────────────────
    // Security Filter Chain: Public vs Protected Endpoints
    // ──────────────────────────────────────────────────────────────────────────

    @Test
    @DisplayName("register endpoint should be public and not return 401 Unauthorized")
    void registerEndpoint_shouldBePublic() throws Exception {
        String registerJson = """
                {
                    "email": "public_test@example.com",
                    "password": "Password123!"
                }
                """;

        // Without authentication header, POST /api/auth/register must NOT be rejected by SecurityFilterChain with 401
        mockMvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(registerJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.email", is("public_test@example.com")))
                .andExpect(jsonPath("$.password").doesNotExist());
    }

    @Test
    @DisplayName("login endpoint should be public and not return 401 Unauthorized from Security filter")
    void loginEndpoint_shouldBePublic() throws Exception {
        // Register user first
        String registerJson = """
                {
                    "email": "login_user@example.com",
                    "password": "Password123!"
                }
                """;
        mockMvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(registerJson))
                .andExpect(status().isCreated());

        // Perform login without any pre-existing auth token/header
        String loginJson = """
                {
                    "email": "login_user@example.com",
                    "password": "Password123!"
                }
                """;
        mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(loginJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.email", is("login_user@example.com")))
                .andExpect(jsonPath("$.password").doesNotExist());
    }

    @Test
    @DisplayName("Protected task endpoints must still return 401 when unauthenticated")
    void taskEndpoints_mustRemainProtected() throws Exception {
        mockMvc.perform(get("/api/tasks"))
                .andExpect(status().isUnauthorized());
    }

    // ──────────────────────────────────────────────────────────────────────────
    // Validation Errors (Registration)
    // ──────────────────────────────────────────────────────────────────────────

    @Test
    @DisplayName("register with blank email should return 400 Bad Request")
    void register_withBlankEmail_shouldReturn400() throws Exception {
        String invalidJson = """
                {
                    "email": "",
                    "password": "Password123!"
                }
                """;

        mockMvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("register with invalid email format should return 400 Bad Request")
    void register_withInvalidEmail_shouldReturn400() throws Exception {
        String invalidJson = """
                {
                    "email": "not-an-email-address",
                    "password": "Password123!"
                }
                """;

        mockMvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("register with blank password should return 400 Bad Request")
    void register_withBlankPassword_shouldReturn400() throws Exception {
        String invalidJson = """
                {
                    "email": "user@example.com",
                    "password": ""
                }
                """;

        mockMvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidJson))
                .andExpect(status().isBadRequest());
    }

    // ──────────────────────────────────────────────────────────────────────────
    // Registration: Duplicate Email
    // ──────────────────────────────────────────────────────────────────────────

    @Test
    @DisplayName("register with duplicate email should return error and not persist duplicate")
    void register_withDuplicateEmail_shouldReturnError() throws Exception {
        String registerJson = """
                {
                    "email": "duplicate@example.com",
                    "password": "Password123!"
                }
                """;

        // First registration succeeds
        mockMvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(registerJson))
                .andExpect(status().isCreated());

        // Duplicate registration fails
        mockMvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(registerJson))
                .andExpect(status().isConflict())
                .andExpect(content().string(containsString("Email already exists")));

        if (userRepository != null) {
            assertEquals(1, userRepository.count());
        }
    }

    // ──────────────────────────────────────────────────────────────────────────
    // Login: Wrong Credentials & Non-Existing Email
    // ──────────────────────────────────────────────────────────────────────────

    @Test
    @DisplayName("login with wrong password should return error with 'Invalid credentials'")
    void login_withWrongPassword_shouldReturnInvalidCredentials() throws Exception {
        String registerJson = """
                {
                    "email": "validuser@example.com",
                    "password": "CorrectPassword123!"
                }
                """;
        mockMvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(registerJson))
                .andExpect(status().isCreated());

        String wrongLoginJson = """
                {
                    "email": "validuser@example.com",
                    "password": "WrongPassword!"
                }
                """;
        mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(wrongLoginJson))
                .andExpect(status().isUnauthorized())
                .andExpect(content().string(containsString("Invalid credentials")));
    }

    @Test
    @DisplayName("login with non-existing email should return error with identical semantic 'Invalid credentials'")
    void login_withNonExistingEmail_shouldReturnInvalidCredentials() throws Exception {
        String nonExistingLoginJson = """
                {
                    "email": "nosuchuser@example.com",
                    "password": "AnyPassword123!"
                }
                """;
        mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(nonExistingLoginJson))
                .andExpect(status().isUnauthorized())
                .andExpect(content().string(containsString("Invalid credentials")));
    }
}
