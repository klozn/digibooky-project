package com.vitsebeirenvantmaeskantje.digibookyproject.api.dto.mappers;

import com.vitsebeirenvantmaeskantje.digibookyproject.api.dto.booklendings.BookLendingDto;
import com.vitsebeirenvantmaeskantje.digibookyproject.api.dto.booklendings.CreateBookLendingDto;
import com.vitsebeirenvantmaeskantje.digibookyproject.domain.BookLending;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookLendingMapper {

    public BookLendingDto toDto(BookLending bookLending) {
        return new BookLendingDto(bookLending.getId(), bookLending.getIsbn(), bookLending.getMemberId(), bookLending.getReturnDate());
    }

    public BookLending toEntity(CreateBookLendingDto bookLendingDto) {
        return new BookLending(bookLendingDto.getIsbn(), bookLendingDto.getMemberId());
    }

    public List<BookLendingDto> toDto(List<BookLending> bookLendings) {
        return bookLendings.stream().map(this::toDto).collect(Collectors.toList());
    }
}
