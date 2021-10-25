package com.vitsebeirenvantmaeskantje.digibookyproject.domain.exceptions;

public class BookIsDeletedException extends RuntimeException {

    public BookIsDeletedException(String message) {
        super(message);
    }
}
