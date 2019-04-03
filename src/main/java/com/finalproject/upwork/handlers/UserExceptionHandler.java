package com.finalproject.upwork.handlers;

import com.finalproject.upwork.exception.NotFoundException;
import com.finalproject.upwork.exception.SpecialCharsException;
import com.mysql.cj.exceptions.WrongArgumentException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


import java.util.NoSuchElementException;

@RestControllerAdvice
@EnableWebMvc
public class UserExceptionHandler {

    @ExceptionHandler(WrongArgumentException.class)
    public ResponseEntity WrongArgumentExceptionHandler(WrongArgumentException exc) {

        return ResponseEntity.ok("Wrong argument");

    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity NoSuchElementExceptionHandler(NoSuchElementException exc) {

        return ResponseEntity.ok("Inexistent element");

    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity NullPointerExceptionHandler(NullPointerException exc) {

        return ResponseEntity.ok("Fields cant be null");

    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity DataIntegrityViolationExceptionHandler(DataIntegrityViolationException exc) {

        return ResponseEntity.ok("Some of the Fields Already exist");

    }

    @ExceptionHandler(SpecialCharsException.class)
    public ResponseEntity SpecialCharsExceptionHandler(SpecialCharsException exc) {

        return ResponseEntity.ok("Special chars not allowed");

    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity NotFoundExceptionHandler(NotFoundException exc) {

        return ResponseEntity.ok(exc.getMessage());

    }


}
