package com.vitsebeirenvantmaeskantje.digibookyproject.api.dto.booklendings;

public class CreateBookLendingDto {

    private final String isbn;
    private final String memberId;

    public CreateBookLendingDto(String isbn, String memberId) {
        this.isbn = isbn;
        this.memberId = memberId;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getMemberId() {
        return memberId;
    }
}
