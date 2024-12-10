package com.dataquadinc.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ValidationException extends RuntimeException{
    private final String errors;

    public ValidationException (
            String errors
    ) {
        super("Validation Failed");
        this.errors = errors;
    }

    public String getErrors() {
        return errors;
    }
}