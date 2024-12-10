package com.dataquadinc.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorResponseBean {
    public int status;
    public String error;
    public Map<String, String> fieldErrors;

    public static ErrorResponseBean builder() {
        return null;
    }

    public ErrorResponseBean status(int value) {
        return null;
    }

    public Object error(String validationError) {
        return null;
    }
}
