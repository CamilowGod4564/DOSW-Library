package edu.eci.dosw.tdd.controller.dto;

import edu.eci.dosw.tdd.core.model.enums.Role;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class UserDTOTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void gettersYSetters_debenFuncionarCorrectamente() {
        UserDTO dto = new UserDTO();
        dto.setName("Juan Pérez");
        dto.setUsername("juanperez");
        dto.setPassword("secret123");
        dto.setEmail("juan@example.com");
        dto.setRole(Role.USER);

        assertEquals("Juan Pérez", dto.getName());
        assertEquals("juanperez", dto.getUsername());
        assertEquals("secret123", dto.getPassword());
        assertEquals("juan@example.com", dto.getEmail());
        assertEquals(Role.USER, dto.getRole());
    }

    @Test
    void validacion_debePasar_cuandoDatosValidos() {
        UserDTO dto = new UserDTO();
        dto.setName("Juan Pérez");
        dto.setUsername("juanperez");
        dto.setPassword("secret123");
        dto.setEmail("juan@example.com");
        dto.setRole(Role.USER);

        Set<ConstraintViolation<UserDTO>> violations = validator.validate(dto);
        assertTrue(violations.isEmpty());
    }

    @Test
    void validacion_debeFallar_cuandoNombreEsBlanco() {
        UserDTO dto = new UserDTO();
        dto.setName("");
        dto.setUsername("juanperez");
        dto.setPassword("secret123");
        dto.setEmail("juan@example.com");

        Set<ConstraintViolation<UserDTO>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("name")));
    }

    @Test
    void validacion_debeFallar_cuandoUsernameEsBlanco() {
        UserDTO dto = new UserDTO();
        dto.setName("Juan Pérez");
        dto.setUsername("");
        dto.setPassword("secret123");
        dto.setEmail("juan@example.com");

        Set<ConstraintViolation<UserDTO>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("username")));
    }

    @Test
    void validacion_debeFallar_cuandoPasswordEsBlanco() {
        UserDTO dto = new UserDTO();
        dto.setName("Juan Pérez");
        dto.setUsername("juanperez");
        dto.setPassword("");
        dto.setEmail("juan@example.com");

        Set<ConstraintViolation<UserDTO>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("password")));
    }

    @Test
    void validacion_debeFallar_cuandoEmailEsInvalido() {
        UserDTO dto = new UserDTO();
        dto.setName("Juan Pérez");
        dto.setUsername("juanperez");
        dto.setPassword("secret123");
        dto.setEmail("emailinvalido");

        Set<ConstraintViolation<UserDTO>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("email")));
    }

    @Test
    void validacion_debeFallar_cuandoEmailEsBlanco() {
        UserDTO dto = new UserDTO();
        dto.setName("Juan Pérez");
        dto.setUsername("juanperez");
        dto.setPassword("secret123");
        dto.setEmail("");

        Set<ConstraintViolation<UserDTO>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("email")));
    }
}
