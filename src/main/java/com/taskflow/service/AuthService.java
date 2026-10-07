package com.taskflow.service;

import com.taskflow.dto.AuthResponse;
import com.taskflow.dto.LoginRequest;
import com.taskflow.dto.RegisterRequest;
import com.taskflow.entity.UserEntity;
import com.taskflow.exception.InvalidCredentialsException;
import com.taskflow.exception.UserAlreadyExistsException;
import com.taskflow.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Authentication service handling user registration and login.
 *
 * Security Checkpoint 02:
 * - Registration: validate email uniqueness, encode password with BCrypt, persist, return AuthResponse
 * - Login: find user by email, verify BCrypt password, return AuthResponse
 * - Both email-not-found and wrong-password throw the same exception with the same message
 *   to prevent user enumeration attacks.
 * - Password is NEVER exposed in AuthResponse.
 */
@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Registers a new user.
     *
     * @param request registration data (email + raw password)
     * @return AuthResponse with id and email (no password)
     * @throws UserAlreadyExistsException if email is already registered
     */
    public AuthResponse register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new UserAlreadyExistsException("Email already exists: " + request.getEmail());
        }

        String encodedPassword = passwordEncoder.encode(request.getPassword());
        UserEntity user = new UserEntity(request.getEmail(), encodedPassword);
        UserEntity saved = userRepository.save(user);

        return new AuthResponse(saved.getId(), saved.getEmail());
    }

    /**
     * Authenticates a user by email and password.
     *
     * @param request login data (email + raw password)
     * @return AuthResponse with id and email (no password)
     * @throws InvalidCredentialsException if email not found or password does not match
     */
    public AuthResponse login(LoginRequest request) {
        UserEntity user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new InvalidCredentialsException("Invalid credentials"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new InvalidCredentialsException("Invalid credentials");
        }

        return new AuthResponse(user.getId(), user.getEmail());
    }
}
