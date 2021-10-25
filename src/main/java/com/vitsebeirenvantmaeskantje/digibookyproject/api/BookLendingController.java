package com.vitsebeirenvantmaeskantje.digibookyproject.api;

import com.vitsebeirenvantmaeskantje.digibookyproject.api.dto.booklendings.BookLendingDto;
import com.vitsebeirenvantmaeskantje.digibookyproject.api.dto.booklendings.CreateBookLendingDto;
import com.vitsebeirenvantmaeskantje.digibookyproject.services.BookLendingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/lendings")
public class BookLendingController {

    private final BookLendingService bookLendingService;
    private final Logger logger = LoggerFactory.getLogger(BookLendingController.class);

    @Autowired
    public BookLendingController(BookLendingService bookLendingService) {
        this.bookLendingService = bookLendingService;
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public BookLendingDto lendBook(@RequestBody CreateBookLendingDto createBookLendingDto) {
        logger.info("Attempting to lend book with ISBN " + createBookLendingDto.getIsbn());
        return bookLendingService.save(createBookLendingDto);
    }

    @PutMapping( path = "/{id}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public BookLendingDto returnBook(@PathVariable("id") String id){
        logger.info("Attempting to return book with lending id " + id);
        return bookLendingService.returnBook(id);
    }

}
