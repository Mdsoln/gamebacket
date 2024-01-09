package com.gamebacket.vercel.app.exc;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class EmptyOrNullValueExceptionHandler {

    @ExceptionHandler(value = {EmptyOrNullValueStorageException.class})
    public ResponseEntity<Object> handleEmptyNullInputs(EmptyOrNullValueStorageException emptyOrNullValueStorageException){
        GlobalExceptions globalExceptions = GlobalExceptions
                .builder()
                .message(emptyOrNullValueStorageException.getMessage())
                .cause(emptyOrNullValueStorageException.getCause())
                .httpStatus(HttpStatus.BAD_REQUEST)
                .build();
        return new ResponseEntity<>(globalExceptions,HttpStatus.BAD_REQUEST);
    }
}
