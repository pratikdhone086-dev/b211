package com.app.handler;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.app.exceptions.CourseNotFoundException;
import com.app.exceptions.EmailAlreadyExistsException;
import com.app.exceptions.InvalidMobileException;
import com.app.exceptions.InvalidStudentException;
import com.app.exceptions.StudentNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(StudentNotFoundException.class)
    public String handleStudentNotFoundException(
            StudentNotFoundException e) {

        return e.getMessage();
    }

    @ExceptionHandler(InvalidStudentException.class)
    public String handleInvalidStudentException(
            InvalidStudentException e) {

        return e.getMessage();
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public String handleEmailAlreadyExistsException(
            EmailAlreadyExistsException e) {

        return e.getMessage();
    }

    @ExceptionHandler(CourseNotFoundException.class)
    public String handleCourseNotFoundException(
            CourseNotFoundException e) {

        return e.getMessage();
    }

    @ExceptionHandler(InvalidMobileException.class)
    public String handleInvalidMobileException(
            InvalidMobileException e) {

        return e.getMessage();
    }
}