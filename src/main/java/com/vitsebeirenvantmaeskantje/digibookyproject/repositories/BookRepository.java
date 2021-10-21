package com.vitsebeirenvantmaeskantje.digibookyproject.repositories;

import com.vitsebeirenvantmaeskantje.digibookyproject.domain.Book;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BookRepository {

    private final Map<String, Book> books = new HashMap<>();

    public BookRepository() {
        books.put("1", new Book("1", "test","ABC","DE"));
        books.put("2", new Book("2", "test","Bart","W"));
    }

    public Book getBookById(String id) {
        return books.get(id);
    }

    public List<Book> getBooks() {
        return new ArrayList<>(books.values());
    }


}
