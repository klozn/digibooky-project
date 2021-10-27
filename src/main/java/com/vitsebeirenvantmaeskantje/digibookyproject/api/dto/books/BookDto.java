package com.vitsebeirenvantmaeskantje.digibookyproject.api.dto.books;

import java.util.Objects;

public class BookDto {
    private final String isbn;
    private final String title;
    private String authorFirstname; // FIXME The DTO has non-final fields
    private String authorLastname;
    private String summary;
    private boolean deleted;
    private boolean lent;

    
    // FIXME This constructor is not needed
    public BookDto(String isbn, String title, String authorFirstname, String authorLastname) {
        this.isbn = isbn;
        this.title = title;
        this.authorFirstname = authorFirstname;
        this.authorLastname = authorLastname;
    }

    // FIXME call the this() constructor
    public BookDto(String isbn, String title, String authorFirstname, String authorLastname, String summary) {
        this.isbn = isbn;
        this.title = title;
        this.authorFirstname = authorFirstname;
        this.authorLastname = authorLastname;
        this.summary = summary;
    }

    public BookDto(String isbn, String title, String authorFirstname, String authorLastname, boolean deleted, boolean lent, String summary) {
        this.isbn = isbn;
        this.title = title;
        this.authorFirstname = authorFirstname;
        this.authorLastname = authorLastname;
        this.lent = lent;
        this.deleted = deleted;
        this.summary = summary;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthorFirstname() {
        return authorFirstname;
    }

    public String getAuthorLastname() {
        return authorLastname;
    }

    public String getSummary() {
        return summary;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public boolean isLent() {
        return lent;
    }

    public String getBookAuthorFullName(){
        return authorFirstname + " " + authorLastname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookDto bookDto = (BookDto) o;
        return Objects.equals(isbn, bookDto.isbn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isbn);
    }

    @Override
    public String toString() {
        return "BookDto{" +
                "isbn='" + isbn + '\'' +
                ", title='" + title + '\'' +
                ", authorFirstname='" + authorFirstname + '\'' +
                ", authorLastname='" + authorLastname + '\'' +
                ", summary='" + summary + '\'' +
                ", deleted=" + deleted +
                ", lent=" + lent +
                '}';
    }
}
