package com.taskflow.security;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * RED Phase Test for PasswordEncoder (Security Checkpoint 02).
 * Verifies that a BCryptPasswordEncoder bean is registered and performs secure hashing.
 */
@SpringBootTest
class PasswordEncoderTest {

    @Autowired(required = false)
    private PasswordEncoder passwordEncoder;

    @Test
    @DisplayName("PasswordEncoder bean must exist and be an instance of BCryptPasswordEncoder")
    void passwordEncoderBean_shouldExistAndBeBCryptPasswordEncoder() {
        assertNotNull(passwordEncoder, "PasswordEncoder bean must be registered in Spring context");
        assertInstanceOf(BCryptPasswordEncoder.class, passwordEncoder, "PasswordEncoder must be BCryptPasswordEncoder");
    }

    @Test
    @DisplayName("encode should hash plaintext password and not return plaintext")
    void encode_shouldHashPasswordAndNotMatchPlaintext() {
        assertNotNull(passwordEncoder, "PasswordEncoder bean is required");
        String rawPassword = "SecurePassword123!";

        String encoded = passwordEncoder.encode(rawPassword);

        assertNotNull(encoded, "Encoded password must not be null");
        assertNotEquals(rawPassword, encoded, "Encoded password must not equal raw plaintext");
        assertTrue(encoded.startsWith("$2a$") || encoded.startsWith("$2b$"),
                "Encoded password must follow BCrypt hash format ($2a$ or $2b$)");
    }

    @Test
    @DisplayName("matches should return true when raw password matches BCrypt hash")
    void matches_shouldReturnTrueForCorrectPassword() {
        assertNotNull(passwordEncoder, "PasswordEncoder bean is required");
        String rawPassword = "SecurePassword123!";
        String encoded = passwordEncoder.encode(rawPassword);

        assertTrue(passwordEncoder.matches(rawPassword, encoded),
                "matches must return true for matching plaintext and hash");
    }

    @Test
    @DisplayName("matches should return false when raw password does not match hash")
    void matches_shouldReturnFalseForWrongPassword() {
        assertNotNull(passwordEncoder, "PasswordEncoder bean is required");
        String rawPassword = "SecurePassword123!";
        String encoded = passwordEncoder.encode(rawPassword);

        assertFalse(passwordEncoder.matches("WrongPassword456!", encoded),
                "matches must return false for non-matching plaintext");
    }

    @Test
    @DisplayName("encode should produce different hashes for identical plaintext due to BCrypt salt")
    void encode_shouldProduceDifferentHashesDueToSalt() {
        assertNotNull(passwordEncoder, "PasswordEncoder bean is required");
        String rawPassword = "SecurePassword123!";

        String hash1 = passwordEncoder.encode(rawPassword);
        String hash2 = passwordEncoder.encode(rawPassword);

        assertNotEquals(hash1, hash2, "BCrypt must generate distinct salts for each encode call");
    }
}
