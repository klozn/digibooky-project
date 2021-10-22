package com.vitsebeirenvantmaeskantje.digibookyproject.repositories;

import com.vitsebeirenvantmaeskantje.digibookyproject.domain.Book;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class BookRepository {

    public final static String ISBN_ONE = "0685374599";
    public final static String ISBN_TWO = "0305293222";
    public final static String ISBN_THREE = "0920401961";
    private final Map<String, Book> books = new HashMap<>();

    public BookRepository() {

        Book book1 = new Book(ISBN_ONE, "de test", "Tom", "De Kock");
        Book book2 = new Book(ISBN_TWO, "de grote afrekening", "Bart", "Waterslaeghers");
        Book book3 = new Book(ISBN_THREE, "de grote afrekening - deel 2", "Bart", "Waterslaeghers");

        book1.setSummary("blablablabla");
        book2.setSummary("blablablabla");
        book3.setSummary("blablablabla");

        books.put(book1.getIsbn(), book1);
        books.put(book2.getIsbn(), book2);
        books.put(book3.getIsbn(), book3);

    }

    public List<Book> getBooks() {
        return new ArrayList<>(books.values());
    }

    public Book save(Book book) {
        books.put(book.getIsbn(), book);
        return book;
    }

    public Book getBookByIsbn(String isbn) throws IllegalArgumentException {
        Optional<Book> foundByISBN = Optional.ofNullable(books.get(isbn));
        if (foundByISBN.isEmpty()) {
            throw new IllegalArgumentException("Book with ISBN " + isbn + " not found");
        }
        return foundByISBN.get();
    }

    public boolean isBookLent(String isbn) {
        return getBookByIsbn(isbn).isLent();
    }

    public boolean assertIsbnExists(String isbn) {
        try {
            getBookByIsbn(isbn);
        } catch (IllegalArgumentException exception) {
            return false;
        }
        return true;
    }


    public Book setBookLentStatus(String isbn, boolean lendingStatus) {
        Book bookToSetLentStatus = getBookByIsbn(isbn);
        bookToSetLentStatus.setLent(lendingStatus);
        return bookToSetLentStatus;
    }

}
