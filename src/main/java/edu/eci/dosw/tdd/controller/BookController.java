package edu.eci.dosw.tdd.controller;


import edu.eci.dosw.tdd.controller.dto.BookDTO;
import edu.eci.dosw.tdd.controller.dto.BookResponseDTO;
import edu.eci.dosw.tdd.core.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;


    @PostMapping
    public BookResponseDTO addBook(BookDTO bookDTO){
        return null;
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
