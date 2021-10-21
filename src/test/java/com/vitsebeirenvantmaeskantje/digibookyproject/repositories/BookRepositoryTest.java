package com.vitsebeirenvantmaeskantje.digibookyproject.repositories;

import com.vitsebeirenvantmaeskantje.digibookyproject.domain.Book;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;


class BookRepositoryTest {

    private BookRepository bib;
    private Book book1;
    private Book book2;

    @BeforeEach
    void setUp() {
        bib = new BookRepository();
        book1 = new Book("123456", "test", "ABC", "DE");
        book2 = new Book("222256", "test2", "Bart", "W");
    }

    @DisplayName("When the library has books and you ask them --> returns books")
    @Test
    void whenGettingAllBooks_ThenReceiveBooks() {
        //WHEN
        List<Book> boeken = bib.getBooks();
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
        Book result = bib.getBookByIsbn("123456");
        result.setSummary("Dit is een test");
        Book expected = book1;
        expected.setSummary("Dit is een test");

        //THEN
        Assertions.assertEquals(expected, result);
        Assertions.assertEquals(expected.getSummary(), result.getSummary());
    }

    @DisplayName("use wildcard to find a book")
    @Test
    void whenLookingForABookUsingAWildCard_ThenReceiveABook() {
        //WHEN
        List<Book> result = bib.getBookByIsbnWildcard("123*", '*');
        List<Book> expected = new ArrayList<>();
        expected.add(bib.getBookByIsbn("123456"));

        //THEN
        Assertions.assertEquals(expected, result);
    }

    @DisplayName("Wildcard does not find a book")
    @Test
    void whenLookingForABookUsingAUselessWildCard_ThenReceiveNoBook() {
        //WHEN
        List<Book> result = bib.getBookByIsbnWildcard("7*", '*');

        //THEN
        Assertions.assertEquals(0, result.size());
    }

    @DisplayName("Wildcard finds multiple books")
    @Test
    void whenLookingForABookUsingAWildCard_ThenReceiveMultipleBooks() {
        //WHEN
        List<Book> result = bib.getBookByIsbnWildcard("*2*", '*');

        //THEN
        Assertions.assertEquals(2, result.size());
    }
}