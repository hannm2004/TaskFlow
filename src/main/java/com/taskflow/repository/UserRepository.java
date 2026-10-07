package com.taskflow.repository;

import com.taskflow.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Spring Data JPA repository for {@link UserEntity}.
 *
 * Security Checkpoint 02:
 * - findByEmail for login lookup and CustomUserDetailsService
 * - existsByEmail for duplicate registration check
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    /**
     * Find a user by their email address.
     *
     * @param email the user's email
     * @return an Optional containing the user if found, or empty if not
     */
    Optional<UserEntity> findByEmail(String email);

    /**
     * Check whether a user with the given email exists.
     *
     * @param email the email to check
     * @return true if a user with that email exists, false otherwise
     */
    boolean existsByEmail(String email);
}
