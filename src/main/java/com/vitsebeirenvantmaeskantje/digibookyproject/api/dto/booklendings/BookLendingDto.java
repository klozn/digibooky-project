package com.vitsebeirenvantmaeskantje.digibookyproject.api.dto.booklendings;

import java.time.LocalDate;

public class BookLendingDto {

    private final String id;
    private final String isbn;
    private final String memberId;
    private final LocalDate returnDate;
    private final boolean returned;

    public BookLendingDto(String id, String isbn, String memberId, LocalDate returnDate, boolean returned) {
        this.id = id;
        this.isbn = isbn;
        this.memberId = memberId;
        this.returnDate = returnDate;
        this.returned = returned;
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

    public boolean isReturned(){return returned;}

}
