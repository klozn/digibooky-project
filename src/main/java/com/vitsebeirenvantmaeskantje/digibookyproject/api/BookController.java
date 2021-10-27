package com.vitsebeirenvantmaeskantje.digibookyproject.api;

import com.vitsebeirenvantmaeskantje.digibookyproject.api.dto.books.BookDto;
import com.vitsebeirenvantmaeskantje.digibookyproject.api.dto.books.CreateBookDto;
import com.vitsebeirenvantmaeskantje.digibookyproject.api.dto.books.EnhancedBookDto;
import com.vitsebeirenvantmaeskantje.digibookyproject.api.dto.books.UpdateBookDto;
import com.vitsebeirenvantmaeskantje.digibookyproject.services.BookDetailsService;
import com.vitsebeirenvantmaeskantje.digibookyproject.services.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/books")
public class BookController {
    private final BookService bookService;
    private final BookDetailsService bookDetailsService;
    private final Logger logger = LoggerFactory.getLogger(BookController.class);

    @Autowired
    public BookController(BookService bookService, BookDetailsService bookDetailsService) {
        this.bookService = bookService;
        this.bookDetailsService = bookDetailsService;
    }

    @GetMapping(produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public List<BookDto> getBooks() {
        logger.info("Retrieved all books");
        return bookService.getAllBooks();
    }

    @GetMapping(produces = "application/json", path = "{isbn}")
    @ResponseStatus(HttpStatus.OK)
    public EnhancedBookDto getBookByISBN(@PathVariable("isbn") String ISBN) {
        logger.info("Retrieved book with id " + ISBN);
        return bookDetailsService.getBookDetails(ISBN);
    }

    @GetMapping(produces = "application/json", path = "/search")
    @ResponseStatus(HttpStatus.OK)
    public List<BookDto> getBookByBySearchTypeWithWildCard(@RequestParam("searchType") String searchType,
                                                           @RequestParam("partialInput") String partialInput) {
        logger.info("Searching for book via " + searchType + " with search query " + partialInput);

        return switch (searchType) {
            case "isbn" -> bookService.getBookByIsbnWildcard(partialInput);
            case "bookTitle" -> bookService.getBookByTitleWildcard(partialInput);
            case "bookAuthor" -> bookService.getBookByAuthorWildcard(partialInput);
            default -> throw new IllegalArgumentException("Impossible search type.");
        };

    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public BookDto registerBook(@RequestBody CreateBookDto createBookDto) {
        logger.info("Register new book with ISBN " + createBookDto.getIsbn() + " by userID " + createBookDto.getLibrarianId() + ".");
        return bookService.registerBook(createBookDto);
    }

    @PutMapping(consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public BookDto updateBook(@RequestBody UpdateBookDto updateBookDto) {
        logger.info("Updating book with ISBN " + updateBookDto.getIsbn() + " by userID " + updateBookDto.getLibrarianId() + ".");
        return bookService.updateBook(updateBookDto);
    }

    @DeleteMapping(path = "/{isbn}/{userId}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public BookDto deleteBook(@PathVariable String isbn, @PathVariable String userId) {
        // CODEREVIEW Remove {userId} from url, turn into @RequestHeader
        logger.info("Deleting book with ISBN " + isbn + " by userID: " + userId + ".");
        return bookService.deleteByIsbn(isbn, userId);
    }

    @PatchMapping(path = "/undelete/{isbn}/{userId}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public BookDto undeleteBook(@PathVariable String isbn, @PathVariable String userId) {
        logger.info("Restoring book with ISBN " + isbn + " by userID: " + userId + ".");
        return bookService.undeleteByIsbn(isbn, userId);
    }

}
