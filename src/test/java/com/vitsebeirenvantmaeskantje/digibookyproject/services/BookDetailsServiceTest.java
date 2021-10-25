package com.vitsebeirenvantmaeskantje.digibookyproject.services;

import com.vitsebeirenvantmaeskantje.digibookyproject.api.dto.booklendings.CreateBookLendingDto;
import com.vitsebeirenvantmaeskantje.digibookyproject.api.dto.mappers.BookDtoMapper;
import com.vitsebeirenvantmaeskantje.digibookyproject.api.dto.mappers.BookLendingMapper;
import com.vitsebeirenvantmaeskantje.digibookyproject.api.dto.mappers.UserMapper;
import com.vitsebeirenvantmaeskantje.digibookyproject.repositories.BookLendingRepository;
import com.vitsebeirenvantmaeskantje.digibookyproject.repositories.BookRepository;
import com.vitsebeirenvantmaeskantje.digibookyproject.repositories.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BookDetailsServiceTest {

    private UserService userService;
    private BookService bookService;
    private BookDetailsService bookDetailsService;
    private BookLendingService bookLendingService;

    @BeforeEach
    void setup() {
        userService = new UserService(new UserRepository(), new UserMapper());
        bookService = new BookService(new BookDtoMapper(), new BookRepository(), userService);
        bookLendingService = new BookLendingService(new BookLendingMapper(), new BookLendingRepository(), userService, bookService);
        bookDetailsService = new BookDetailsService(bookService, bookLendingService, userService, new BookDtoMapper());
    }

    @DisplayName("When retrieving details of a lent book, lender fullname is present")
    @Test
    void whenAskingForBookDetailsOfLentBook_ThenFullNameOfLenderIsPresent() {
        CreateBookLendingDto lendingABook = new CreateBookLendingDto(BookRepository.ISBN_ONE, "3");
        bookLendingService.save(lendingABook);

        String expected = "Job Vanjongenhoven";

        Assertions.assertEquals(expected, bookDetailsService.getBookDetails(BookRepository.ISBN_ONE).getLenderFullName());
    }
}