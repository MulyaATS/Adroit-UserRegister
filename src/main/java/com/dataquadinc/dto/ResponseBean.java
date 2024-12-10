package com.dataquadinc.dto;

import lombok.Data;

@Data
public class ResponseBean<T> {

    private int status;
    private String message;
    private T data;

    public void setStatus(int value) {
    }

    public void setMessage(String s) {
    }

    public void setData(T res) {

    }
}