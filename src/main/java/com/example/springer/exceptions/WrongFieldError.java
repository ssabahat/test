package com.example.springer.exceptions;

public class WrongFieldError extends RuntimeException {
    public WrongFieldError(String message) {
        super(message);
    }
}
