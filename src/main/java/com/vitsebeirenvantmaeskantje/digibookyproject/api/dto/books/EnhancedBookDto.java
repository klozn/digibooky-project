package com.vitsebeirenvantmaeskantje.digibookyproject.api.dto.books;

public class EnhancedBookDto {
    private final String isbn;
    private final String title;
    private final String authorFirstname;
    private final String authorLastname;
    private final String summary;
    private final boolean lent;
    private final String lenderFullName;

    public EnhancedBookDto(String isbn, String title, String authorFirstname, String authorLastname, String summary, boolean lent, String lenderFullName) {
        this.isbn = isbn;
        this.title = title;
        this.authorFirstname = authorFirstname;
        this.authorLastname = authorLastname;
        this.summary = summary;
        this.lent = lent;
        this.lenderFullName = lenderFullName;
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

    public boolean isLent() {
        return lent;
    }

    public String getLenderFullName() {
        return lenderFullName;
    }
}
