package com.dataquadinc.controller;

import com.dataquadinc.dto.LogoutResponseDto;
import com.dataquadinc.model.UserDetails;
import com.dataquadinc.service.UserLogout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/logout")
public class LogoutController {
    @Autowired
    private UserLogout userLogout;

    @PutMapping("/save/{userId}")
    public ResponseEntity<LogoutResponseDto> logoutUser(@PathVariable String userId){
        LogoutResponseDto res = userLogout.logoutUser(userId);

        System.out.println("Logout response: " + res);

        if (res.isSuccess())  {
            return new ResponseEntity<>(res, HttpStatus.OK);
        }
        return new ResponseEntity<>(res, HttpStatus.UNAUTHORIZED);
    }
}
