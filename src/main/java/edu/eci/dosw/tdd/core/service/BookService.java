package edu.eci.dosw.tdd.core.service;

import edu.eci.dosw.tdd.controller.dto.BookDTO;
import edu.eci.dosw.tdd.controller.dto.BookResponseDTO;
import edu.eci.dosw.tdd.core.model.Book;

import java.util.List;

public interface BookService {
    public BookResponseDTO addBook(BookDTO bookDTO);
    public List<BookResponseDTO> getAllBooks();
    public BookResponseDTO getBookById(Long bookId);
    public BookResponseDTO updateBook(BookDTO bookDTO);
    void deleteBook(Long id);
}
