package com.app.restspringboot.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EmployeeNotFoundException.class)
    public String handleEmployeeNotFoundException(EmployeeNotFoundException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(DuplicateEmployeeException.class)
    public String handleDuplicateEmployeeException(DuplicateEmployeeException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(InvalidEmployeeException.class)
    public String handleInvalidEmployeeException(InvalidEmployeeException ex) {
        return ex.getMessage();
    }
}