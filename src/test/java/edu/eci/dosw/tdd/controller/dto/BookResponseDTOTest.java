package edu.eci.dosw.tdd.controller.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookResponseDTOTest {

    @Test
    void gettersYSetters_debenFuncionarCorrectamente() {
        BookResponseDTO dto = new BookResponseDTO();
        dto.setId(1L);
        dto.setTitle("Clean Code");
        dto.setAuthor("Robert C. Martin");

        assertEquals(1L, dto.getId());
        assertEquals("Clean Code", dto.getTitle());
        assertEquals("Robert C. Martin", dto.getAuthor());
    }

    @Test
    void camposNulos_cuandoNoSeEstablecen() {
        BookResponseDTO dto = new BookResponseDTO();

        assertNull(dto.getId());
        assertNull(dto.getTitle());
        assertNull(dto.getAuthor());
    }
}
