package com.dataquadinc.service;

import com.dataquadinc.dto.LoginResponseDto;
import com.dataquadinc.dto.LogoutResponseDto;
import com.dataquadinc.exceptions.InvalidUserException;
import com.dataquadinc.model.UserDetails;
import com.dataquadinc.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;

@Service
public class UserLogout {
    @Autowired
    private UserDao userDao;

    public LogoutResponseDto logoutUser(String userId) {
        UserDetails userDetails = userDao.findByUserId(userId);


        System.out.println("User details for userId " + userId + ": " + userDetails);

        // Check if the user exists
        if (userDetails == null) {
            // Return error response if user does not exist
            LocalDateTime timestamp = LocalDateTime.now();
            return new LogoutResponseDto(
                    false, // success = false for failure
                    "Logout Failed", // message indicating failure
                    timestamp, // include timestamp for the failure
                    "Invalid Credentials" // error details
            );
        }

        // Check if the user details (email or password) are null
        if (userDetails.getEmail() == null && userDetails.getPassword() == null) {
            LocalDateTime ts = LocalDateTime.now();
            // Return error response if email or password is null
            return new LogoutResponseDto(
                    false, // success = false for failure
                    "Logout Failed", // message indicating failure
                    ts, // include timestamp for the failure
                    "Invalid Credentials" // error details
            );
        }


            System.out.println(userId);

            LocalDateTime timestamp = LocalDateTime.now();
            userDetails.setLastLoginTime(timestamp);
            userDao.save(userDetails);

            System.out.println(userDetails);

        // Return success response


        //        res.setUserId(userId);
//        res.setLastLoginTime(timestamp);
//        res.setErrors();
        return new LogoutResponseDto(true, "Logout successful", LocalDateTime.now(), "null");
    }



}
