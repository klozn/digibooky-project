package com.vitsebeirenvantmaeskantje.digibookyproject.services;

import com.vitsebeirenvantmaeskantje.digibookyproject.api.dto.BookDto;
import com.vitsebeirenvantmaeskantje.digibookyproject.api.dto.mappers.BookDtoMapper;
import com.vitsebeirenvantmaeskantje.digibookyproject.domain.Book;
import com.vitsebeirenvantmaeskantje.digibookyproject.repositories.BookRepository;
import com.vitsebeirenvantmaeskantje.digibookyproject.services.utility.PatternMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {

    private final BookDtoMapper bookDtoMapper;
    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookDtoMapper bookDtoMapper, BookRepository bookRepository) {
        this.bookDtoMapper = bookDtoMapper;
        this.bookRepository = bookRepository;
    }

    public List<BookDto> getAllBooks() {
        return bookDtoMapper.toDto(bookRepository.getBooks());
    }

    public BookDto getByIsbn(String isbn) {
        return bookDtoMapper.toDTO(bookRepository.getBookByIsbn(isbn));
    }

    public List<BookDto> getBookByIsbnWildcard(String partialISBN) {

        List<BookDto> foundBooks = new ArrayList<>();

        for (BookDto book : getAllBooks()) {
            if (PatternMatcher.matches(partialISBN, book.getIsbn()))
                foundBooks.add(book);
        }

        return foundBooks;
    }

    public List<BookDto> getBookByTitleWildcard(String partialInput) {
        List<BookDto> foundBooks = new ArrayList<>();

        for (BookDto book : getAllBooks()) {
            if (PatternMatcher.matches(partialInput, book.getTitle()))
                foundBooks.add(book);
        }

        return foundBooks;
    }

    public List<BookDto> getBookByAuthorWildcard(String partialInput) {
        List<BookDto> foundBooks = new ArrayList<>();

        for (BookDto book : getAllBooks()) {
            if (PatternMatcher.matches(partialInput, book.getBookAuthorFullName()))
                foundBooks.add(book);
        }

        return foundBooks;
    }

    public BookDto registerBook(BookDto bookDto, String id){
        userService.assertLibrarianId(id);
        bookRepository.save(new Book(bookDto.getIsbn(), bookDto.getTitle(), bookDto.getAuthorFirstname(),
                bookDto.getAuthorLastname()));
        return bookDto;
    }
}
