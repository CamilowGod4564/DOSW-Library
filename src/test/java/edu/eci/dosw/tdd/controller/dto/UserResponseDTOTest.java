package edu.eci.dosw.tdd.controller.dto;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserResponseDTOTest {

    @Test
    void gettersYSetters_debenFuncionarCorrectamente() {
        LoanResponseDTO loan = new LoanResponseDTO();
        loan.setId(1L);

        UserResponseDTO dto = new UserResponseDTO();
        dto.setId(1L);
        dto.setName("Juan Pérez");
        dto.setUsername("juanperez");
        dto.setEmail("juan@example.com");
        dto.setLoans(List.of(loan));

        assertEquals(1L, dto.getId());
        assertEquals("Juan Pérez", dto.getName());
        assertEquals("juanperez", dto.getUsername());
        assertEquals("juan@example.com", dto.getEmail());
        assertEquals(1, dto.getLoans().size());
        assertEquals(1L, dto.getLoans().get(0).getId());
    }

    @Test
    void camposNulos_cuandoNoSeEstablecen() {
        UserResponseDTO dto = new UserResponseDTO();

        assertNull(dto.getId());
        assertNull(dto.getName());
        assertNull(dto.getUsername());
        assertNull(dto.getEmail());
        assertNull(dto.getLoans());
    }

    @Test
    void loans_puedeSerListaVacia() {
        UserResponseDTO dto = new UserResponseDTO();
        dto.setLoans(List.of());

        assertNotNull(dto.getLoans());
        assertTrue(dto.getLoans().isEmpty());
    }
}
