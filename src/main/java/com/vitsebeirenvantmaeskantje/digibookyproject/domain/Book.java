package com.vitsebeirenvantmaeskantje.digibookyproject.domain;

public class Book {
    private final String isbn;
    private final String title;
    private String authorFirstname;
    private String authorLastname;
    private String summary;
    private boolean deleted;
    private boolean lent;

    public Book(String isbn, String title) {
        this.isbn = isbn;
        this.title = title;
    }
}
