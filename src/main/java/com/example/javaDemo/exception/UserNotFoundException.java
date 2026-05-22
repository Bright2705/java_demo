package com.example.javaDemo.exception;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(
            String message
    ) {

        super(message);

    }
}
