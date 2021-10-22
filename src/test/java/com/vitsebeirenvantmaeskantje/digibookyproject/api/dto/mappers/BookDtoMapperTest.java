package com.vitsebeirenvantmaeskantje.digibookyproject.api.dto.mappers;

import com.vitsebeirenvantmaeskantje.digibookyproject.api.dto.books.BookDto;
import com.vitsebeirenvantmaeskantje.digibookyproject.domain.Book;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class BookDtoMapperTest {
    private final static String VALID_ISBN = "0205080057";

    @DisplayName("BookDto to Book")
    @Test
    void whenBookDtoIsConvertedToBook_ThenReceivesBook() {
        //GIVEN
        BookDto bookDto = new BookDto(VALID_ISBN, "test", "test", "test");
        BookDtoMapper mapper = new BookDtoMapper();
        Book expected = new Book(VALID_ISBN, "test", "test", "test");

        //WHEN
        Book result = mapper.toEntity(bookDto);

        //THEN
        Assertions.assertEquals(expected, result);
    }

    @DisplayName("Book to BookDto")
    @Test
    void whenBookIsConvertedToBook_ThenReceivesBook() {
        //GIVEN
        Book book = new Book(VALID_ISBN, "test", "test", "test");
        BookDtoMapper mapper = new BookDtoMapper();
        BookDto expected = new BookDto(VALID_ISBN, "test", "test", "test");

        //WHEN
        BookDto result = mapper.toDto(book);


        //THEN
        Assertions.assertEquals(expected, result);
    }

    @DisplayName("List BookDto to List Book")
    @Test
    void whenListBookDtoIsConvertedToBook_ThenReceivesListBook() {
        //GIVEN
        BookDto bookDto = new BookDto(VALID_ISBN, "test", "test", "test");
        List<BookDto> bookDtos = new ArrayList<>();
        bookDtos.add(bookDto);

        BookDtoMapper mapper = new BookDtoMapper();

        Book book = new Book(VALID_ISBN, "test", "test", "test");
        List<Book> expected = new ArrayList<>();
        expected.add(book);

        //WHEN
        List<Book> result = mapper.toEntity(bookDtos);


        //THEN
        Assertions.assertEquals(expected, result);
    }

    @DisplayName("List Book to List BookDto")
    @Test
    void whenListBookIsConvertedToBook_ThenReceivesListBookDto() {
        //GIVEN
        Book book = new Book(VALID_ISBN, "test", "test", "test");
        List<Book> books = new ArrayList<>();
        books.add(book);

        BookDto bookDto = new BookDto(VALID_ISBN, "test", "test", "test");
        List<BookDto> expected = new ArrayList<>();
        expected.add(bookDto);

        BookDtoMapper mapper = new BookDtoMapper();

        //WHEN
        List<BookDto> result = mapper.toDto(books);

        //THEN
        Assertions.assertEquals(expected, result);
    }

}