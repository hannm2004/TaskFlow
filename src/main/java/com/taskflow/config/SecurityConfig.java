package com.taskflow.config;

import com.taskflow.service.CustomUserDetailsService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Spring Security configuration for TaskFlow REST API.
 *
 * Scope (Security Checkpoint 02):
 *  - CSRF disabled (stateless REST API)
 *  - Session management: STATELESS
 *  - No form login, no HTTP Basic
 *  - Custom AuthenticationEntryPoint → 401 for unauthenticated requests
 *  - Public: /api/health, /api/auth/**, Swagger UI, OpenAPI docs
 *  - Protected: /api/tasks/** → must be authenticated
 *  - DaoAuthenticationProvider wired with CustomUserDetailsService + BCryptPasswordEncoder
 *
 * NOT yet implemented: JWT, roles, authorization.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CustomUserDetailsService customUserDetailsService;
    private final PasswordEncoder passwordEncoder;

    public SecurityConfig(CustomUserDetailsService customUserDetailsService,
                          PasswordEncoder passwordEncoder) {
        this.customUserDetailsService = customUserDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(customUserDetailsService);
        provider.setPasswordEncoder(passwordEncoder);
        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

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

            // Wire DaoAuthenticationProvider
            .authenticationProvider(authenticationProvider())

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
                // Auth endpoints are public — no token needed to register or login
                .requestMatchers("/api/auth/**").permitAll()
                // All task endpoints require authentication
                .requestMatchers("/api/tasks/**").authenticated()
                // Any other request must also be authenticated
                .anyRequest().authenticated()
            );

        return http.build();
    }
}
