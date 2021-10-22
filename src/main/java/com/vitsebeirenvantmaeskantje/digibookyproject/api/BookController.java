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

    @GetMapping(produces = "application/json", path = "/search/{searchType}/{partialInput}/{wildcard}")
    @ResponseStatus(HttpStatus.OK)
    public List<BookDto> getBookByBySearchTypeWithWildCard(@PathVariable("searchType") String searchType,
                                                           @PathVariable("partialInput") String partialInput,
                                                           @PathVariable("wildcard") Character wildcard) {

        logger.info("Searching for book via " + searchType + " with search query " + partialInput
                + " and wildcard character " + wildcard);

        return switch (searchType) {
            case "ISBN" -> bookService.getBookByIsbnWildcard(partialInput, wildcard);
            case "bookTitle" -> bookService.getBookByTitleWildcard(partialInput, wildcard);
            case "bookAuthor" -> bookService.getBookByAuthorWildcard(partialInput, wildcard);
            default -> throw new IllegalArgumentException("Impossible search type.");
        };

    }

}
