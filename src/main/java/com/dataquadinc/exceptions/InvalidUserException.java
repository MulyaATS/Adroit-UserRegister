package com.dataquadinc.exceptions;

public class InvalidUserException extends RuntimeException {
        public InvalidUserException(){
            super();
        }
        public InvalidUserException(String message){
            super(message);
        }
}
