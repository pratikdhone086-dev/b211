package com.app.restspringboot.exception;

public class InvalidEmployeeException extends RuntimeException {

    public InvalidEmployeeException(String message) {
        super(message);
    }
}