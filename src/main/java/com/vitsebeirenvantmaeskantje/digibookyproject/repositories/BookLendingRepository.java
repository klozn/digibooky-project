package com.vitsebeirenvantmaeskantje.digibookyproject.repositories;

import com.vitsebeirenvantmaeskantje.digibookyproject.domain.BookLending;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BookLendingRepository {
    private final Map<String, BookLending> lentBooks = new HashMap<>();

    public List<BookLending> getLentBooks() {
        return new ArrayList<>(lentBooks.values());
    }

    public BookLending save(BookLending bookLending) {
        return lentBooks.put(bookLending.getId(), bookLending);
    }

    public BookLending getBookLending(String bookLendingId) {
        return lentBooks.get(bookLendingId);
    }

    public void returnBook(BookLending bookLending) {
        bookLending.setReturned(true);
        lentBooks.remove(bookLending.getId());
    }


}
