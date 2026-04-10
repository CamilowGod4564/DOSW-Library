package edu.eci.dosw.tdd.core.service.impl;

import edu.eci.dosw.tdd.controller.dto.BookDTO;
import edu.eci.dosw.tdd.controller.dto.BookResponseDTO;
import edu.eci.dosw.tdd.core.model.Book;
import edu.eci.dosw.tdd.persistence.mapper.BookMapper;
import edu.eci.dosw.tdd.persistence.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookServiceImplTest {

    @Mock
    private BookRepository bookRepository;

    @Mock
    private BookMapper bookMapper;

    @InjectMocks
    private BookServiceImpl bookService;

    private Book book;
    private BookDTO bookDTO;
    private BookResponseDTO bookResponseDTO;

    @BeforeEach
    void setUp() {
        bookDTO = new BookDTO();
        bookDTO.setTitle("Clean Code");
        bookDTO.setAuthor("Robert C. Martin");

        book = new Book();
        book.setId(1L);
        book.setTitle("Clean Code");
        book.setAuthor("Robert C. Martin");

        bookResponseDTO = new BookResponseDTO();
        bookResponseDTO.setId(1L);
        bookResponseDTO.setTitle("Clean Code");
        bookResponseDTO.setAuthor("Robert C. Martin");
    }

    // --- addBook ---

    @Test
    void addBook_debeRetornarBookResponseDTO_cuandoDatosValidos() {
        when(bookMapper.toEntity(bookDTO)).thenReturn(book);
        when(bookRepository.save(book)).thenReturn(book);
        when(bookMapper.toResponse(book)).thenReturn(bookResponseDTO);

        BookResponseDTO result = bookService.addBook(bookDTO);

        assertNotNull(result);
        assertEquals("Clean Code", result.getTitle());
        assertEquals("Robert C. Martin", result.getAuthor());
        assertEquals(1L, result.getId());

        verify(bookMapper).toEntity(bookDTO);
        verify(bookRepository).save(book);
        verify(bookMapper).toResponse(book);
    }

    // --- getAllBooks ---

    @Test
    void getAllBooks_debeRetornarListaDeLibros() {
        List<Book> books = List.of(book);
        List<BookResponseDTO> responseDTOs = List.of(bookResponseDTO);

        when(bookRepository.findAll()).thenReturn(books);
        when(bookMapper.toResponseList(books)).thenReturn(responseDTOs);

        List<BookResponseDTO> result = bookService.getAllBooks();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Clean Code", result.get(0).getTitle());

        verify(bookRepository).findAll();
        verify(bookMapper).toResponseList(books);
    }

    @Test
    void getAllBooks_debeRetornarListaVacia_cuandoNoHayLibros() {
        when(bookRepository.findAll()).thenReturn(List.of());
        when(bookMapper.toResponseList(List.of())).thenReturn(List.of());

        List<BookResponseDTO> result = bookService.getAllBooks();

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    // --- getBookById ---

    @Test
    void getBookById_debeRetornarLibro_cuandoExisteElId() {
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
        when(bookMapper.toResponse(book)).thenReturn(bookResponseDTO);

        BookResponseDTO result = bookService.getBookById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Clean Code", result.getTitle());

        verify(bookRepository).findById(1L);
    }

    @Test
    void getBookById_debeLanzarExcepcion_cuandoNoExisteElId() {
        when(bookRepository.findById(99L)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> bookService.getBookById(99L));

        assertEquals("Libro no encontrado con ID: 99", ex.getMessage());
        verify(bookRepository).findById(99L);
    }

    // --- updateBook ---

    @Test
    void updateBook_debeLanzarUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class,
                () -> bookService.updateBook(bookDTO));
    }

    // --- deleteBook ---

    @Test
    void deleteBook_debeEliminarLibro_cuandoExisteElId() {
        when(bookRepository.existsById(1L)).thenReturn(true);
        doNothing().when(bookRepository).deleteById(1L);

        assertDoesNotThrow(() -> bookService.deleteBook(1L));

        verify(bookRepository).existsById(1L);
        verify(bookRepository).deleteById(1L);
    }

    @Test
    void deleteBook_debeLanzarExcepcion_cuandoNoExisteElId() {
        when(bookRepository.existsById(99L)).thenReturn(false);

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> bookService.deleteBook(99L));

        assertEquals("Libro no encontrado con ID: 99", ex.getMessage());
        verify(bookRepository).existsById(99L);
        verify(bookRepository, never()).deleteById(any());
    }
}
