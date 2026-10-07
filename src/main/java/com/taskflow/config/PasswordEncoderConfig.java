package com.taskflow.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Security beans configuration for TaskFlow.
 *
 * Security Checkpoint 02:
 * - Registers BCryptPasswordEncoder as the PasswordEncoder bean.
 * - No JWT, no AuthenticationManager, no UserDetailsService bean here (wired in SecurityConfig).
 */
@Configuration
public class PasswordEncoderConfig {

    /**
     * BCrypt password encoder bean used for hashing and verifying user passwords.
     * BCrypt automatically handles salting — each encode() call produces a unique hash.
     *
     * @return BCryptPasswordEncoder instance
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
