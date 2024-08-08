package com.example.jwt.jwt.exceptions;

public class ResourceNotFound extends RuntimeException {
    String message;
    String status;

    public ResourceNotFound(String message, String status) {
        super(String.format("%s\n%s", message, status));
        this.message = message;
        this.status = status;
    }
}
