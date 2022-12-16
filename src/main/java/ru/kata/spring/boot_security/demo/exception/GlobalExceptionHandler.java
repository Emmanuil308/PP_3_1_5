package ru.kata.spring.boot_security.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<BodyForMyException> catchMyNotFoundException(MyNotFoundException e){
        e.getStackTrace();
        return new ResponseEntity<>(new BodyForMyException(e.getMessage(), HttpStatus.NOT_FOUND.value())
                ,HttpStatus.NOT_FOUND);
    }

//    @ExceptionHandler
//    public ResponseEntity<BodyForMyException> catchMyNotRoleException(MyNotRoleException e){
//        e.getStackTrace();
//        return new ResponseEntity<>(new BodyForMyException(e.getMessage(), HttpStatus.BAD_REQUEST.value())
//                ,HttpStatus.BAD_REQUEST);
//    }
}
