package com.app.exceptions;

public class InvalidStudentException extends RuntimeException {

    public InvalidStudentException(String message) {
        super(message);
    }
}