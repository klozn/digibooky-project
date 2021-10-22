package com.vitsebeirenvantmaeskantje.digibookyproject.api.dto.books;

public class UpdateBookDto {
    private final String title;
    private final String authorFirstname;
    private final String authorLastname;
    private final String summary;

    public UpdateBookDto(String title, String authorFirstname, String authorLastname, String summary) {
        this.title = title;
        this.authorFirstname = authorFirstname;
        this.authorLastname = authorLastname;
        this.summary = summary;
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
}
