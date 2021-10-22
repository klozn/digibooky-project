package com.vitsebeirenvantmaeskantje.digibookyproject.repositories;

import com.vitsebeirenvantmaeskantje.digibookyproject.domain.Book;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;


class BookRepositoryTest {

    private BookRepository bookRepository;
    private Book book1;
    private Book book2;
    private Book book3;

    @BeforeEach
    void setUp() {
        bookRepository = new BookRepository();
        book1 = bookRepository.getBookByIsbn(BookRepository.ISBN_ONE);
        book2 = bookRepository.getBookByIsbn(BookRepository.ISBN_TWO);
        book3 = bookRepository.getBookByIsbn(BookRepository.ISBN_THREE);
    }

    @DisplayName("When the library has books and you ask them --> returns books")
    @Test
    void whenGettingAllBooks_ThenReceiveBooks() {
        //WHEN
        List<Book> boeken = bookRepository.getBooks();
        List<Book> results = new ArrayList<>();
        results.add(book1);
        results.add(book3);
        results.add(book2);

        //THEN
        Assertions.assertEquals(results, boeken);
    }

    @DisplayName("View details of book")
    @Test
    void whenInspectingABook_ThenReceivesDetailsOfBook() {
        //WHEN
        Book result = bookRepository.getBookByIsbn(BookRepository.ISBN_ONE);
        result.setSummary("Dit is een test");
        Book expected = book1;
        expected.setSummary("Dit is een test");

        //THEN
        Assertions.assertEquals(expected, result);
        Assertions.assertEquals(expected.getSummary(), result.getSummary());
    }
}