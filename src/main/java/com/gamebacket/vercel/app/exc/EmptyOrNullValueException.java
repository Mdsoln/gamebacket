package com.gamebacket.vercel.app.exc;

public class EmptyOrNullValueException extends RuntimeException{

    public EmptyOrNullValueException(String message) {
        super(message);
    }

    public EmptyOrNullValueException(String message, Throwable cause) {
        super(message, cause);
    }
}
