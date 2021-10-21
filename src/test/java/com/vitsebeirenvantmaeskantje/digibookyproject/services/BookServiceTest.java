package com.vitsebeirenvantmaeskantje.digibookyproject.services;

import com.vitsebeirenvantmaeskantje.digibookyproject.api.dto.BookDto;
import com.vitsebeirenvantmaeskantje.digibookyproject.api.dto.mappers.BookDtoMapper;
import com.vitsebeirenvantmaeskantje.digibookyproject.domain.Book;
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

    @BeforeEach
    void setup() {
        bookService = new BookService(new BookDtoMapper(), new BookRepository());
        book1 = new BookDto("123456", "test", "ABC", "DE");
        book2 = new BookDto("222256", "test2", "Bart", "W");
    }

    @DisplayName("get booklist from bookrepo in form bookDto")
    @Test
    void whenAskingForBookListInService_ThenGetBook() {
        //WHEN
        List<BookDto> results = bookService.getAllBooks();

        //THEN
        Assertions.assertEquals(2, results.size());
        Assertions.assertEquals("123456", results.get(0).getIsbn());
    }


    @DisplayName("Get a book by ISBN")
    @Test
    void whenAskingForBookByIsbnInService_ThenGetBookDto() {
        //WHEN
        BookDto result = bookService.getByIsbn("123456");

        //THEN
        Assertions.assertEquals("ABC", result.getAuthorFirstname());
    }

    @DisplayName("Get a book using a wildcard for the ISBN")
    @Test
    void whenAskingForABookByWildcardISBN_ThenGetABookDto() {
        //WHEN
        List<BookDto> result = bookService.getBookByIsbnWildcard("123*",'*');
        List<BookDto> expected = new ArrayList<>();
        expected.add(book1);
        //THEN
        Assertions.assertEquals(expected,result);

    }


}