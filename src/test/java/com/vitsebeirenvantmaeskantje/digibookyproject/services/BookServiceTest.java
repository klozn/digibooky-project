package com.vitsebeirenvantmaeskantje.digibookyproject.services;

import com.vitsebeirenvantmaeskantje.digibookyproject.api.dto.BookDto;
import com.vitsebeirenvantmaeskantje.digibookyproject.api.dto.mappers.BookDtoMapper;
import com.vitsebeirenvantmaeskantje.digibookyproject.repositories.BookRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class BookServiceTest {
    private BookService bookService;
    private BookDto book1;
    private BookDto book2;
    private BookDto book3;

    @BeforeEach
    void setup() {
        bookService = new BookService(new BookDtoMapper(), new BookRepository());
        book1 = new BookDto("123456", "de test", "Tom", "De Kock");
        book2 = new BookDto("222256", "de grote afrekening", "Bart", "Waterslaeghers");
        book3 = new BookDto("698726", "de grote afrekening - deel 2", "Bart", "Waterslaeghers");
    }

    @DisplayName("get booklist from bookrepo in form bookDto")
    @Test
    void whenAskingForBookListInService_ThenGetBook() {
        //WHEN
        List<BookDto> results = bookService.getAllBooks();

        //THEN
        Assertions.assertEquals(3, results.size());
        Assertions.assertEquals("123456", results.get(0).getIsbn());
    }


    //ISBN

    @DisplayName("Get a book by ISBN")
    @Test
    void whenAskingForBookByIsbnInService_ThenGetBookDto() {
        //WHEN
        BookDto result = bookService.getByIsbn("123456");

        //THEN
        Assertions.assertEquals("Tom", result.getAuthorFirstname());
    }

    @DisplayName("Get a book using a wildcard for the ISBN")
    @Test
    void whenAskingForABookByWildcardISBN_ThenGetABookDto() {
        //WHEN
        List<BookDto> result = bookService.getBookByIsbnWildcard("123*", '*');
        List<BookDto> expected = new ArrayList<>();
        expected.add(book1);
        //THEN
        Assertions.assertEquals(expected, result);

    }

    @DisplayName("Wildcard ISBN does not find a book")
    @Test
    void whenLookingForABookUsingAUselessWildCard_ThenReceiveNoBook() {
        //WHEN
        List<BookDto> result = bookService.getBookByIsbnWildcard("7*", '*');

        //THEN
        Assertions.assertEquals(0, result.size());
    }

    @DisplayName("Wildcard ISBN finds multiple books")
    @Test
    void whenLookingForABookUsingAWildCard_ThenReceiveMultipleBooks() {
        //WHEN
        List<BookDto> result = bookService.getBookByIsbnWildcard("****5*", '*');

        //THEN
        Assertions.assertEquals(2, result.size());
    }

    //TITLE

    @DisplayName("Get a book using a wildcard for the title")
    @Test
    void whenAskingForABookByWildcardTitle_ThenGetABookDto() {
        //WHEN
        List<BookDto> result = bookService.getBookByTitleWildcard("***test*", '*');
        List<BookDto> expected = new ArrayList<>();
        expected.add(book1);
        //THEN
        Assertions.assertEquals(expected, result);

    }

    @DisplayName("Wildcard title does not find a book")
    @Test
    void whenLookingForABookUsingAUselessTitleWildCard_ThenReceiveNoBook() {
        //WHEN
        List<BookDto> result = bookService.getBookByTitleWildcard("notfound*", '*');

        //THEN
        Assertions.assertEquals(0, result.size());
    }

    @DisplayName("Wildcard finds multiple books based on title")
    @Test
    void whenLookingForABookUsingATitleWildCard_ThenReceiveMultipleBooks() {
        //WHEN
        List<BookDto> result = bookService.getBookByTitleWildcard("de*", '*');
        System.out.println(result.toString());
        //THEN
        Assertions.assertEquals(3, result.size());
    }

    //AUTHOR

    @DisplayName("Get a book using a wildcard for the author's full name")
    @Test
    void whenAskingForABookByWildcardAuthorFullName_ThenGetABookDto() {
        //WHEN
        List<BookDto> result = bookService.getBookByAuthorWildcard("T*", '*');
        List<BookDto> expected = new ArrayList<>();
        expected.add(book1);
        //THEN
        Assertions.assertEquals(expected, result);

    }

    @DisplayName("Wildcard author's full name does not find a book")
    @Test
    void whenLookingForABookUsingAUselessAuthorFullNameWildCard_ThenReceiveNoBook() {
        //WHEN
        List<BookDto> result = bookService.getBookByAuthorWildcard("notfound*", '*');

        //THEN
        Assertions.assertEquals(0, result.size());
    }

    @DisplayName("Wildcard finds multiple books based on author's full name")
    @Test
    void whenLookingForABookUsingAuthorFullNameWildCard_ThenReceiveMultipleBooks() {
        //WHEN
        List<BookDto> result = bookService.getBookByAuthorWildcard("Bart*", '*');
        System.out.println(result.toString());
        //THEN
        Assertions.assertEquals(2, result.size());
    }
}