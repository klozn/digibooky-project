package com.vitsebeirenvantmaeskantje.digibookyproject.domain;

import org.apache.commons.validator.routines.ISBNValidator;

import java.util.Objects;

public class Book {
    private String isbn;
    private String title;
    private String authorFirstname;
    private String authorLastname;
    private String summary;
    private boolean deleted;
    private boolean lent;

    public Book(String isbn, String title, String authorFirstname, String authorLastname) {
        setIsbn(isbn);
        setTitle(title);
        setAuthorFirstname(authorFirstname);
        setAuthorLastname(authorLastname);
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

    public void setIsbn(String isbn) {
        if (!ISBNValidator.getInstance().isValid(isbn)) {
            throw new IllegalArgumentException("Invalid isbn.");
        }
        this.isbn = isbn;
    }

    public void setTitle(String title) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("The title can't be null or blank.");
        }
        this.title = title;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getBookAuthorFullName(){
        return authorFirstname + " " + authorLastname;
    }

    public void setAuthorFirstname(String authorFirstname) {
        this.authorFirstname = authorFirstname;
    }

    public void setAuthorLastname(String authorLastname) {
        if (authorLastname == null || authorLastname.isBlank()) {
            throw new IllegalArgumentException("Author's lastname can't be null or blank.");
        }
        this.authorLastname = authorLastname;
    }

    public void setLent(boolean lent) {
        this.lent = lent;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
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
