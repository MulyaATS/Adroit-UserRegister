package com.dataquadinc.dto;

import java.time.LocalDateTime;

public class RegisterResponseDto {

    private boolean success;
    private String message;
    private Payload payload;
    private String errors;

    // Constructor
    public RegisterResponseDto(boolean success, String message, Payload payload, String errors) {
        this.success = success;
        this.message = message;
        this.payload = payload;
        this.errors = errors;
    }

    // Getters and Setters
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Payload getPayload() {
        return payload;
    }

    public void setPayload(Payload payload) {
        this.payload = payload;
    }

    public String getErrors() {
        return errors;
    }

    public void setErrors(String errors) {
        this.errors = errors;
    }

    // Inner class for Payload
    public static class Payload {
        private String userId;
        private String userName;
        private String email;

        // Constructor
        public Payload(String userId, String userName, String email) {
            this.userId = userId;
            this.userName = userName;
            this.email = email;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        // Getters and Setters
        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }






    }
}
