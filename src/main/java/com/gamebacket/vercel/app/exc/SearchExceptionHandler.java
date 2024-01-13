package com.gamebacket.vercel.app.exc;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class SearchExceptionHandler {

    @ExceptionHandler(value = {SearchExceptions.class})
    public ResponseEntity<Object> searchHandler(
         SearchExceptions searchExceptions
    ){
        GlobalExceptions globalExceptions = new GlobalExceptions(
                searchExceptions.getMessage(),
                searchExceptions.getCause(),
                HttpStatus.NOT_FOUND
        );
        return new ResponseEntity<>(globalExceptions,HttpStatus.NOT_FOUND);
    }
}
