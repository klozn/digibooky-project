package com.vitsebeirenvantmaeskantje.digibookyproject.services;

import com.vitsebeirenvantmaeskantje.digibookyproject.api.dto.BookDto;
import com.vitsebeirenvantmaeskantje.digibookyproject.api.dto.mappers.BookDtoMapper;
import com.vitsebeirenvantmaeskantje.digibookyproject.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public List<BookDto> getBookByIsbnWildcard(String partialISBN, Character wildcard) {
        return bookDtoMapper.toDto(bookRepository.getBookByIsbnWildcard(partialISBN, wildcard));
    }
}
