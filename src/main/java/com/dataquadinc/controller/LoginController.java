package com.dataquadinc.controller;
import com.dataquadinc.dto.LoginDto;
import com.dataquadinc.dto.LoginResponseDto;
import com.dataquadinc.exceptions.InvalidCredentialsException;
import com.dataquadinc.service.UserLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class LoginController {

    @Autowired
    private UserLogin userLogin;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginDto loginDto) {
        try {
            LoginResponseDto response = userLogin.authenticate(loginDto);
            return ResponseEntity.ok(response);
        } catch (InvalidCredentialsException e) {
            // Handle invalid credentials case
            LoginResponseDto response = new LoginResponseDto(
                    false,
                    "Login Failed",
                    null ,
                    "Invalid Credentials"// Set payload as null for failure case
            );

            // Add errors if necessary
            response.setErrors("Invalid credentials");

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }
}
