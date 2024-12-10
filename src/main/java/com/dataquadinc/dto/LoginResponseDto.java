package com.dataquadinc.dto;
import lombok.*;

import java.time.LocalDateTime;

@Data

@Getter
@Setter
@ToString
public class LoginResponseDto {
    private boolean success;
    private String message;
    private Payload payload;
    private String errors;  // Added errors map for failure cases

    @Data
    public static class Payload {
        private String userId;
        private String roles;
        private LocalDateTime loginTimestamp;


        public Payload(String userId, Object loginTimestamp, String roles) {
            this.userId = userId;
            this.loginTimestamp = (LocalDateTime) loginTimestamp;
            this.roles = roles;
        }
    }

    // Custom constructor to include message and errors
    public LoginResponseDto(boolean success, String message, Payload payload, String errors ){
        this.success = success;
        this.message = message;
        this.payload = payload;
        this.errors = errors;
    }

    // Setter for errors
    public void setErrors(String errors) {
        this.errors = errors;
    }
}
