package com.dataquadinc.dto;


import lombok.*;
import java.time.LocalDateTime;
import java.util.Map;

@Data
@Getter
@Setter
@ToString
public class LogoutResponseDto {
    private boolean success;  // Indicates if the logout was successful
    private String message;   // A message describing the logout result
    private LocalDateTime logoutTimestamp;  // Timestamp of when the user logged out
    private String errors;  // To capture errors if logout fails

    // Custom constructor for success cases
    public LogoutResponseDto(boolean success, String message, LocalDateTime logoutTimestamp, String errors) {
        this.success = success;
        this.message = message;
        this.logoutTimestamp = logoutTimestamp;
        this.errors = errors;
    }

    // Setter for errors
    public void setErrors(Map<String, String> errors) {
        this.errors = errors.toString();
    }


    public void setUserId(String userId) {
    }

    public void setLastLoginTime(LocalDateTime localDateTime) {
    }

    public boolean isSuccess() {
       return success;
    }

}