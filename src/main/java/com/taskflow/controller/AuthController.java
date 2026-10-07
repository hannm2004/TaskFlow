package com.taskflow.controller;

import com.taskflow.dto.AuthResponse;
import com.taskflow.dto.LoginRequest;
import com.taskflow.dto.RegisterRequest;
import com.taskflow.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for authentication endpoints.
 *
 * Security Checkpoint 02:
 * - POST /api/auth/register → 201 Created
 * - POST /api/auth/login    → 200 OK
 *
 * Both endpoints are public (no authentication required).
 * Validation errors → 400 (handled by GlobalExceptionHandler via MethodArgumentNotValidException)
 * Duplicate email → 409 (handled by GlobalExceptionHandler via UserAlreadyExistsException)
 * Invalid credentials → 401 (handled by GlobalExceptionHandler via InvalidCredentialsException)
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    /**
     * Register a new user account.
     *
     * @param request registration data with email and password (validated)
     * @return 201 Created with AuthResponse (id and email, never password)
     */
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody RegisterRequest request) {
        AuthResponse response = authService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * Authenticate an existing user.
     *
     * @param request login credentials (email and password, validated)
     * @return 200 OK with AuthResponse (id and email, never password)
     */
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
        AuthResponse response = authService.login(request);
        return ResponseEntity.ok(response);
    }
}
