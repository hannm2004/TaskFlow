package com.taskflow.service;

import com.taskflow.dto.AuthResponse;
import com.taskflow.dto.LoginRequest;
import com.taskflow.dto.RegisterRequest;
import com.taskflow.entity.UserEntity;
import com.taskflow.exception.InvalidCredentialsException;
import com.taskflow.exception.UserAlreadyExistsException;
import com.taskflow.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * RED Phase Unit Test for AuthService (Security Checkpoint 02).
 * Verifies registration, login, exception semantics, password hashing, and response security.
 */
@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private AuthService authService;

    // ──────────────────────────────────────────────────────────────────────────
    // Registration Tests
    // ──────────────────────────────────────────────────────────────────────────

    @Test
    @DisplayName("register with valid request should hash password, persist user, and return AuthResponse without exposing password")
    void register_withValidRequest_shouldEncodePasswordAndSaveUser() {
        RegisterRequest request = new RegisterRequest("newuser@example.com", "PlainPassword123!");
        String rawPassword = request.getPassword();
        String hashedPassword = "$2a$10$encodedSecureHash789";

        when(userRepository.existsByEmail("newuser@example.com")).thenReturn(false);
        when(passwordEncoder.encode(rawPassword)).thenReturn(hashedPassword);

        UserEntity savedEntity = new UserEntity("newuser@example.com", hashedPassword);
        savedEntity.setId(10L);
        when(userRepository.save(any(UserEntity.class))).thenReturn(savedEntity);

        AuthResponse response = authService.register(request);

        assertNotNull(response);
        assertEquals(10L, response.getId());
        assertEquals("newuser@example.com", response.getEmail());

        // Verify password was hashed before persisting
        ArgumentCaptor<UserEntity> userCaptor = ArgumentCaptor.forClass(UserEntity.class);
        verify(userRepository).save(userCaptor.capture());
        UserEntity capturedUser = userCaptor.getValue();
        assertEquals(hashedPassword, capturedUser.getPassword());
        assertNotEquals(rawPassword, capturedUser.getPassword(), "Plaintext password must NEVER be persisted");
    }

    @Test
    @DisplayName("register with duplicate email should throw UserAlreadyExistsException and not persist")
    void register_withExistingEmail_shouldThrowUserAlreadyExistsException() {
        RegisterRequest request = new RegisterRequest("existing@example.com", "PlainPassword123!");

        when(userRepository.existsByEmail("existing@example.com")).thenReturn(true);

        UserAlreadyExistsException ex = assertThrows(UserAlreadyExistsException.class, () ->
                authService.register(request)
        );

        assertEquals("Email already exists: existing@example.com", ex.getMessage());
        verify(userRepository, never()).save(any(UserEntity.class));
    }

    // ──────────────────────────────────────────────────────────────────────────
    // Login Tests
    // ──────────────────────────────────────────────────────────────────────────

    @Test
    @DisplayName("login with valid credentials should return AuthResponse")
    void login_withValidCredentials_shouldReturnAuthResponse() {
        LoginRequest request = new LoginRequest("user@example.com", "CorrectPassword123!");
        String hashedPassword = "$2a$10$encodedSecureHash789";

        UserEntity user = new UserEntity("user@example.com", hashedPassword);
        user.setId(5L);

        when(userRepository.findByEmail("user@example.com")).thenReturn(Optional.of(user));
        when(passwordEncoder.matches("CorrectPassword123!", hashedPassword)).thenReturn(true);

        AuthResponse response = authService.login(request);

        assertNotNull(response);
        assertEquals(5L, response.getId());
        assertEquals("user@example.com", response.getEmail());
    }

    @Test
    @DisplayName("login with wrong password should throw InvalidCredentialsException with 'Invalid credentials'")
    void login_withWrongPassword_shouldThrowInvalidCredentialsException() {
        LoginRequest request = new LoginRequest("user@example.com", "WrongPassword!");
        String hashedPassword = "$2a$10$encodedSecureHash789";

        UserEntity user = new UserEntity("user@example.com", hashedPassword);
        user.setId(5L);

        when(userRepository.findByEmail("user@example.com")).thenReturn(Optional.of(user));
        when(passwordEncoder.matches("WrongPassword!", hashedPassword)).thenReturn(false);

        InvalidCredentialsException ex = assertThrows(InvalidCredentialsException.class, () ->
                authService.login(request)
        );

        assertEquals("Invalid credentials", ex.getMessage(),
                "Failure must use standard semantic error 'Invalid credentials'");
    }

    @Test
    @DisplayName("login with non-existing email should throw InvalidCredentialsException with same semantic error")
    void login_withNonExistingEmail_shouldThrowInvalidCredentialsException() {
        LoginRequest request = new LoginRequest("nonexisting@example.com", "AnyPassword!");

        when(userRepository.findByEmail("nonexisting@example.com")).thenReturn(Optional.empty());

        InvalidCredentialsException ex = assertThrows(InvalidCredentialsException.class, () ->
                authService.login(request)
        );

        assertEquals("Invalid credentials", ex.getMessage(),
                "Failure for non-existing email must use the exact same semantic error 'Invalid credentials' to avoid user enumeration");
    }
}
