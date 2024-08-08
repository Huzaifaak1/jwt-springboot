package com.example.jwt.jwt.exceptions;

public class InvalidCreds extends RuntimeException{
    String message;
    public InvalidCreds(String message){
        super(message);
        this.message = message;
    }

}
