package com.taskflow.security;

import com.taskflow.entity.UserEntity;
import com.taskflow.repository.UserRepository;
import com.taskflow.service.CustomUserDetailsService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

/**
 * RED Phase Unit Test for CustomUserDetailsService (Security Checkpoint 02).
 * Verifies integration between UserRepository and Spring Security UserDetailsService.
 */
@ExtendWith(MockitoExtension.class)
class CustomUserDetailsServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private CustomUserDetailsService userDetailsService;

    @Test
    @DisplayName("loadUserByUsername should return UserDetails when user exists by email")
    void loadUserByUsername_whenUserExists_shouldReturnUserDetails() {
        String email = "alice@example.com";
        String hashedPassword = "$2a$10$hashedPassword123";
        UserEntity user = new UserEntity(email, hashedPassword);
        user.setId(1L);

        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));

        UserDetails userDetails = userDetailsService.loadUserByUsername(email);

        assertNotNull(userDetails);
        assertEquals(email, userDetails.getUsername());
        assertEquals(hashedPassword, userDetails.getPassword());
    }

    @Test
    @DisplayName("loadUserByUsername should throw UsernameNotFoundException when email not found")
    void loadUserByUsername_whenUserDoesNotExist_shouldThrowUsernameNotFoundException() {
        String email = "notfound@example.com";

        when(userRepository.findByEmail(email)).thenReturn(Optional.empty());

        assertThrows(UsernameNotFoundException.class, () ->
                userDetailsService.loadUserByUsername(email)
        );
    }
}
