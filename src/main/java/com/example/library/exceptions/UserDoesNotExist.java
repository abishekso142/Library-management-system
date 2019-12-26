package com.example.library.exceptions;

public class UserDoesNotExist extends RuntimeException
{
    private static final String message = "The user does not exist anymore, please recheck";
    public UserDoesNotExist() {
        super(message);
    }

}
