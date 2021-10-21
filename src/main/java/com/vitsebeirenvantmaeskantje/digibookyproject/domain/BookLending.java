package com.vitsebeirenvantmaeskantje.digibookyproject.domain;

import java.time.LocalDate;
import java.util.UUID;

public class BookLending {
    private final String id;
    private final String isbn;
    private final String memberId;
    private LocalDate returnDate;

    public BookLending(String isbn, String memberId) {
        id = UUID.randomUUID().toString();
        this.isbn = isbn;
        this.memberId = memberId;
    }
}
