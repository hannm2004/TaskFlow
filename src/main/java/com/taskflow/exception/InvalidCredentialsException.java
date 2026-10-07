package com.taskflow.exception;

/**
 * Thrown when login fails due to non-existing email or wrong password.
 *
 * Security Checkpoint 02: mapped to HTTP 401 Unauthorized in GlobalExceptionHandler.
 *
 * Both "email not found" and "wrong password" cases throw this same exception with
 * the same message to prevent user enumeration attacks.
 */
public class InvalidCredentialsException extends RuntimeException {

    public InvalidCredentialsException(String message) {
        super(message);
    }
}
