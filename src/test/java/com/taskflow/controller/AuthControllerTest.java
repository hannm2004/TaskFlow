package com.taskflow.controller;

import com.taskflow.dto.AuthResponse;
import com.taskflow.dto.LoginRequest;
import com.taskflow.dto.RegisterRequest;
import com.taskflow.exception.InvalidCredentialsException;
import com.taskflow.exception.UserAlreadyExistsException;
import com.taskflow.service.AuthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * RED Phase Unit & WebMvc Test for AuthController (Security Checkpoint 02).
 * Verifies endpoint mapping, status codes, and response JSON payload contracts.
 */
@ExtendWith(MockitoExtension.class)
class AuthControllerTest {

    @Mock
    private AuthService authService;

    @InjectMocks
    private AuthController authController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(authController).build();
    }

    @Test
    @DisplayName("register should return 201 Created and AuthResponse with user details")
    void register_shouldReturnCreatedAndAuthResponse() throws Exception {
        RegisterRequest request = new RegisterRequest("newuser@example.com", "Password123!");
        AuthResponse response = new AuthResponse(1L, "newuser@example.com");

        when(authService.register(any(RegisterRequest.class))).thenReturn(response);

        String jsonBody = """
                {
                    "email": "newuser@example.com",
                    "password": "Password123!"
                }
                """;

        mockMvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.email", is("newuser@example.com")))
                .andExpect(jsonPath("$.password").doesNotExist());
    }

    @Test
    @DisplayName("login should return 200 OK and AuthResponse with user details")
    void login_shouldReturnOkAndAuthResponse() throws Exception {
        LoginRequest request = new LoginRequest("user@example.com", "Password123!");
        AuthResponse response = new AuthResponse(2L, "user@example.com");

        when(authService.login(any(LoginRequest.class))).thenReturn(response);

        String jsonBody = """
                {
                    "email": "user@example.com",
                    "password": "Password123!"
                }
                """;

        mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(2)))
                .andExpect(jsonPath("$.email", is("user@example.com")))
                .andExpect(jsonPath("$.password").doesNotExist());
    }

    @Test
    @DisplayName("register unit call should return 201 Created ResponseEntity")
    void register_unitCall_shouldReturnCreatedResponse() {
        RegisterRequest request = new RegisterRequest("test@example.com", "Password123!");
        AuthResponse authResponse = new AuthResponse(1L, "test@example.com");

        when(authService.register(request)).thenReturn(authResponse);

        ResponseEntity<AuthResponse> response = authController.register(request);

        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(1L, response.getBody().getId());
        assertEquals("test@example.com", response.getBody().getEmail());
    }

    @Test
    @DisplayName("login unit call should return 200 OK ResponseEntity")
    void login_unitCall_shouldReturnOkResponse() {
        LoginRequest request = new LoginRequest("test@example.com", "Password123!");
        AuthResponse authResponse = new AuthResponse(1L, "test@example.com");

        when(authService.login(request)).thenReturn(authResponse);

        ResponseEntity<AuthResponse> response = authController.login(request);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1L, response.getBody().getId());
        assertEquals("test@example.com", response.getBody().getEmail());
    }

    @Test
    @DisplayName("register should propagate UserAlreadyExistsException when duplicate email")
    void register_whenDuplicateEmail_shouldPropagateException() {
        RegisterRequest request = new RegisterRequest("duplicate@example.com", "Password123!");

        when(authService.register(request))
                .thenThrow(new UserAlreadyExistsException("Email already exists: duplicate@example.com"));

        assertThrows(UserAlreadyExistsException.class, () ->
                authController.register(request)
        );
    }

    @Test
    @DisplayName("login should propagate InvalidCredentialsException when wrong password or non-existing email")
    void login_whenInvalidCredentials_shouldPropagateException() {
        LoginRequest request = new LoginRequest("user@example.com", "WrongPassword!");

        when(authService.login(request))
                .thenThrow(new InvalidCredentialsException("Invalid credentials"));

        assertThrows(InvalidCredentialsException.class, () ->
                authController.login(request)
        );
    }
}
