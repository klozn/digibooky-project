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

    @BeforeEach
    void setUp() {
        bookRepository = new BookRepository();
        book1 = new Book("123456", "test", "ABC", "DE");
        book2 = new Book("222256", "test2", "Bart", "W");
    }

    @DisplayName("When the library has books and you ask them --> returns books")
    @Test
    void whenGettingAllBooks_ThenReceiveBooks() {
        //WHEN
        List<Book> boeken = bookRepository.getBooks();
        List<Book> results = new ArrayList<>();
        results.add(book1);
        results.add(book2);

        //THEN
        Assertions.assertEquals(results, boeken);
    }

    @DisplayName("View details of book")
    @Test
    void whenInspectingABook_ThenReceivesDetailsOfBook() {
        //WHEN
        Book result = bookRepository.getBookByIsbn("123456");
        result.setSummary("Dit is een test");
        Book expected = book1;
        expected.setSummary("Dit is een test");

        //THEN
        Assertions.assertEquals(expected, result);
        Assertions.assertEquals(expected.getSummary(), result.getSummary());
    }
}