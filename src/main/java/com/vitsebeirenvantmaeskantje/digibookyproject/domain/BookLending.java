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
        setId();
        this.isbn = isbn;
        this.memberId = memberId;
        setReturnDate();
    }

    private void setReturnDate() {
        this.returnDate = LocalDate.now().plusDays(DEFAULT_LENDING_PERIOD_IN_DAYS);
    }

    private void setId(){
        this.id = UUID.randomUUID().toString();
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
