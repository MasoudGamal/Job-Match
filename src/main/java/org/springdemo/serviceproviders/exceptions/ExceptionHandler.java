package org.springdemo.serviceproviders.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandler {


    @org.springframework.web.bind.annotation.ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> handleNotFoundException(RuntimeException myRuntimeException){
        myRuntimeException.printStackTrace();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(myRuntimeException.getMessage());
    }
}
