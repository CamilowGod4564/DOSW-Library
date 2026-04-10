package edu.eci.dosw.tdd.controller.dto;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class BookDTOTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void gettersYSetters_debenFuncionarCorrectamente() {
        BookDTO dto = new BookDTO();
        dto.setTitle("Clean Code");
        dto.setAuthor("Robert C. Martin");

        assertEquals("Clean Code", dto.getTitle());
        assertEquals("Robert C. Martin", dto.getAuthor());
    }

    @Test
    void validacion_debePasar_cuandoDatosValidos() {
        BookDTO dto = new BookDTO();
        dto.setTitle("Clean Code");
        dto.setAuthor("Robert C. Martin");

        Set<ConstraintViolation<BookDTO>> violations = validator.validate(dto);
        assertTrue(violations.isEmpty());
    }

    @Test
    void validacion_debeFallar_cuandoTituloEsBlanco() {
        BookDTO dto = new BookDTO();
        dto.setTitle("");
        dto.setAuthor("Robert C. Martin");

        Set<ConstraintViolation<BookDTO>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("title")));
    }

    @Test
    void validacion_debeFallar_cuandoTituloEsNulo() {
        BookDTO dto = new BookDTO();
        dto.setAuthor("Robert C. Martin");

        Set<ConstraintViolation<BookDTO>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("title")));
    }

    @Test
    void validacion_debeFallar_cuandoAutorEsBlanco() {
        BookDTO dto = new BookDTO();
        dto.setTitle("Clean Code");
        dto.setAuthor("");

        Set<ConstraintViolation<BookDTO>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("author")));
    }

    @Test
    void validacion_debeFallar_cuandoAutorEsNulo() {
        BookDTO dto = new BookDTO();
        dto.setTitle("Clean Code");

        Set<ConstraintViolation<BookDTO>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("author")));
    }
}
