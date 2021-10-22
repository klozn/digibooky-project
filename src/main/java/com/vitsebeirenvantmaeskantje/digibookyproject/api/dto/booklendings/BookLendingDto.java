package com.vitsebeirenvantmaeskantje.digibookyproject.api.dto.booklendings;

import java.time.LocalDate;

public class BookLendingDto {

    private final String id;
    private final String isbn;
    private final String memberId;
    private LocalDate returnDate;

    public BookLendingDto(String id, String isbn, String memberId, LocalDate returnDate) {
        this.id = id;
        this.isbn = isbn;
        this.memberId = memberId;
        this.returnDate = returnDate;
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

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }
}
