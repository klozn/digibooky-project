package com.vitsebeirenvantmaeskantje.digibookyproject.repositories;

import com.vitsebeirenvantmaeskantje.digibookyproject.domain.Book;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class BookRepository {

    private final Map<String, Book> books = new HashMap<>();

    public BookRepository() {
        Book book1 = new Book("1", "test", "ABC", "DE");
        Book book2 = new Book("2", "test", "ABC", "DE");
        book1.setSummary("blablablabla");
        book2.setSummary("blablablabla");

        books.put("1", book1);
        books.put("2", book2);
    }

    public List<Book> getBooks() {
        return new ArrayList<>(books.values());
    }


    public Book getBookByIsbn(String isbn) throws IllegalArgumentException {
        Optional<Book> foundByISBN = Optional.ofNullable(books.get(isbn));
        if (foundByISBN.isEmpty()) {
            throw new IllegalArgumentException("Book with ISBN " + isbn + " not found");
        }
        return books.get(isbn);
    }
}
