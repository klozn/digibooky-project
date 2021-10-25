package com.vitsebeirenvantmaeskantje.digibookyproject.services;

import com.vitsebeirenvantmaeskantje.digibookyproject.api.dto.booklendings.BookLendingDto;
import com.vitsebeirenvantmaeskantje.digibookyproject.api.dto.booklendings.CreateBookLendingDto;
import com.vitsebeirenvantmaeskantje.digibookyproject.api.dto.books.BookDto;
import com.vitsebeirenvantmaeskantje.digibookyproject.api.dto.books.EnhancedBookDto;
import com.vitsebeirenvantmaeskantje.digibookyproject.api.dto.mappers.BookLendingMapper;
import com.vitsebeirenvantmaeskantje.digibookyproject.domain.BookLending;
import com.vitsebeirenvantmaeskantje.digibookyproject.domain.exceptions.UserNotFoundException;
import com.vitsebeirenvantmaeskantje.digibookyproject.repositories.BookLendingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class BookLendingService {

    private final BookLendingMapper bookLendingMapper;
    private final BookLendingRepository bookLendingRepository;
    private final UserService userService;
    private final BookService bookService;

    @Autowired
    public BookLendingService(BookLendingMapper bookLendingMapper, BookLendingRepository bookLendingRepository,
                              UserService userService, BookService bookService) {
        this.bookLendingMapper = bookLendingMapper;
        this.bookLendingRepository = bookLendingRepository;
        this.userService = userService;
        this.bookService = bookService;
    }

    public BookLendingDto save(CreateBookLendingDto bookLendingDto) {

        if (!bookService.assertIsbnExists(bookLendingDto.getIsbn())) {
            throw new IllegalArgumentException("Book ISBN " + bookLendingDto.getIsbn() + " does not exist");
        }

        if (bookService.isBookLent(bookLendingDto.getIsbn())) {
            throw new IllegalArgumentException("Book with ISBN " + bookLendingDto.getIsbn() + " is already lent");
        }

        if (!userService.assertUserIdExistsABoolean(bookLendingDto.getMemberId())) {
            throw new UserNotFoundException("User with id " + bookLendingDto.getMemberId() + " does not exist");
        }

        BookLending lentBook = new BookLending(bookLendingDto.getIsbn(), bookLendingDto.getMemberId());
        bookLendingRepository.save(lentBook);

        bookService.setBookLentStatus(bookLendingDto.getIsbn(), true);
        return bookLendingMapper.toDto(lentBook);
    }

}
