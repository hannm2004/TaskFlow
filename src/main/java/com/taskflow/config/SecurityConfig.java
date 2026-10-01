package com.taskflow.config;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Baseline Spring Security configuration for TaskFlow REST API.
 *
 * Scope (Security Checkpoint 01):
 *  - CSRF disabled (stateless REST API)
 *  - Session management: STATELESS
 *  - No form login, no HTTP Basic
 *  - Custom AuthenticationEntryPoint → 401 for unauthenticated requests
 *  - Public: /api/health, Swagger UI, OpenAPI docs
 *  - Protected: /api/tasks/** → must be authenticated
 *
 * NOT yet implemented: JWT, UserEntity, roles, registration/login.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // Disable CSRF — REST API uses stateless tokens, not cookies
            .csrf(AbstractHttpConfigurer::disable)

            // Stateless session — no HttpSession created or used
            .sessionManagement(session ->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )

            // No form-based login
            .formLogin(AbstractHttpConfigurer::disable)

            // No HTTP Basic authentication
            .httpBasic(AbstractHttpConfigurer::disable)

            // Return 401 (not 403) for unauthenticated requests to protected endpoints.
            // Without an explicit AuthenticationEntryPoint, Spring Security defaults to
            // 403 when httpBasic/formLogin are both disabled.
            .exceptionHandling(ex -> ex
                .authenticationEntryPoint((request, response, authException) ->
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized")
                )
            )

            // Authorization rules
            .authorizeHttpRequests(auth -> auth
                // Public endpoints
                .requestMatchers("/api/health").permitAll()
                .requestMatchers("/v3/api-docs/**").permitAll()
                .requestMatchers("/swagger-ui/**").permitAll()
                .requestMatchers("/swagger-ui.html").permitAll()
                // All task endpoints require authentication
                .requestMatchers("/api/tasks/**").authenticated()
                // Any other request must also be authenticated
                .anyRequest().authenticated()
            );

        return http.build();
    }
}
