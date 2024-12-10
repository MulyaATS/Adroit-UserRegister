package com.dataquadinc.service;

import com.dataquadinc.Map.UserMapper;
import com.dataquadinc.dto.RegisterResponseDto;
import com.dataquadinc.dto.UserDto;
import com.dataquadinc.dto.UserResponse;
import com.dataquadinc.exceptions.DuplicateEmailException;
import com.dataquadinc.exceptions.ValidationException;
import com.dataquadinc.dto.ResponseBean;
import com.dataquadinc.model.UserDetails;
import com.dataquadinc.repository.LoginDao;
import com.dataquadinc.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserMapper userMapper;



    @Autowired
    private LoginDao loginDao;

    public String registerUser(UserDetails userDetails) {

        if (loginDao.findByEmail(userDetails.getEmail()) != null) {
            throw new DuplicateEmailException("Email is already in use");
        }

        userDetails.setLoginTimestamp(LocalDateTime.now());

        userDetails.setPassword(passwordEncoder.encode(userDetails.getPassword()));
        loginDao.save(userDetails);

        return "Registration successful";
    }


    public ResponseEntity<ResponseBean<UserResponse>> registerUser(UserDto userDto) throws ValidationException {


        Map<String,String> errors = new HashMap<>();


        if (userDao.findByUserName(userDto.getUserName()) != null) {

            errors.put("userName","userName already exists");
        }

        if (userDao.findByEmail(userDto.getEmail())!=null) {
            errors.put("email","email is already in use");
        }
        if (userDao.findByUserId(userDto.getUserId())!=null) {
            errors.put("userId","userId already exists");
        }

        if( !errors.isEmpty()) {
            throw new ValidationException(errors.toString());
        }

        System.out.println(userDto);

        // Encrypt the password
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userDto.setConfirmPassword(passwordEncoder.encode(userDto.getConfirmPassword()));

        // Convert DTO to entity
        UserDetails user = userMapper.toEntity(userDto);
    System.out.println(user);
        // Save the user to the database
        UserDetails dbUser = userDao.save(user);
        ResponseBean<UserResponse> resp = new ResponseBean<UserResponse>();
        UserResponse res=new UserResponse();
        res.setUserName(dbUser.getUserName());
        res.setUserId(dbUser.getUserId());
        res.setEmail(dbUser.getEmail());

        resp.setStatus(HttpStatus.CREATED.value());
        resp.setMessage(dbUser.getRoles()+" Created Sucessfully");
        resp.setData(res);

        return new ResponseEntity<ResponseBean<UserResponse>>(resp,HttpStatus.CREATED);



    }

    public boolean emailExists(String email) {
        return userDao.existsByEmail(email);  // Assuming 'existsByEmail' is defined in the repository
    }

    public boolean userNameExists(String userName) {
        return userDao.existsByUserName(userName);  // Check if username exists in the repository
    }

    public ResponseEntity<String> getRolesByUserId(String UserId ) {
        UserDetails user = userDao.findByUserId(UserId);
        String roles = user.getRoles();
        return  ResponseEntity.ok(roles);



    }


    public RegisterResponseDto authenticate() {
        return null;
    }
}
