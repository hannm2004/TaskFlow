package com.taskflow.dto;

/**
 * Response DTO returned after successful registration or login.
 *
 * Security Checkpoint 02:
 * - Contains only id and email — NEVER password or password hash.
 * - No token field in CP02 (JWT deferred).
 */
public class AuthResponse {

    private Long id;
    private String email;

    /**
     * No-args constructor for serialization.
     */
    public AuthResponse() {
    }

    public AuthResponse(Long id, String email) {
        this.id = id;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
