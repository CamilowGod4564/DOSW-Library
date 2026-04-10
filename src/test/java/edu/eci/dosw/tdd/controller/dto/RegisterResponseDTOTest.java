package edu.eci.dosw.tdd.controller.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RegisterResponseDTOTest {

    @Test
    void constructor_debeEstablecerTodosLosCampos() {
        RegisterResponseDTO dto = new RegisterResponseDTO(1L, "juanperez", "Juan Pérez", "USER", "Registro exitoso");

        assertEquals(1L, dto.getUserId());
        assertEquals("juanperez", dto.getUsername());
        assertEquals("Juan Pérez", dto.getName());
        assertEquals("USER", dto.getRole());
        assertEquals("Registro exitoso", dto.getMessage());
    }

    @Test
    void gettersYSetters_debenFuncionarCorrectamente() {
        RegisterResponseDTO dto = new RegisterResponseDTO(1L, "u", "n", "r", "m");
        dto.setUserId(2L);
        dto.setUsername("newUser");
        dto.setName("New Name");
        dto.setRole("ADMIN");
        dto.setMessage("Nuevo mensaje");

        assertEquals(2L, dto.getUserId());
        assertEquals("newUser", dto.getUsername());
        assertEquals("New Name", dto.getName());
        assertEquals("ADMIN", dto.getRole());
        assertEquals("Nuevo mensaje", dto.getMessage());
    }
}
