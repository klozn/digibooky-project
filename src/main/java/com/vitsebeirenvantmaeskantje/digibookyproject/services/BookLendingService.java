package com.vitsebeirenvantmaeskantje.digibookyproject.services;

import com.vitsebeirenvantmaeskantje.digibookyproject.api.dto.booklendings.BookLendingDto;
import com.vitsebeirenvantmaeskantje.digibookyproject.api.dto.booklendings.CreateBookLendingDto;
import com.vitsebeirenvantmaeskantje.digibookyproject.api.dto.mappers.BookLendingMapper;
import com.vitsebeirenvantmaeskantje.digibookyproject.domain.BookLending;
import com.vitsebeirenvantmaeskantje.digibookyproject.domain.exceptions.UserNotFoundException;
import com.vitsebeirenvantmaeskantje.digibookyproject.repositories.BookLendingRepository;
import com.vitsebeirenvantmaeskantje.digibookyproject.repositories.BookRepository;
import com.vitsebeirenvantmaeskantje.digibookyproject.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookLendingService {

    private final BookLendingMapper bookLendingMapper;
    private final BookRepository bookRepository;
    private final BookLendingRepository bookLendingRepository;
    private final UserService userService;
    private final BookService bookService;
    private final UserRepository userRepository;

    @Autowired
    public BookLendingService(BookLendingMapper bookLendingMapper, BookRepository bookRepository,
                              BookLendingRepository bookLendingRepository, UserService userService,
                              BookService bookService, UserRepository userRepository) {
        this.bookLendingMapper = bookLendingMapper;
        this.bookRepository = bookRepository;
        this.bookLendingRepository = bookLendingRepository;
        this.userService = userService;
        this.bookService = bookService;
        this.userRepository = userRepository;
    }

    public BookLendingDto save(CreateBookLendingDto bookLendingDto) {
        if (bookRepository.getBookByIsbn(bookLendingDto.getIsbn()).isLent()) {
            throw new IllegalArgumentException("Book is already lent");
        }

        try {
            userService.fetchUserIfExistElseThrowException(bookLendingDto.getMemberId());
        } catch (UserNotFoundException exception) {
            throw new UserNotFoundException("User not found" + exception.getMessage());
        }

        BookLending lentBook = new BookLending(bookLendingDto.getIsbn(), bookLendingDto.getMemberId());
        bookLendingRepository.save(lentBook);

        bookRepository.setBookLentStatus(bookLendingDto.getIsbn(), true);
        return bookLendingMapper.toDto(lentBook);
    }


}
