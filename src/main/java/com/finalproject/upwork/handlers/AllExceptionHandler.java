package com.finalproject.upwork.handlers;

import com.finalproject.upwork.exception.*;
import com.mysql.cj.exceptions.WrongArgumentException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@RestControllerAdvice
@EnableWebMvc
public class AllExceptionHandler {

    @ExceptionHandler(WrongArgumentException.class)
    public ResponseEntity WrongArgumentExceptionHandler(WrongArgumentException exc) {

        return ResponseEntity.ok("Wrong argument");

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

        return ResponseEntity.ok(exc.getMessage());

    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity NotFoundExceptionHandler(NotFoundException exc) {

        return ResponseEntity.ok(exc.getMessage());

    }

    @ExceptionHandler(NotUrlException.class)
    public ResponseEntity NotUrlExceptionHandler(NotUrlException exc) {

        return ResponseEntity.ok(exc.getMessage());

    }

    @ExceptionHandler(SizeException.class)
    public ResponseEntity SizeHandler(SizeException exc) {

        return ResponseEntity.ok(exc.getMessage());

    }

    @ExceptionHandler(WrongEmailException.class)
    public ResponseEntity EmailHandler(WrongEmailException exc) {

        return ResponseEntity.ok(exc.getMessage());

    }

    @ExceptionHandler(CantSubmitException.class)
    public ResponseEntity SubmitHandler(CantSubmitException exc) {

        return ResponseEntity.ok("You cant submit to the task that you posted ");

    }

}
