package com.vitsebeirenvantmaeskantje.digibookyproject.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Test for books")
class BookTest {
    private final static String VALID_ISBN = "0205080057";

    @DisplayName("Isbn, title and author last name are required")
    @Test
    void whenInfoIsCorrectlyProvidedToRegisterANewBook_ThenCreateANewBook() {
        assertDoesNotThrow(() -> new Book(VALID_ISBN, "test", null, "bart"));
    }

    @DisplayName("Registering a new book does not work if the Isbn is invalid")
    @Test
    void whenIsbnIsInvalidWhenRegisteringANewBook_ThenThrowException() {
        assertThrows(IllegalArgumentException.class, () ->
                new Book(null, "test", null, "bart"));
        assertThrows(IllegalArgumentException.class, () ->
                new Book("", "test", null, "bart"));
        assertThrows(IllegalArgumentException.class, () ->
                new Book("test", "test", null, "bart"));
    }

    @DisplayName("Registering a new book does not work if the title is null or blank")
    @Test
    void whenTitleIsNullOrBlankWhenRegisteringANewBook_ThenThrowException() {
        assertThrows(IllegalArgumentException.class, () ->
                new Book(VALID_ISBN, null, null, "bart"));
        assertThrows(IllegalArgumentException.class, () ->
                new Book(VALID_ISBN, "", null, "bart"));
    }

    @DisplayName("Registering a new book does not work if the author last name is null or blank")
    @Test
    void whenAuthorLastNameIsNullOrBlankWhenRegisteringANewBook_ThenThrowException() {
        assertThrows(IllegalArgumentException.class, () ->
                new Book(VALID_ISBN, "test", null, null));
        assertThrows(IllegalArgumentException.class, () ->
                new Book(VALID_ISBN, "test", null, ""));
    }

}