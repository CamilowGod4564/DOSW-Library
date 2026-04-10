package edu.eci.dosw.tdd.controller.dto;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class RegisterRequestDTOTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void gettersYSetters_debenFuncionarCorrectamente() {
        RegisterRequestDTO dto = new RegisterRequestDTO();
        dto.setUsername("juanperez");
        dto.setName("Juan Pérez");
        dto.setPassword("secret123");

        assertEquals("juanperez", dto.getUsername());
        assertEquals("Juan Pérez", dto.getName());
        assertEquals("secret123", dto.getPassword());
    }

    @Test
    void validacion_debePasar_cuandoDatosValidos() {
        RegisterRequestDTO dto = new RegisterRequestDTO();
        dto.setUsername("juanperez");
        dto.setName("Juan Pérez");
        dto.setPassword("secret123");

        Set<ConstraintViolation<RegisterRequestDTO>> violations = validator.validate(dto);
        assertTrue(violations.isEmpty());
    }

    @Test
    void validacion_debeFallar_cuandoUsernameEsBlanco() {
        RegisterRequestDTO dto = new RegisterRequestDTO();
        dto.setUsername("");
        dto.setName("Juan Pérez");
        dto.setPassword("secret123");

        Set<ConstraintViolation<RegisterRequestDTO>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("username")));
    }

    @Test
    void validacion_debeFallar_cuandoUsernameMenorDe3Caracteres() {
        RegisterRequestDTO dto = new RegisterRequestDTO();
        dto.setUsername("ab");
        dto.setName("Juan Pérez");
        dto.setPassword("secret123");

        Set<ConstraintViolation<RegisterRequestDTO>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("username")));
    }

    @Test
    void validacion_debeFallar_cuandoNombreEsBlanco() {
        RegisterRequestDTO dto = new RegisterRequestDTO();
        dto.setUsername("juanperez");
        dto.setName("");
        dto.setPassword("secret123");

        Set<ConstraintViolation<RegisterRequestDTO>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("name")));
    }

    @Test
    void validacion_debeFallar_cuandoPasswordMenorDe6Caracteres() {
        RegisterRequestDTO dto = new RegisterRequestDTO();
        dto.setUsername("juanperez");
        dto.setName("Juan Pérez");
        dto.setPassword("abc");

        Set<ConstraintViolation<RegisterRequestDTO>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("password")));
    }

    @Test
    void validacion_debeFallar_cuandoPasswordEsNulo() {
        RegisterRequestDTO dto = new RegisterRequestDTO();
        dto.setUsername("juanperez");
        dto.setName("Juan Pérez");

        Set<ConstraintViolation<RegisterRequestDTO>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("password")));
    }
}
