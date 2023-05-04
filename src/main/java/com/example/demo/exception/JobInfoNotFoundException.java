package com.example.demo.exception;

public class JobInfoNotFoundException extends RuntimeException {
    public JobInfoNotFoundException(String message) {
        super(message);
    }
}