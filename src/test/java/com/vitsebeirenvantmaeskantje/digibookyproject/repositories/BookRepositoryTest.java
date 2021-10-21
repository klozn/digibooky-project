package com.vitsebeirenvantmaeskantje.digibookyproject.repositories;

import static org.junit.jupiter.api.Assertions.*;

import com.vitsebeirenvantmaeskantje.digibookyproject.domain.Book;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;


class BookRepositoryTest {

    @DisplayName("When the library has books and you ask them --> returns books")
    @Test
    void whenGettingAllBooks_ThenReceiveBooks(){
        //GIVEN
        BookRepository bib = new BookRepository();

        //WHEN
        List<Book> boeken = bib.getBooks();
        List<Book> results = new ArrayList<>();
        results.add(new Book("1","test","ABC","DE"));
        results.add(new Book("2","test2","Bart","W"));

        //THEN
        Assertions.assertEquals(results,boeken);
    }

}