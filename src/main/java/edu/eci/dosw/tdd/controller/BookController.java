package edu.eci.dosw.tdd.controller;


import edu.eci.dosw.tdd.controller.dto.BookDTO;
import edu.eci.dosw.tdd.controller.dto.BookResponseDTO;
import edu.eci.dosw.tdd.core.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;




    @PostMapping
    @Operation(summary = "Agregar un nuevo libro")
    public ResponseEntity<BookResponseDTO> addBook(@Valid @RequestBody BookDTO bookDTO) {
        BookResponseDTO response = bookService.addBook(bookDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public List<BookResponseDTO> getAllBooks(){
        return null;
    }

    @GetMapping("/{id}")
    public BookResponseDTO getBookById(String bookId){
        return null;
    }

    @PutMapping("/{id}")
    public BookResponseDTO updateBook(BookDTO bookDTO){
        return null;
    }

}
