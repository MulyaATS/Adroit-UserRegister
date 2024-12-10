package com.dataquadinc.dto;

import lombok.Data;

@Data
public class UserResponse {
    private String userId;
    private String userName;
    private String EmailId;

    public void setUserName(String userName) {
    }

    public void setUserId(String userId) {
    }

    public void setEmail(String email) {

    }
}