package com.vitsebeirenvantmaeskantje.digibookyproject.domain.exceptions;

public class UnauthorizedUserException extends RuntimeException{
    public UnauthorizedUserException() {
    }

    public UnauthorizedUserException(String message) {
        super(message);
    }

    public UnauthorizedUserException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnauthorizedUserException(Throwable cause) {
        super(cause);
    }
}
