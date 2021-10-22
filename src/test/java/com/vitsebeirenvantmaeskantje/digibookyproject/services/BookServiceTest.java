package com.vitsebeirenvantmaeskantje.digibookyproject.services;

import com.vitsebeirenvantmaeskantje.digibookyproject.api.dto.books.BookDto;
import com.vitsebeirenvantmaeskantje.digibookyproject.api.dto.books.CreateBookDto;
import com.vitsebeirenvantmaeskantje.digibookyproject.api.dto.mappers.BookDtoMapper;
import com.vitsebeirenvantmaeskantje.digibookyproject.api.dto.mappers.UserMapper;
import com.vitsebeirenvantmaeskantje.digibookyproject.domain.exceptions.UnauthorizedUserException;
import com.vitsebeirenvantmaeskantje.digibookyproject.repositories.BookRepository;
import com.vitsebeirenvantmaeskantje.digibookyproject.repositories.UserRepository;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

class BookServiceTest {
    private final static String ADMIN_ID = "1";
    private final static String LIBRARIAN_ID = "2";
    private final static String MEMBER_ID = "3";
    private BookService bookService;
    private BookRepository bookRepository;
    private BookDto book1;
    private BookDto book2;
    private BookDto book3;

    @BeforeEach
    void setup() {
        book1 = new BookDto(BookRepository.ISBN_ONE, "de test", "Tom", "De Kock");
        book2 = new BookDto(BookRepository.ISBN_TWO, "de grote afrekening", "Bart", "Waterslaeghers");
        book3 = new BookDto(BookRepository.ISBN_THREE, "de grote afrekening - deel 2", "Bart", "Waterslaeghers");
        bookRepository = new BookRepository();
        bookService = new BookService(new BookDtoMapper(), bookRepository, new UserService(new UserRepository(), new UserMapper()));

    }

    @DisplayName("get booklist from bookrepo in form bookDto")
    @Test
    void whenAskingForBookListInService_ThenGetBook() {
        //WHEN
        List<BookDto> results = bookService.getAllBooks();

        //THEN
        Assertions.assertEquals(3, results.size());
        Assertions.assertEquals(BookRepository.ISBN_ONE, results.get(0).getIsbn());
    }


    //ISBN

    @DisplayName("Get a book by ISBN")
    @Test
    void whenAskingForBookByIsbnInService_ThenGetBookDto() {
        //WHEN
        BookDto result = bookService.getByIsbn(BookRepository.ISBN_ONE);

        //THEN
        Assertions.assertEquals("Tom", result.getAuthorFirstname());
    }

    @DisplayName("Get a book using a wildcard for the ISBN")
    @Test
    void whenAskingForABookByWildcardISBN_ThenGetABookDto() {
        //WHEN
        List<BookDto> result = bookService.getBookByIsbnWildcard("06*");
        List<BookDto> expected = new ArrayList<>();
        expected.add(bookService.getByIsbn(BookRepository.ISBN_ONE));
        //THEN
        Assertions.assertEquals(expected, result);

    }

    @DisplayName("Wildcard ISBN does not find a book")
    @Test
    void whenLookingForABookUsingAUselessWildCard_ThenReceiveNoBook() {
        //WHEN
        List<BookDto> result = bookService.getBookByIsbnWildcard("71111*");

        //THEN
        Assertions.assertEquals(0, result.size());
    }

    @DisplayName("Wildcard ISBN finds multiple books")
    @Test
    void whenLookingForABookUsingAWildCard_ThenReceiveMultipleBooks() {
        //WHEN
        List<BookDto> result = bookService.getBookByIsbnWildcard("*5*");

        //THEN
        Assertions.assertEquals(2, result.size());
    }

    //TITLE

    @DisplayName("Get a book using a wildcard for the title")
    @Test
    void whenAskingForABookByWildcardTitle_ThenGetABookDto() {
        //WHEN
        List<BookDto> result = bookService.getBookByTitleWildcard("*test*");
        List<BookDto> expected = new ArrayList<>();
        expected.add(book1);
        //THEN
        Assertions.assertEquals(expected, result);

    }

    @DisplayName("Wildcard title does not find a book")
    @Test
    void whenLookingForABookUsingAUselessTitleWildCard_ThenReceiveNoBook() {
        //WHEN
        List<BookDto> result = bookService.getBookByTitleWildcard("notfound*");

        //THEN
        Assertions.assertEquals(0, result.size());
    }

    @DisplayName("Wildcard finds multiple books based on title")
    @Test
    void whenLookingForABookUsingATitleWildCard_ThenReceiveMultipleBooks() {
        //WHEN
        List<BookDto> result = bookService.getBookByTitleWildcard("de*");
        System.out.println(result.toString());
        //THEN
        Assertions.assertEquals(3, result.size());
    }

    //AUTHOR

    @DisplayName("Get a book using a wildcard for the author's full name")
    @Test
    void whenAskingForABookByWildcardAuthorFullName_ThenGetABookDto() {
        //WHEN
        List<BookDto> result = bookService.getBookByAuthorWildcard("T*");
        List<BookDto> expected = new ArrayList<>();
        expected.add(book1);
        //THEN
        Assertions.assertEquals(expected, result);

    }

    @DisplayName("Registering a new book")
    @Nested
    class RegisteringANewBook{

        @DisplayName("When a librarian registers a new book, it works.")
        @Test
        void whenUserIsLibrarianRegisterANewBook_ThenNewBookIsAddedToTheLibrary(){
            Assertions.assertDoesNotThrow(() -> bookService.registerBook(book1, LIBRARIAN_ID));
            BookDto created = bookService.registerBook(book1, LIBRARIAN_ID);

            Assertions.assertEquals(book1.getIsbn(), created.getIsbn());
            Assertions.assertEquals(book1.getTitle(), created.getTitle());
            Assertions.assertEquals(book1.getAuthorFirstname(), created.getAuthorFirstname());
            Assertions.assertEquals(book1.getAuthorLastname(), created.getAuthorLastname());
            Assertions.assertEquals(3,bookRepository.getBooks().size());

        }
        @DisplayName("When a member registers a new book, it throws UnauthorizedUserException.")
        @Test
        void whenUserIsMemberRegisterANewBook_ThenAnExceptionIsThrown() {
            CreateBookDto createBookDto = new CreateBookDto(book1.getIsbn(),book1.getTitle(),book1.getAuthorFirstname(),
                    book1.getAuthorLastname(),book1.getSummary());
            Assertions.assertThrows(UnauthorizedUserException.class, () -> bookService.registerBook(createBookDto, MEMBER_ID));

        }
        @DisplayName("When an admin registers a new book, it throws UnauthorizedUserException.")
        @Test
        void whenUserIsAdminRegisterANewBook_ThenAnExceptionIsThrown() {
            CreateBookDto createBookDto = new CreateBookDto(book1.getIsbn(),book1.getTitle(),book1.getAuthorFirstname(),
                    book1.getAuthorLastname(),book1.getSummary());
            Assertions.assertThrows(UnauthorizedUserException.class, () -> bookService.registerBook(createBookDto, ADMIN_ID));

        }
    }

    @DisplayName("Wildcard author's full name does not find a book")
    @Test
    void whenLookingForABookUsingAUselessAuthorFullNameWildCard_ThenReceiveNoBook() {
        //WHEN
        List<BookDto> result = bookService.getBookByAuthorWildcard("notfound*");

        //THEN
        Assertions.assertEquals(0, result.size());
    }

    @DisplayName("Wildcard finds multiple books based on author's full name")
    @Test
    void whenLookingForABookUsingAuthorFullNameWildCard_ThenReceiveMultipleBooks() {
        //WHEN
        List<BookDto> result = bookService.getBookByAuthorWildcard("Bart*");
        System.out.println(result.toString());
        //THEN
        Assertions.assertEquals(2, result.size());
    }
}