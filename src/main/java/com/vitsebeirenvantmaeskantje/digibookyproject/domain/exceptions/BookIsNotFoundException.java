package com.vitsebeirenvantmaeskantje.digibookyproject.domain.exceptions;

public class BookIsNotFoundException extends RuntimeException{

    public BookIsNotFoundException(String message) {
        super(message);
    }
}
