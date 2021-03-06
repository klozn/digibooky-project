package com.vitsebeirenvantmaeskantje.digibookyproject.services;

import com.vitsebeirenvantmaeskantje.digibookyproject.api.dto.booklendings.BookLendingDto;
import com.vitsebeirenvantmaeskantje.digibookyproject.api.dto.booklendings.CreateBookLendingDto;
import com.vitsebeirenvantmaeskantje.digibookyproject.api.dto.books.BookDto;
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


@Service
public class BookLendingService {

    private final BookLendingMapper bookLendingMapper;
    private final BookLendingRepository bookLendingRepository;
    private final UserService userService;
    private final BookService bookService;
    private final LocalDate CURRENT_DATE = LocalDate.now();

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

    public List<BookDto> getLentBooksByMemberId(String memberId, String librarianId) {
        userService.assertLibrarianId(librarianId);
        return bookLendingRepository.getLentBooks().stream()
                .filter(bookLending -> !bookLending.isReturned())
                .filter(bookLending -> bookLending.getMemberId().equals(memberId))
                .map(bookLending -> bookService.getByIsbn(bookLending.getIsbn()))
                .collect(Collectors.toList());
    }


    public List<BookDto> getOverdueBooks(String librarianId) {
        userService.assertLibrarianId(librarianId);
        return bookLendingRepository.getLentBooks().stream()
                .filter(bookLending -> !bookLending.isReturned())
                .filter(bookLending -> bookLending.getReturnDate().isBefore(LocalDate.now()))
                .map(bookLending -> bookService.getByIsbn(bookLending.getIsbn()))
                .collect(Collectors.toList());
    }

    public String getMemberIdByLentBookISBN(String isbn) {
        return Objects.requireNonNull(bookLendingRepository.getLentBooks().stream()
                .filter(bookLending -> bookLending.getIsbn().equals(isbn))
                .findAny().orElse(null)).getMemberId();
    }

    public BookLendingDto returnBook(String lendingID) {
        if (!isTrueLendingId(lendingID)) {
            throw new IllegalArgumentException("Lent book's ID not found.");
        }
        if (!isReturnedInTime(lendingID)) {
            System.out.println("Book is overdue!");
        }
        BookLending bookLending = bookLendingRepository.getBookLending(lendingID);
        bookService.setBookLentStatus(bookLending.getIsbn(), false);
        bookLendingRepository.returnBook(bookLending);

        return bookLendingMapper.toDto(bookLending);

    }

    private boolean isReturnedInTime(String lendingID) {
        List<BookLending> books = new ArrayList<>();
        for (BookLending book : bookLendingRepository.getLentBooks()) {
            if (book.getId().equals(lendingID)) {
                books.add(book);
            }
        }
        BookLending bookLending = books.get(0);
        return bookLending.getReturnDate().isAfter(CURRENT_DATE);
    }

    public boolean isTrueLendingId(String lendingID) {
        return bookLendingRepository.getLentBooks().stream()
                .anyMatch(book -> book.getId().equals(lendingID));
    }


}
