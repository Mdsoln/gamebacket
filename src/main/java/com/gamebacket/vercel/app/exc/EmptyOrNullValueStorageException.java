package com.gamebacket.vercel.app.exc;

public class EmptyOrNullValueStorageException extends RuntimeException{

    public EmptyOrNullValueStorageException(String message) {
        super(message);
    }

    public EmptyOrNullValueStorageException(String message, Throwable cause) {
        super(message, cause);
    }
}
