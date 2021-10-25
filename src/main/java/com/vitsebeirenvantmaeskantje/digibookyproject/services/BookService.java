package com.vitsebeirenvantmaeskantje.digibookyproject.services;

import com.vitsebeirenvantmaeskantje.digibookyproject.api.dto.books.BookDto;
import com.vitsebeirenvantmaeskantje.digibookyproject.api.dto.books.CreateBookDto;
import com.vitsebeirenvantmaeskantje.digibookyproject.api.dto.books.UpdateBookDto;
import com.vitsebeirenvantmaeskantje.digibookyproject.api.dto.mappers.BookDtoMapper;
import com.vitsebeirenvantmaeskantje.digibookyproject.domain.Book;
import com.vitsebeirenvantmaeskantje.digibookyproject.domain.exceptions.BookIsDeletedException;
import com.vitsebeirenvantmaeskantje.digibookyproject.domain.exceptions.BookIsNotFoundException;
import com.vitsebeirenvantmaeskantje.digibookyproject.repositories.BookRepository;
import com.vitsebeirenvantmaeskantje.digibookyproject.services.utility.PatternMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final BookDtoMapper bookDtoMapper;
    private final BookRepository bookRepository;
    private final UserService userService;

    @Autowired
    public BookService(BookDtoMapper bookDtoMapper, BookRepository bookRepository, UserService userService) {
        this.bookDtoMapper = bookDtoMapper;
        this.bookRepository = bookRepository;
        this.userService = userService;
    }

    public List<BookDto> getAllBooks() {
        return bookRepository.getBooks().stream()
                .filter(book -> !book.isDeleted())
                .sorted(Comparator.comparing(Book::getAuthorLastname).thenComparing(Book::getAuthorFirstname))
                .map(bookDtoMapper::toDto)
                .collect(Collectors.toList());
    }

    public BookDto getByIsbn(String isbn) {
        return bookDtoMapper.toDto(fetchBookByIsbnElseThrowException(isbn));
    }

    public List<BookDto> getBookByIsbnWildcard(String partialISBN) {

        List<BookDto> foundBooks = new ArrayList<>();

        for (BookDto book : getAllBooks()) {
            if (!book.isDeleted() && PatternMatcher.matches(partialISBN, book.getIsbn()))
                foundBooks.add(book);
        }

        return foundBooks;
    }

    public List<BookDto> getBookByTitleWildcard(String partialInput) {
        List<BookDto> foundBooks = new ArrayList<>();

        for (BookDto book : getAllBooks()) {
            if (!book.isDeleted() && PatternMatcher.matches(partialInput, book.getTitle()))
                foundBooks.add(book);
        }

        return foundBooks;
    }

    public List<BookDto> getBookByAuthorWildcard(String partialInput) {
        List<BookDto> foundBooks = new ArrayList<>();

        for (BookDto book : getAllBooks()) {
            if (!book.isDeleted() && PatternMatcher.matches(partialInput, book.getBookAuthorFullName()))
                foundBooks.add(book);
        }

        return foundBooks;
    }

    public BookDto registerBook(CreateBookDto createBookDto) {
        userService.assertLibrarianId(createBookDto.getLibrarianId());
        Book created = bookRepository.save(new Book(createBookDto.getIsbn(), createBookDto.getTitle(),
                createBookDto.getAuthorFirstname(), createBookDto.getAuthorLastname()));
        created.setSummary(createBookDto.getSummary());
        return bookDtoMapper.toDto(created);
    }

    public BookDto updateBook(UpdateBookDto updateBookDto) {
        userService.assertLibrarianId(updateBookDto.getLibrarianId());
        Book toUpdate = fetchBookByIsbnForUpdateElseThrowException(updateBookDto.getIsbn());
        toUpdate.setTitle(updateBookDto.getTitle());
        toUpdate.setAuthorFirstname(updateBookDto.getAuthorFirstname());
        toUpdate.setAuthorLastname(updateBookDto.getAuthorLastname());
        toUpdate.setSummary(updateBookDto.getSummary());
        bookRepository.save(toUpdate);
        return bookDtoMapper.toDto(toUpdate);
    }

    private Book fetchBookByIsbnElseThrowException(String isbn) {
        Book book = bookRepository.getBookByIsbn(isbn);
        if (book == null) {
            throw new BookIsNotFoundException("No book found with isbn: " + isbn);
        }
        if (book.isDeleted()) {
            throw new BookIsDeletedException("Book with isbn: " + isbn + " has been deleted from the database.");
        }
        return book;
    }

    private Book fetchBookByIsbnForUpdateElseThrowException(String isbn) {
        Book book = bookRepository.getBookByIsbn(isbn);
        if (book == null) {
            throw new BookIsNotFoundException("No book found with isbn: " + isbn);
        }
        return book;
    }

    public boolean assertIsbnExists(String isbn) {
        return bookRepository.assertIsbnExists(isbn);
    }

    public boolean isBookLent(String isbn) {
        return bookRepository.isBookLent(isbn);
    }

    public void setBookLentStatus(String isbn, boolean bookLent) {
        bookRepository.setBookLentStatus(isbn, bookLent);
    }

    public BookDto deleteByIsbn(String isbn, String userId) {
        userService.assertLibrarianId(userId);
        Book book = fetchBookByIsbnElseThrowException(isbn);
        book.setDeleted(true);
        bookRepository.save(book);
        return bookDtoMapper.toDto(book);
    }

    public BookDto undeleteByIsbn(String isbn, String userId) {
        userService.assertLibrarianId(userId);
        Book book = bookRepository.getBookByIsbn(isbn);
        book.setDeleted(false);
        bookRepository.save(book);
        return bookDtoMapper.toDto(book);
    }
}
