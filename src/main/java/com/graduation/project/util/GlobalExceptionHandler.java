package com.graduation.project.util;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<Map<String, String>> restaurantDeletingException() {
        Map<String, String> errors = new HashMap<>();
        errors.put("error", "Restaurant is not found on DB");
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }


    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Map<String, String>> userPatchingException(ConstraintViolationException ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put(ex.getConstraintName(), ex.getMessage());
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<Map<String, String>> restaurantPatchingException() {
        Map<String, String> errors = new HashMap<>();
        errors.put("error", "Restaurant is not found on DB");
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
