package edu.eci.dosw.tdd.controller.dto;

import edu.eci.dosw.tdd.core.model.enums.Status;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class LoanResponseDTOTest {

    @Test
    void gettersYSetters_debenFuncionarCorrectamente() {
        Date loanDate = new Date();
        Date returnDate = new Date();

        LoanResponseDTO dto = new LoanResponseDTO();
        dto.setId(10L);
        dto.setBookId(1L);
        dto.setUserId(2L);
        dto.setLoanDate(loanDate);
        dto.setReturnDate(returnDate);
        dto.setStatus(Status.RETURNED);

        assertEquals(10L, dto.getId());
        assertEquals(1L, dto.getBookId());
        assertEquals(2L, dto.getUserId());
        assertEquals(loanDate, dto.getLoanDate());
        assertEquals(returnDate, dto.getReturnDate());
        assertEquals(Status.RETURNED, dto.getStatus());
    }

    @Test
    void camposNulos_cuandoNoSeEstablecen() {
        LoanResponseDTO dto = new LoanResponseDTO();

        assertNull(dto.getId());
        assertNull(dto.getBookId());
        assertNull(dto.getUserId());
        assertNull(dto.getLoanDate());
        assertNull(dto.getReturnDate());
        assertNull(dto.getStatus());
    }
}
