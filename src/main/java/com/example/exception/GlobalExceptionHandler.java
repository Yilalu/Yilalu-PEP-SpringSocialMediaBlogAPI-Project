package com.example.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Global exception handler for the entire application.
 * This class intercepts exceptions thrown from any controller
 * and returns consistent response.
*/
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles duplicate username errors.
     * Returns HTTP 409 Conflict.
    */
    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<String> handleDuplicateUsername(IllegalStateException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    }
    /**
     * Handles bad request scenarios.
     * Returns HTTP 400 Bad Request.
    */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleBadRequest(IllegalArgumentException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
    /**
     * Handles authentication or credential-related errors.
     * Returns HTTP 401 Unauthorized.
    */
    @ExceptionHandler(SecurityException.class)
    public ResponseEntity<String> handleUnauthorized(SecurityException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
    }

    /**
     *Catches any unhandled exceptions to avoid exposing stack traces.
     * Returns HTTP 500 Internal Server Error.
    */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneralError(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("An unexpected error occurred: " + e.getMessage());
    }
}

