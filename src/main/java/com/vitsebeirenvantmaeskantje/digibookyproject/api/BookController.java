package com.vitsebeirenvantmaeskantje.digibookyproject.api;

import com.vitsebeirenvantmaeskantje.digibookyproject.api.dto.books.BookDto;
import com.vitsebeirenvantmaeskantje.digibookyproject.api.dto.books.CreateBookDto;
import com.vitsebeirenvantmaeskantje.digibookyproject.api.dto.books.UpdateBookDto;
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
    private final Logger logger = LoggerFactory.getLogger(BookController.class);

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping(produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public List<BookDto> getBooks() {
        logger.info("Retrieved all books");
        return bookService.getAllBooks();
    }

    @GetMapping(produces = "application/json", path = "{isbn}")
    @ResponseStatus(HttpStatus.OK)
    public BookDto getBookByISBN(@PathVariable("isbn") String ISBN) {
        logger.info("Retrieved book with id " + ISBN);
        return bookService.getByIsbn(ISBN);
    }

    @GetMapping(produces = "application/json", path = "/search/{searchType}/{partialInput}")
    @ResponseStatus(HttpStatus.OK)
    public List<BookDto> getBookByBySearchTypeWithWildCard(@PathVariable("searchType") String searchType,
                                                           @PathVariable("partialInput") String partialInput) {

        logger.info("Searching for book via " + searchType + " with search query ");

        return switch (searchType) {
            case "isbn" -> bookService.getBookByIsbnWildcard(partialInput);
            case "bookTitle" -> bookService.getBookByTitleWildcard(partialInput);
            case "bookAuthor" -> bookService.getBookByAuthorWildcard(partialInput);
            default -> throw new IllegalArgumentException("Impossible search type.");
        };

    }

    @PostMapping(consumes = "application/json", produces = "application/json", path = "/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public BookDto registerBook(@RequestBody CreateBookDto createBookDto, @PathVariable("id") String userId) {
        logger.info("Register new book with ISBN " + createBookDto.getIsbn() + " by userID " + userId + ".");
        return bookService.registerBook(createBookDto, userId);
    }
}
