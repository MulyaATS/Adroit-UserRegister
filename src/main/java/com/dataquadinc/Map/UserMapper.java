package com.dataquadinc.Map;

import com.dataquadinc.dto.UserDto;
import com.dataquadinc.exceptions.ValidationException;
import com.dataquadinc.model.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

     // You will need to create this DAO

    public UserDetails toEntity(UserDto userDto) throws ValidationException {
        UserDetails user = new UserDetails();
        user.setUserId(userDto.getUserId());
        user.setUserName(userDto.getUserName());
        user.setPassword(userDto.getPassword());// Password should be encrypted later
        user.setConfirmPassword(userDto.getConfirmPassword());
        user.setEmail(userDto.getEmail());
        user.setPersonalemail(userDto.getPersonalemail());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setDesignation(userDto.getDesignation());
        user.setRoles(userDto.getRoles());
        user.setGender(userDto.getGender());
        user.setDateOfBirth(userDto.getDateOfBirth());
        user.setDateOfJoining(userDto.getDateOfJoining());


        return user;
    }
}
