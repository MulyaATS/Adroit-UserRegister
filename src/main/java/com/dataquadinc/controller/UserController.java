package com.dataquadinc.controller;

import com.dataquadinc.Map.UserMapper;
import com.dataquadinc.dto.RegisterResponseDto;
import com.dataquadinc.dto.ResponseBean;
import com.dataquadinc.dto.UserDto;
import com.dataquadinc.dto.UserResponse;
import com.dataquadinc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.management.relation.RoleNotFoundException;
import java.time.LocalDateTime;

@RestController
public class UserController {


    private final UserService userService;
    private final UserMapper userMapper;

    @Autowired
    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponseDto> registerUser(@RequestBody UserDto userDto) throws RoleNotFoundException {
        if (userDto.getUserId() == null || userDto.getUserId().isEmpty()) {
            RegisterResponseDto response = new RegisterResponseDto(
                    false,
                    "Registration Failed",
                    null,
                    "UserId cannot be null or empty"
            );
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        // Validate Password
        if (userDto.getPassword() == null || userDto.getPassword().isEmpty()) {
            RegisterResponseDto response = new RegisterResponseDto(
                    false,
                    "Registration Failed",
                    null,
                    "Password cannot be null or empty"
            );
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        // Validate Confirm Password
        if (userDto.getConfirmPassword() == null || userDto.getConfirmPassword().isEmpty()) {
            RegisterResponseDto response = new RegisterResponseDto(
                    false,
                    "Registration Failed",
                    null,
                    "Confirm Password cannot be null or empty"
            );
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        // Check if passwords match
        if (!userDto.getPassword().equals(userDto.getConfirmPassword())) {
            RegisterResponseDto response = new RegisterResponseDto(
                    false,
                    "Registration Failed",
                    null,
                    "Password and Confirm Password must match"
            );
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        // Validate Email
        if (userDto.getEmail() == null || userDto.getEmail().isEmpty()) {
            RegisterResponseDto response = new RegisterResponseDto(
                    false,
                    "Registration Failed",
                    null,
                    "Email cannot be null or empty"
            );
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        // Validate Email Format
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        if (!userDto.getEmail().matches(emailRegex)) {
            RegisterResponseDto response = new RegisterResponseDto(
                    false,
                    "Registration Failed",
                    null,
                    "Invalid email format"
            );
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        // Check if email already exists
        boolean emailExists = userService.emailExists(userDto.getEmail());
        if (emailExists) {
            RegisterResponseDto response = new RegisterResponseDto(
                    false,
                    "Registration Failed",
                    null,
                    "Email is already in use"
            );
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        // Check if username already exists
        boolean userNameExists = userService.userNameExists(userDto.getUserName());
        if (userNameExists) {
            RegisterResponseDto response = new RegisterResponseDto(
                    false,
                    "Registration Failed",
                    null,
                    "Username is already in use"
            );
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        // Proceed with Registration
        try {
            // Register user and get user details
            ResponseEntity<ResponseBean<UserResponse>> userDetails = userService.registerUser(userDto);

            // Check if userDetails is null (if registration fails)
            if (userDetails == null) {
                RegisterResponseDto response = new RegisterResponseDto(
                        false,
                        "Registration Failed",
                        null,
                        "Invalid Credentials"
                );
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }

            // Prepare Payload data
            String userId = userDto.getUserId(); // This would be fetched from the registered user
            String userName = userDto.getUserName(); // Assuming userDetails contains userName
            String email = userDto.getEmail();

            // Create Payload and Response DTO

            RegisterResponseDto.Payload payload = new RegisterResponseDto.Payload(
                    userId,
                    userName,
                    email
            );

            // Create the final response with success
            RegisterResponseDto response = new RegisterResponseDto(
                    true,
                    "Registration successful",
                    payload,
                    null // No errors during registration
            );

            return ResponseEntity.status(HttpStatus.CREATED).body(response);

        } catch (Exception e) {
            // If any exception occurs, respond with a failure message
            RegisterResponseDto response = new RegisterResponseDto(
                    false,
                    "Registration Failed",
                    null,
                    e.getMessage() // Provide the exception message if any
            );
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }



    }
}
