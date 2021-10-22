package com.vitsebeirenvantmaeskantje.digibookyproject.domain;

import java.time.LocalDate;
import java.util.UUID;

public class BookLending {

    private static final int DEFAULT_LENDING_PERIOD_IN_DAYS = 21;

    private String id;
    private final String isbn;
    private final String memberId;
    private LocalDate returnDate;

    public BookLending(String isbn, String memberId) {
        id = UUID.randomUUID().toString();
        this.isbn = isbn;
        this.memberId = memberId;
        setReturnDate();
    }

    public void setReturnDate() {
        this.returnDate = LocalDate.now().plusDays(DEFAULT_LENDING_PERIOD_IN_DAYS);
    }

    public String getId() {
        return id;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getMemberId() {
        return memberId;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }


}
