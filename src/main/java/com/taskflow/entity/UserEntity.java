package com.taskflow.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

/**
 * JPA entity representing an authenticated user in TaskFlow.
 *
 * Security Checkpoint 02:
 * - email is the identity (unique, not null)
 * - password stored as BCrypt hash (never plaintext)
 * - No roles in CP02
 * - No relationship with TaskEntity in CP02
 */
@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Email must not be blank")
    @Email(message = "Email must be a valid email address")
    @Column(unique = true, nullable = false)
    private String email;

    @NotBlank(message = "Password must not be blank")
    @Column(nullable = false)
    private String password;

    /**
     * No-args constructor required by JPA.
     */
    public UserEntity() {
    }

    /**
     * Convenience constructor for creating a user with email and (already encoded) password.
     *
     * @param email   user's unique email address
     * @param password BCrypt-encoded password hash
     */
    public UserEntity(String email, String password) {
        this.email = email;
        this.password = password;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
