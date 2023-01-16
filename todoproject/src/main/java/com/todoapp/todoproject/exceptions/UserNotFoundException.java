package com.todoapp.todoproject.exceptions;

public class UserNotFoundException extends RuntimeException{
    
    public UserNotFoundException(String message) {
        super(message);
    }
}
