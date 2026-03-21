package edu.eci.dosw.tdd.core.service;

import edu.eci.dosw.tdd.controller.dto.BookDTO;
import edu.eci.dosw.tdd.controller.mapper.BookMapper;
import edu.eci.dosw.tdd.core.exception.BookNotAvaliableException;
import edu.eci.dosw.tdd.core.model.Book;
import edu.eci.dosw.tdd.core.validator.BookValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @Mock
    private BookMapper bookMapper;

    @Mock
    private BookValidator bookValidator;

    @InjectMocks
    private BookService bookService;

    private BookDTO bookDTO;
    private Book book;

    @BeforeEach
    void setUp() {
        bookDTO = new BookDTO(null, "Clean Code", "Robert C. Martin", 5, null);

        book = new Book();
        book.setTitle("Clean Code");
        book.setAuthor("Robert C. Martin");
        book.setEjemplares(5);
    }

    private String addBookAndGetId() {
        when(bookMapper.toEntity(bookDTO)).thenReturn(book);
        when(bookMapper.toDto(any(Book.class))).thenReturn(bookDTO);
        bookService.addBook(bookDTO);
        return book.getId();
    }

    @Test
    void testAddBookReturnsDTO() {
        BookDTO expectedDTO = new BookDTO("1", "Clean Code", "Robert C. Martin", 5, 5);

        when(bookMapper.toEntity(bookDTO)).thenReturn(book);
        when(bookMapper.toDto(any(Book.class))).thenReturn(expectedDTO);

        BookDTO result = bookService.addBook(bookDTO);

        assertNotNull(result);
        assertEquals("Clean Code", result.getTitle());
        assertEquals(5, result.getDisponible());
        verify(bookValidator).validateCreate(bookDTO);
    }

    @Test
    void testAddBookSetsDisponiblesEqualToEjemplares() {
        book.setEjemplares(3);
        BookDTO expectedDTO = new BookDTO("1", "Clean Code", "Robert C. Martin", 3, 3);

        when(bookMapper.toEntity(bookDTO)).thenReturn(book);
        when(bookMapper.toDto(any(Book.class))).thenReturn(expectedDTO);

        bookService.addBook(bookDTO);

        assertEquals(3, book.getDisponibles());
    }

    @Test
    void testAddBookCallsValidator() {
        when(bookMapper.toEntity(bookDTO)).thenReturn(book);
        when(bookMapper.toDto(any(Book.class))).thenReturn(bookDTO);

        bookService.addBook(bookDTO);

        verify(bookValidator, times(1)).validateCreate(bookDTO);
    }

    @Test
    void testGetAllBooksReturnsEmptyList() {
        List<BookDTO> result = bookService.getAllBooks();
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void testGetAllBooksReturnsAllAddedBooks() {
        Book book2 = new Book();
        book2.setTitle("Refactoring");
        book2.setAuthor("Martin Fowler");
        book2.setEjemplares(2);

        BookDTO bookDTO2 = new BookDTO(null, "Refactoring", "Martin Fowler", 2, null);
        BookDTO dto1 = new BookDTO("1", "Clean Code", "Robert C. Martin", 5, 5);
        BookDTO dto2 = new BookDTO("2", "Refactoring", "Martin Fowler", 2, 2);

        when(bookMapper.toEntity(bookDTO)).thenReturn(book);
        when(bookMapper.toEntity(bookDTO2)).thenReturn(book2);
        when(bookMapper.toDto(book)).thenReturn(dto1);
        when(bookMapper.toDto(book2)).thenReturn(dto2);

        bookService.addBook(bookDTO);
        bookService.addBook(bookDTO2);

        List<BookDTO> result = bookService.getAllBooks();
        assertEquals(2, result.size());
    }

    @Test
    void testGetBookByIdReturnsDTO() {
        BookDTO expectedDTO = new BookDTO("1", "Clean Code", "Robert C. Martin", 5, 5);
        when(bookMapper.toEntity(bookDTO)).thenReturn(book);
        when(bookMapper.toDto(any(Book.class))).thenReturn(expectedDTO);

        bookService.addBook(bookDTO);
        String generatedId = book.getId();

        BookDTO result = bookService.getBookById(generatedId);
        assertNotNull(result);
    }

    @Test
    void testGetBookByIdThrowsWhenNotFound() {
        assertThrows(BookNotAvaliableException.class, () -> bookService.getBookById("nonexistent"));
    }

    @Test
    void testGetBookEntityByIdReturnsBook() {
        when(bookMapper.toEntity(bookDTO)).thenReturn(book);
        when(bookMapper.toDto(any(Book.class))).thenReturn(bookDTO);

        bookService.addBook(bookDTO);
        String generatedId = book.getId();

        Book result = bookService.getBookEntityById(generatedId);
        assertNotNull(result);
        assertEquals("Clean Code", result.getTitle());
    }

    @Test
    void testGetBookEntityByIdThrowsWhenNotFound() {
        assertThrows(BookNotAvaliableException.class, () -> bookService.getBookEntityById("nonexistent"));
    }

    @Test
    void testUpdateAvailabilityDecreases() {
        String id = addBookAndGetId();

        bookService.updateAvailability(id, -1);

        assertEquals(4, book.getDisponibles());
    }

    @Test
    void testUpdateAvailabilityIncreases() {
        String id = addBookAndGetId();
        book.setDisponibles(4);

        bookService.updateAvailability(id, 1);

        assertEquals(5, book.getDisponibles());
    }

    @Test
    void testUpdateAvailabilityThrowsWhenBookNotFound() {
        assertThrows(BookNotAvaliableException.class, () -> bookService.updateAvailability("nonexistent", -1));
    }

    @Test
    void testUpdateAvailabilityThrowsWhenGoesNegative() {
        String id = addBookAndGetId();
        book.setDisponibles(0);

        assertThrows(IllegalArgumentException.class, () -> bookService.updateAvailability(id, -1));
    }

    @Test
    void testUpdateAvailabilityThrowsWhenExceedsEjemplares() {
        String id = addBookAndGetId();

        assertThrows(IllegalArgumentException.class, () -> bookService.updateAvailability(id, 1));
    }
}
