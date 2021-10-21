package com.vitsebeirenvantmaeskantje.digibookyproject.api;

import com.vitsebeirenvantmaeskantje.digibookyproject.api.dto.BookDto;
import com.vitsebeirenvantmaeskantje.digibookyproject.api.dto.mappers.BookDtoMapper;
import com.vitsebeirenvantmaeskantje.digibookyproject.services.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/books")
public class BookController {
    private final BookService bookService;
    private final BookDtoMapper bookDtoMapper;
    private final Logger logger = LoggerFactory.getLogger(BookController.class);

    @Autowired
    public BookController(BookService bookService, BookDtoMapper bookDtoMapper) {
        this.bookService = bookService;
        this.bookDtoMapper = bookDtoMapper;
    }

    @GetMapping(produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public List<BookDto> getBooks() {
        logger.info("Retrieved all books");
        return bookService.getAllBooks();
    }

    @GetMapping(produces = "application/json", path = "{ISBN}")
    @ResponseStatus(HttpStatus.OK)
    public BookDto getBookByISBN(@PathVariable("ISBN") String ISBN) {
        logger.info("Retrieved book with id " + ISBN);
        return bookService.getByIsbn(ISBN);
    }

  //  wildcardsearch nog af te werken
}
