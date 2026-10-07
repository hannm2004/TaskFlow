package com.taskflow.exception;

/**
 * Thrown when attempting to register with an email address that already exists.
 *
 * Security Checkpoint 02: mapped to HTTP 409 Conflict in GlobalExceptionHandler.
 */
public class UserAlreadyExistsException extends RuntimeException {

    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
