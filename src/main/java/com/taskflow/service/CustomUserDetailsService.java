package com.taskflow.service;

import com.taskflow.entity.UserEntity;
import com.taskflow.repository.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Spring Security UserDetailsService implementation that loads users by email.
 *
 * Security Checkpoint 02:
 * - loadUserByUsername(email) looks up the user by email in the database.
 * - Returns a Spring Security User with:
 *   - username = email
 *   - password = BCrypt hash from database
 *   - no roles (empty authorities) — roles deferred to CP03+
 * - Throws UsernameNotFoundException if email is not found.
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Load user by email address (Spring Security calls this with the "username" field,
     * which in our case is the email).
     *
     * @param email the user's email address
     * @return UserDetails with email as username and BCrypt hash as password
     * @throws UsernameNotFoundException if no user exists with the given email
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

        return User.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .authorities(java.util.List.of()) // No roles in CP02; empty list is unambiguous
                .build();
    }
}
