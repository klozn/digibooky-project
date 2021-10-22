package com.vitsebeirenvantmaeskantje.digibookyproject.domain;

import java.util.Objects;

public class Book {
    private final String isbn;
    private final String title;
    private String authorFirstname;
    private String authorLastname;
    private String summary;
    private boolean deleted;
    private boolean lent;

    public Book(String isbn, String title, String authorFirstname, String authorLastname) {
        this.isbn = isbn;
        this.title = title;
        this.authorFirstname = authorFirstname;
        this.authorLastname = authorLastname;
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

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getBookAuthorFullName(){
        return authorFirstname + " " + authorLastname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(isbn, book.isbn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isbn);
    }
}
