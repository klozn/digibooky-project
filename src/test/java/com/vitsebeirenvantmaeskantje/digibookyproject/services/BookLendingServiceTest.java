package com.vitsebeirenvantmaeskantje.digibookyproject.services;

import com.vitsebeirenvantmaeskantje.digibookyproject.api.dto.booklendings.BookLendingDto;
import com.vitsebeirenvantmaeskantje.digibookyproject.api.dto.booklendings.CreateBookLendingDto;
import com.vitsebeirenvantmaeskantje.digibookyproject.api.dto.mappers.BookDtoMapper;
import com.vitsebeirenvantmaeskantje.digibookyproject.api.dto.mappers.BookLendingMapper;
import com.vitsebeirenvantmaeskantje.digibookyproject.api.dto.mappers.UserMapper;
import com.vitsebeirenvantmaeskantje.digibookyproject.domain.exceptions.UserNotFoundException;
import com.vitsebeirenvantmaeskantje.digibookyproject.repositories.BookLendingRepository;
import com.vitsebeirenvantmaeskantje.digibookyproject.repositories.BookRepository;
import com.vitsebeirenvantmaeskantje.digibookyproject.repositories.UserRepository;
import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

class BookLendingServiceTest {

    private static final String ISBN_ONE = BookRepository.ISBN_ONE;

    private BookLendingService bookLendingService;

    private CreateBookLendingDto validDTO;
    private CreateBookLendingDto invalidISBNDTO;
    private CreateBookLendingDto invalidMemberIdDTO;


    @BeforeEach
    void setup() {
        bookLendingService = new BookLendingService(new BookLendingMapper(),
                new BookLendingRepository(), new UserService(new UserRepository(), new UserMapper()),
                new BookService(new BookDtoMapper(), new BookRepository(),
                        new UserService(new UserRepository(), new UserMapper())));

        validDTO = new CreateBookLendingDto(ISBN_ONE, "1");
        invalidISBNDTO = new CreateBookLendingDto("111", "1");
        invalidMemberIdDTO = new CreateBookLendingDto(ISBN_ONE, "10000");
    }


    @DisplayName("Valid book lending has auto generated ID")
    @Test
    void whenSavingBookLendingWithValidISBNAndMemberID_ThenBookLendingHasAutoGeneratedId() {
        //WHEN
        BookLendingDto lentBook = bookLendingService.save(validDTO);

        //THEN
        Assertions.assertNotNull(lentBook.getId());
    }


    @DisplayName("Valid book lending has return date now plus 21 days")
    @Test
    void whenSavingBookLendingWithValidISBNAndMemberID_ThenBookLendingHasCorrectReturnDate() {
        //WHEN
        BookLendingDto lentBook = bookLendingService.save(validDTO);

        int returnAfterNumberOfDays = 21;
        LocalDate expectedReturnDate = LocalDate.now().plusDays(returnAfterNumberOfDays);

        //THEN
        Assertions.assertEquals(expectedReturnDate, lentBook.getReturnDate());
    }

    @DisplayName("Valid book lending has correct ISBN")
    @Test
    void whenSavingBookLendingWithValidISBNAndMemberID_ThenBookLendingHasCorrectISBN() {
        //WHEN
        BookLendingDto lentBook = bookLendingService.save(validDTO);

        //THEN
        Assertions.assertEquals(ISBN_ONE, lentBook.getIsbn());
    }

    @DisplayName("Double lending is not possible")
    @Test
    void whenSavingValidBookLendingThatIsNotLendable_ThenExceptionIsThrown() {
        //WHEN
        bookLendingService.save(validDTO);

        //THEN
        assertThrows(IllegalArgumentException.class, () -> bookLendingService.save(validDTO));

    }

    @DisplayName("Book lending with invalid ISBN throws exception")
    @Test
    void whenSavingBookLendingWithInValidISBN_ThenExceptionIsThrown() {

        assertThrows(IllegalArgumentException.class, () -> bookLendingService.save(invalidISBNDTO));
    }


    @DisplayName("Book lending with invalid Member ID throws exception")
    @Test
    void whenSavingBookLendingWithInValidMemberID_ThenExceptionIsThrown() {

        assertThrows(UserNotFoundException.class, () -> bookLendingService.save(invalidMemberIdDTO));
    }

/*    @DisplayName("get lent books by memberId returns all non returned books lent to that member")
    @Test
    void getLentBooksByMemberId_whenUserIsLibrarian_returnsAllNonReturnedBooksLentToThatMember() {
        // TODO: 25/10/2021 implement
        assertTrue(false);
    }*/

    @Nested
    @DisplayName("Returning a book")
    class ReturningABook {

        @DisplayName("Member returns a book that is not overdue")
        @Test
        void whenReturningABookThatIsNotOverdue_ThenItIsAlright() {
            //GIVEN
            BookLendingDto lentBook = bookLendingService.save(validDTO);
            String lendingId = lentBook.getId();
            LocalDate localDate = LocalDate.now();
            LocalDate lastReturningDate = lentBook.getReturnDate();
            String isbnReturnedBook = lentBook.getIsbn();
            BookService bookService = new BookService(new BookDtoMapper(), new BookRepository(),
                    new UserService(new UserRepository(), new UserMapper()));
            //WHEN
            bookLendingService.returnBook(lendingId);
            //THEN
            Assertions.assertTrue(lastReturningDate.isAfter(localDate));
            Assertions.assertFalse(bookService.isBookLent(isbnReturnedBook));
        }


        @DisplayName("Member gives wrong lending id.")
        @Test
        void whenMemberDeliversAWrongLendingID_ThenThrowAnException() {
            //GIVEN
            BookLendingDto lentBook = bookLendingService.save(validDTO);
            String lendingId = "test";
            LocalDate localDate = LocalDate.now();
            LocalDate lastReturningDate = lentBook.getReturnDate();
            String isbnReturnedBook = lentBook.getIsbn();
            BookService bookService = new BookService(new BookDtoMapper(), new BookRepository(),
                    new UserService(new UserRepository(), new UserMapper()));

            //THEN
            assertThrows(IllegalArgumentException.class, () -> bookLendingService.returnBook(lendingId));
        }
    }

}
