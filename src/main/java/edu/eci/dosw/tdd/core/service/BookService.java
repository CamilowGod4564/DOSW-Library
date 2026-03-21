package edu.eci.dosw.tdd.core.service;

import edu.eci.dosw.tdd.controller.dto.BookDTO;
import edu.eci.dosw.tdd.core.model.Book;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import edu.eci.dosw.tdd.core.exception.BookNotAvaliableException;
import edu.eci.dosw.tdd.controller.mapper.BookMapper;
import edu.eci.dosw.tdd.core.validator.BookValidator;
import edu.eci.dosw.tdd.core.util.IdGeneratorUtil;

import java.util.stream.Collectors;

@Service
public class BookService {

    private Map<String, Book> books = new HashMap<>();

    private final BookMapper bookMapper;
    private final BookValidator bookValidator;

    public BookService(BookMapper bookMapper, BookValidator bookValidator) {
        this.bookMapper = bookMapper;
        this.bookValidator = bookValidator;
    }

    public BookDTO addBook(BookDTO bookDTO) {
        // 1. Validar datos
        bookValidator.validateCreate(bookDTO);

        // 2. Convertir DTO a Entity
        Book book = bookMapper.toEntity(bookDTO);

        // 3. Generar ID y establecer disponibilidad inicial
        book.setId(IdGeneratorUtil.generateBookId());
        book.setDisponibles(book.getEjemplares());

        // 4. Guardar en el mapa
        books.put(book.getId(), book);

        // 5. Convertir a DTO y retornar
        return bookMapper.toDto(book);
    }

    public List<BookDTO> getAllBooks() {
        return books.values().stream()
                .map(bookMapper::toDto)
                .collect(Collectors.toList());
    }

    public BookDTO getBookById(String id) {
        Book book = books.get(id);
        if (book == null) {
            throw new BookNotAvaliableException("Libro no encontrado con ID: " + id);
        }
        return bookMapper.toDto(book);
    }

    public Book getBookEntityById(String id) {
        Book book = books.get(id);
        if (book == null) {
            throw new BookNotAvaliableException("Libro no encontrado con ID: " + id);
        }
        return book;
    }

    public void updateAvailability(String bookId, int change) {
        Book book = books.get(bookId);
        if (book == null) {
            throw new BookNotAvaliableException("Libro no encontrado con ID: " + bookId);
        }

        int newAvailable = book.getDisponibles() + change;

        if (newAvailable < 0) {
            throw new IllegalArgumentException("No hay suficientes ejemplares disponibles");
        }

        if (newAvailable > book.getEjemplares()) {
            throw new IllegalArgumentException("No se pueden devolver más ejemplares de los que existen");
        }

        book.setDisponibles(newAvailable);
    }
}