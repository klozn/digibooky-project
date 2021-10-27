package com.vitsebeirenvantmaeskantje.digibookyproject.repositories;

import com.vitsebeirenvantmaeskantje.digibookyproject.domain.Book;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


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
        List<Book> books = bookRepository.getBooks();

        //THEN
        assertThat(books).containsExactlyInAnyOrder(book1, book2, book3);
    }

    @DisplayName("View details of book")
    @Test
    void whenInspectingABook_ThenReceivesDetailsOfBook() {
        // CODEReview this test relies on your default list of books
        // a typical test would construct a new book instance, save it in the repository,
        // and then check whether a retrieval by its isbn returns the correct Book instance
        // so:
        // * given step: construct a book instance and save it in the repository
        // * when step: retrieve a book using the isbn you used in the given step
        // * then step: verify whether hte retrieved book instance from the when step equals the constructed instance from the given step

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