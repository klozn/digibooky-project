package com.vitsebeirenvantmaeskantje.digibookyproject.api.dto.mappers;

import com.vitsebeirenvantmaeskantje.digibookyproject.api.dto.BookDto;
import com.vitsebeirenvantmaeskantje.digibookyproject.domain.Book;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookDtoMapper {

    public BookDto toDTO(Book book) {
        return new BookDto(book.getIsbn(), book.getTitle(), book.getAuthorFirstname(), book.getAuthorLastname(), book.getSummary());
    }

    public Book toEntity(BookDto bookDto) {
        return new Book(bookDto.getIsbn(), bookDto.getTitle(), bookDto.getAuthorFirstname(), bookDto.getAuthorLastname());
    }

    public List<BookDto> toDto(List<Book> books) {
        return books.stream().map(this::toDTO).collect(Collectors.toList());
    }

    public List<Book> toEntity(List<BookDto> books) {
        return books.stream().map(this::toEntity).collect(Collectors.toList());
    }

}
