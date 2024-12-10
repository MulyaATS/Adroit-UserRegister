package com.dataquadinc.service;

import com.dataquadinc.dto.LoginDto;
import com.dataquadinc.dto.LoginResponseDto;
import com.dataquadinc.exceptions.InvalidCredentialsException;
import com.dataquadinc.model.UserDetails;
import com.dataquadinc.repository.LoginDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserLogin {

    @Autowired
    private LoginDao loginDao;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public LoginResponseDto authenticate(LoginDto loginDto) {

        UserDetails userDetails = loginDao.findByEmail(loginDto.getEmail());

        System.out.println(userDetails);

        if (userDetails == null) {
            throw new InvalidCredentialsException("Invalid credentials");
        }
        System.out.println("User retrieved: " + userDetails);

        if (!passwordEncoder.matches(loginDto.getPassword(), userDetails.getPassword())) {
            throw new InvalidCredentialsException("Invalid credentials");
        }

        // Set login timestamp
        userDetails.setLoginTimestamp(LocalDateTime.now());
        loginDao.save(userDetails);
        LoginResponseDto.Payload payload = new LoginResponseDto.Payload(
                userDetails.getUserId(),
                userDetails.getLoginTimestamp(),
                userDetails.getRoles().toString());

        System.out.println(userDetails.getRoles());

        // Return response in the desired format with success message
        return new LoginResponseDto(true, "Login successful", payload,"null");
    }


}
