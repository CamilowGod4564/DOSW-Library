package edu.eci.dosw.tdd.controller.dto;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class LoginRequestDTOTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void gettersYSetters_debenFuncionarCorrectamente() {
        LoginRequestDTO dto = new LoginRequestDTO();
        dto.setUsername("juanperez");
        dto.setPassword("secret123");

        assertEquals("juanperez", dto.getUsername());
        assertEquals("secret123", dto.getPassword());
    }

    @Test
    void validacion_debePasar_cuandoDatosValidos() {
        LoginRequestDTO dto = new LoginRequestDTO();
        dto.setUsername("juanperez");
        dto.setPassword("secret123");

        Set<ConstraintViolation<LoginRequestDTO>> violations = validator.validate(dto);
        assertTrue(violations.isEmpty());
    }

    @Test
    void validacion_debeFallar_cuandoUsernameEsBlanco() {
        LoginRequestDTO dto = new LoginRequestDTO();
        dto.setUsername("");
        dto.setPassword("secret123");

        Set<ConstraintViolation<LoginRequestDTO>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("username")));
    }

    @Test
    void validacion_debeFallar_cuandoUsernameEsNulo() {
        LoginRequestDTO dto = new LoginRequestDTO();
        dto.setPassword("secret123");

        Set<ConstraintViolation<LoginRequestDTO>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("username")));
    }

    @Test
    void validacion_debeFallar_cuandoPasswordEsBlanco() {
        LoginRequestDTO dto = new LoginRequestDTO();
        dto.setUsername("juanperez");
        dto.setPassword("");

        Set<ConstraintViolation<LoginRequestDTO>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("password")));
    }

    @Test
    void validacion_debeFallar_cuandoPasswordEsNulo() {
        LoginRequestDTO dto = new LoginRequestDTO();
        dto.setUsername("juanperez");

        Set<ConstraintViolation<LoginRequestDTO>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("password")));
    }
}
