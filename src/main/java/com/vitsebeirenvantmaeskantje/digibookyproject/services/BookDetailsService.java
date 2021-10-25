package com.vitsebeirenvantmaeskantje.digibookyproject.services;

import com.vitsebeirenvantmaeskantje.digibookyproject.api.dto.books.BookDto;
import com.vitsebeirenvantmaeskantje.digibookyproject.api.dto.books.EnhancedBookDto;
import com.vitsebeirenvantmaeskantje.digibookyproject.api.dto.mappers.BookDtoMapper;
import com.vitsebeirenvantmaeskantje.digibookyproject.domain.User;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;

@Service
public class BookDetailsService {
    private final BookService bookService;
    private final BookLendingService bookLendingService;
    private final UserService userService;
    private final BookDtoMapper bookDtoMapper;

    @Autowired
    public BookDetailsService(BookService bookService, BookLendingService bookLendingService, UserService userService, BookDtoMapper bookDtoMapper) {
        this.bookService = bookService;
        this.bookLendingService = bookLendingService;
        this.userService = userService;
        this.bookDtoMapper = bookDtoMapper;
    }

    public EnhancedBookDto getBookDetails(String isbn) {
        BookDto bookDto = bookService.getByIsbn(isbn);
        if (bookDto.isLent()) {
            String memberId = bookLendingService.getMemberIdByLentBookISBN(isbn);
            User member = userService.fetchUserIfExistElseThrowException(memberId);
            return bookDtoMapper.toEnhancedBookDto(bookDto, member.getFirstName() + " " + member.getLastName());
        }
        return bookDtoMapper.toEnhancedBookDto(bookDto, null);
    }

}
