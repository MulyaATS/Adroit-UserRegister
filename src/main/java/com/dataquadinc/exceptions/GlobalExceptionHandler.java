package com.dataquadinc.exceptions;

import ch.qos.logback.core.model.Model;
import com.dataquadinc.dto.ErrorResponseBean;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import javax.naming.AuthenticationException;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    // Handle AuthenticationException
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<Object> handleAuthenticationException(AuthenticationException ex, WebRequest request) {
        // Custom response body with error details
        ErrorDetails errorDetails = new ErrorDetails("Invalid credentials", request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.UNAUTHORIZED); // HTTP 401
    }

    // Handle EntityNotFoundException (e.g., user not found)
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails("User not found", request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND); // HTTP 404
    }

    // Handle all other exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGeneralException(Exception ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails("An unexpected error occurred", request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR); // HTTP 500
    }

    @ExceptionHandler(InvalidUserException.class)
    public ResponseEntity<Map<String, String>> handleInvalidUserException(InvalidUserException ex) {
        Map<String, String> map = new HashMap<>();
        map.put("userId", ex.getMessage());
        return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
    }

//    @ExceptionHandler(InvalidCredentialsException.class)
//    public ResponseEntity<String> handleInvalidCredentialsException(InvalidCredentialsException ex) {
//        // Return a customized message with HTTP 401 Unauthorized status
//        return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNAUTHORIZED);
//    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<Map<String, Object>> handleInvalidCredentialsException(InvalidCredentialsException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("success", false);  // Add success as false
        response.put("message", ex.getMessage());  // Add error message to "message"
        response.put("payload", null);  // No payload in error response
        response.put("errors", Map.of("ERROR", ex.getMessage()));  // Include the error message in "errors"

        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);  // Return with 401 Unauthorized
    }

}
