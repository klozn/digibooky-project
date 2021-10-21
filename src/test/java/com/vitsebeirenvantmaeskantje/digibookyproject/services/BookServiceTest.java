package com.vitsebeirenvantmaeskantje.digibookyproject.services;

import com.vitsebeirenvantmaeskantje.digibookyproject.api.dto.BookDto;
import com.vitsebeirenvantmaeskantje.digibookyproject.api.dto.mappers.BookDtoMapper;
import com.vitsebeirenvantmaeskantje.digibookyproject.domain.Book;
import com.vitsebeirenvantmaeskantje.digibookyproject.repositories.BookRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

class BookServiceTest {

    @DisplayName("get booklist from bookrepo in form bookDto")
    @Test
    void whenAskingForBookListInService_ThenGetBookDtos() {
        //GIVEN
        BookService bookService = new BookService(new BookDtoMapper(), new BookRepository());

        //WHEN
        List<BookDto> results = bookService.getAllBooks();

        //THEN
        Assertions.assertEquals(2, results.size());
        Assertions.assertEquals("123456", results.get(0).getIsbn());
    }



    @DisplayName("Get a book by ISBN")
    @Test
    void whenAskingForBookByIsbnInService_ThenGetBookDto() {
        //GIVEN
        BookService bookService = new BookService(new BookDtoMapper(), new BookRepository());

        //WHEN
        BookDto result = bookService.getByIsbn("123456");

        //THEN
        Assertions.assertEquals("ABC", result.getAuthorFirstname());
    }


}