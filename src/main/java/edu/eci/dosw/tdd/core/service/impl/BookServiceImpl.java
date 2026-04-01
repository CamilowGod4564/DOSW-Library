package edu.eci.dosw.tdd.core.service.impl;

import edu.eci.dosw.tdd.controller.dto.BookDTO;
import edu.eci.dosw.tdd.controller.dto.BookResponseDTO;
import edu.eci.dosw.tdd.core.service.BookService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    @Override
    public BookResponseDTO addBook(BookDTO bookDTO) {
        return null;
    }

    @Override
    public List<BookResponseDTO> getAllBooks() {
        return List.of();
    }

    @Override
    public BookResponseDTO getBookById(String bookId) {
        return null;
    }

    @Override
    public BookResponseDTO updateBook(BookDTO bookDTO) {
        return null;
    }
}
