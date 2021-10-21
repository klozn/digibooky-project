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

        books.put(book1.getIsbn(), book1);
        books.put(book2.getIsbn(), book2);
    }

    public List<Book> getBooks() {
        return new ArrayList<>(books.values());
    }


    public Book getBookByIsbn(String isbn) throws IllegalArgumentException {
        Optional<Book> foundByISBN = Optional.ofNullable(books.get(isbn));
        if (foundByISBN.isEmpty()) {
            throw new IllegalArgumentException("Book with ISBN " + isbn + " not found");
        }
        return foundByISBN.get();
    }

    public List<Book> getBookByIsbnWildcard(String partialISBN, Character wildcard) {

        List<Book> foundBooks = new ArrayList<>();

        for (Book book : getBooks()) {
            if (patternMatcher(partialISBN, book.getIsbn(), wildcard))
                foundBooks.add(book);
        }

        return foundBooks;
    }


    private static boolean patternMatcher(String partialISBN, String ISBN, Character wildcard) {

        // If we reach at the end of both strings,
        // we are done
        if (partialISBN.length() == 0 && ISBN.length() == 0)
            return true;

        // Make sure that the characters after '*'
        // are present in second string.
        // This function assumes that the first
        // string will not contain two consecutive '*'
        if (partialISBN.length() > 1 && partialISBN.charAt(0) == wildcard &&
                ISBN.length() == 0)
            return false;

        // If the first string contains '?',
        // or current characters of both strings match
        if ((partialISBN.length() > 1 && partialISBN.charAt(0) == wildcard) ||
                (partialISBN.length() != 0 && ISBN.length() != 0 &&
                        partialISBN.charAt(0) == ISBN.charAt(0)))
            return patternMatcher(partialISBN.substring(1),
                    ISBN.substring(1), wildcard);

        // If there is *, then there are two possibilities
        // a) We consider current character of second string
        // b) We ignore current character of second string.
        if (partialISBN.length() > 0 && partialISBN.charAt(0) == wildcard)
            return patternMatcher(partialISBN.substring(1), ISBN, wildcard) ||
                    patternMatcher(partialISBN, ISBN.substring(1), wildcard);
        return false;
    }

}
