package com.taskflow.dto;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * RED Phase Test for Auth DTO Validations (Security Checkpoint 02).
 * Verifies Bean Validation rules for RegisterRequest and LoginRequest.
 */
class AuthValidationTest {

    private static Validator validator;

    @BeforeAll
    static void setUpValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    @DisplayName("RegisterRequest with valid email and password should have no validation errors")
    void registerRequest_withValidData_shouldPassValidation() {
        RegisterRequest request = new RegisterRequest("user@example.com", "Password123!");

        Set<ConstraintViolation<RegisterRequest>> violations = validator.validate(request);

        assertTrue(violations.isEmpty(), "Valid RegisterRequest should have no violations");
    }

    @Test
    @DisplayName("RegisterRequest with blank or null email should fail validation")
    void registerRequest_withBlankEmail_shouldFailValidation() {
        RegisterRequest request = new RegisterRequest("", "Password123!");

        Set<ConstraintViolation<RegisterRequest>> violations = validator.validate(request);

        assertFalse(violations.isEmpty(), "Blank email must fail validation");
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("email")));
    }

    @Test
    @DisplayName("RegisterRequest with invalid email format should fail validation")
    void registerRequest_withInvalidEmailFormat_shouldFailValidation() {
        RegisterRequest request = new RegisterRequest("not-a-valid-email", "Password123!");

        Set<ConstraintViolation<RegisterRequest>> violations = validator.validate(request);

        assertFalse(violations.isEmpty(), "Malformed email must fail validation");
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("email")));
    }

    @Test
    @DisplayName("RegisterRequest with blank password should fail validation")
    void registerRequest_withBlankPassword_shouldFailValidation() {
        RegisterRequest request = new RegisterRequest("user@example.com", "");

        Set<ConstraintViolation<RegisterRequest>> violations = validator.validate(request);

        assertFalse(violations.isEmpty(), "Blank password must fail validation");
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("password")));
    }

    @Test
    @DisplayName("RegisterRequest with password shorter than minimum size should fail validation")
    void registerRequest_withShortPassword_shouldFailValidation() {
        RegisterRequest request = new RegisterRequest("user@example.com", "12345");

        Set<ConstraintViolation<RegisterRequest>> violations = validator.validate(request);

        assertFalse(violations.isEmpty(), "Password shorter than 6 characters must fail validation");
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("password")));
    }

    @Test
    @DisplayName("LoginRequest with valid email and password should pass validation")
    void loginRequest_withValidData_shouldPassValidation() {
        LoginRequest request = new LoginRequest("user@example.com", "Password123!");

        Set<ConstraintViolation<LoginRequest>> violations = validator.validate(request);

        assertTrue(violations.isEmpty(), "Valid LoginRequest should have no violations");
    }

    @Test
    @DisplayName("LoginRequest with blank email should fail validation")
    void loginRequest_withBlankEmail_shouldFailValidation() {
        LoginRequest request = new LoginRequest("", "Password123!");

        Set<ConstraintViolation<LoginRequest>> violations = validator.validate(request);

        assertFalse(violations.isEmpty(), "Blank email in LoginRequest must fail validation");
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("email")));
    }

    @Test
    @DisplayName("LoginRequest with blank password should fail validation")
    void loginRequest_withBlankPassword_shouldFailValidation() {
        LoginRequest request = new LoginRequest("user@example.com", "");

        Set<ConstraintViolation<LoginRequest>> violations = validator.validate(request);

        assertFalse(violations.isEmpty(), "Blank password in LoginRequest must fail validation");
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("password")));
    }
}
