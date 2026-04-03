package edu.eci.dosw.tdd.core.service.impl;

import edu.eci.dosw.tdd.controller.dto.BookDTO;
import edu.eci.dosw.tdd.controller.dto.BookResponseDTO;
import edu.eci.dosw.tdd.core.model.Book;
import edu.eci.dosw.tdd.core.service.BookService;
import edu.eci.dosw.tdd.persistence.mapper.BookMapper;
import edu.eci.dosw.tdd.persistence.repository.BookRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;



    @Override
    @Transactional
    public BookResponseDTO addBook(BookDTO bookDTO) {
        log.info("Agregando nuevo libro: título='{}', autor='{}'",
                bookDTO.getTitle(), bookDTO.getAuthor());

        Book book = bookMapper.toEntity(bookDTO);
        Book savedBook = bookRepository.save(book);
        log.info("Libro agregado exitosamente con ID: {}", savedBook.getId());
        return bookMapper.toResponse(savedBook);
    }

    @Override
    public List<BookResponseDTO> getAllBooks() {
        log.info("Obteniendo todos los libros");

        List<Book> books = bookRepository.findAll();

        return bookMapper.toResponseList(books);
    }

    @Override
    public BookResponseDTO getBookById(String bookId) {
        return null;
    }

    @Override
    @Transactional
    public BookResponseDTO updateBook(BookDTO bookDTO) {
        log.info("Actualizando libro");
        throw new UnsupportedOperationException("Usa updateBook(id, bookDTO) en su lugar");
    }

}
