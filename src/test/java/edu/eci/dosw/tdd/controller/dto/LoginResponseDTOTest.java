package edu.eci.dosw.tdd.controller.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoginResponseDTOTest {

    @Test
    void constructor_debeEstablecerTodosLosCampos() {
        LoginResponseDTO dto = new LoginResponseDTO("token123", "juanperez", "Juan Pérez", "USER", 1L);

        assertEquals("token123", dto.getToken());
        assertEquals("juanperez", dto.getUsername());
        assertEquals("Juan Pérez", dto.getName());
        assertEquals("USER", dto.getRole());
        assertEquals(1L, dto.getUserId());
    }

    @Test
    void gettersYSetters_debenFuncionarCorrectamente() {
        LoginResponseDTO dto = new LoginResponseDTO("t", "u", "n", "r", 1L);
        dto.setToken("newToken");
        dto.setUsername("newUser");
        dto.setName("New Name");
        dto.setRole("ADMIN");
        dto.setUserId(2L);

        assertEquals("newToken", dto.getToken());
        assertEquals("newUser", dto.getUsername());
        assertEquals("New Name", dto.getName());
        assertEquals("ADMIN", dto.getRole());
        assertEquals(2L, dto.getUserId());
    }
}
