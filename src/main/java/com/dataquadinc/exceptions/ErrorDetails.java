package com.dataquadinc.exceptions;

public class ErrorDetails {
    private String message;  // The message describing the error.
    private String details;  // Additional details, like the request URI or other context.

    // Constructor
    public ErrorDetails(String message, String details) {
        this.message = message;
        this.details = details;
    }

    // Getters and Setters
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
