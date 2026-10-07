package com.taskflow.repository;

import com.taskflow.entity.UserEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * RED Phase Test for UserRepository (Security Checkpoint 02).
 * Verifies persistence, email lookup, existence checks, and unique email constraint.
 */
@SpringBootTest
@Transactional
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
    }

    @Test
    @DisplayName("Should persist user and generate primary key id")
    void save_shouldPersistUserAndGenerateId() {
        UserEntity user = new UserEntity("alice@example.com", "$2a$10$encodedPasswordHash123");

        UserEntity saved = userRepository.save(user);

        assertNotNull(saved.getId(), "User id should be generated upon save");
        assertEquals("alice@example.com", saved.getEmail());
        assertEquals("$2a$10$encodedPasswordHash123", saved.getPassword());
    }

    @Test
    @DisplayName("Should find user by email when user exists")
    void findByEmail_whenUserExists_shouldReturnUser() {
        UserEntity user = new UserEntity("bob@example.com", "$2a$10$encodedPasswordHash123");
        userRepository.save(user);

        Optional<UserEntity> found = userRepository.findByEmail("bob@example.com");

        assertTrue(found.isPresent(), "User should be found by existing email");
        assertEquals("bob@example.com", found.get().getEmail());
    }

    @Test
    @DisplayName("Should return empty Optional when searching for non-existing email")
    void findByEmail_whenUserDoesNotExist_shouldReturnEmpty() {
        Optional<UserEntity> found = userRepository.findByEmail("nonexisting@example.com");

        assertFalse(found.isPresent(), "Non-existing email should return empty Optional");
    }

    @Test
    @DisplayName("Should return true when email exists in database")
    void existsByEmail_whenUserExists_shouldReturnTrue() {
        UserEntity user = new UserEntity("charlie@example.com", "$2a$10$encodedPasswordHash123");
        userRepository.save(user);

        boolean exists = userRepository.existsByEmail("charlie@example.com");

        assertTrue(exists, "existsByEmail should return true for registered email");
    }

    @Test
    @DisplayName("Should return false when email does not exist in database")
    void existsByEmail_whenUserDoesNotExist_shouldReturnFalse() {
        boolean exists = userRepository.existsByEmail("notfound@example.com");

        assertFalse(exists, "existsByEmail should return false for unregistered email");
    }

    @Test
    @DisplayName("Should throw DataIntegrityViolationException when saving duplicate email due to unique constraint")
    void save_withDuplicateEmail_shouldThrowDataIntegrityViolationException() {
        UserEntity user1 = new UserEntity("duplicate@example.com", "$2a$10$hash1");
        userRepository.saveAndFlush(user1);

        UserEntity user2 = new UserEntity("duplicate@example.com", "$2a$10$hash2");

        assertThrows(DataIntegrityViolationException.class, () -> {
            userRepository.saveAndFlush(user2);
        }, "Saving user with duplicate email must trigger unique constraint violation");
    }
}
