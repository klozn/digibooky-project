package com.vitsebeirenvantmaeskantje.digibookyproject.repositories;

import com.vitsebeirenvantmaeskantje.digibookyproject.domain.Book;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class BookRepository {

    private final Map<String, Book> books = new HashMap<>();

    public Book getBookById(String id) {
        return books.get(id);
    }

    public List<Book> getBooks() {
        return new ArrayList<>(books.values());
    }


}
