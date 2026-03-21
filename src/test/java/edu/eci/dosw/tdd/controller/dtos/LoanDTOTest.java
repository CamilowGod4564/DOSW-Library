package edu.eci.dosw.tdd.controller.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class LoanDTOTest {

    private LoanDTO loanDTO;

    @BeforeEach
    void setUp() {
        loanDTO = new LoanDTO();
    }

    @Test
    void testNoArgsConstructorCreatesInstance() {
        assertNotNull(loanDTO);
    }

    @Test
    void testAllArgsConstructor() {
        LocalDate loanDate = LocalDate.of(2024, 1, 15);
        LocalDate returnDate = LocalDate.of(2024, 2, 15);
        LoanDTO dto = new LoanDTO("L-001", "BOOK-1", "USER-1", loanDate, returnDate, "ACTIVE");

        assertEquals("L-001", dto.getId());
        assertEquals("BOOK-1", dto.getBookId());
        assertEquals("USER-1", dto.getUserId());
        assertEquals(loanDate, dto.getLoanDate());
        assertEquals(returnDate, dto.getReturnDate());
        assertEquals("ACTIVE", dto.getStatus());
    }

    @Test
    void testSetAndGetId() {
        loanDTO.setId("L-001");
        assertEquals("L-001", loanDTO.getId());
    }

    @Test
    void testSetAndGetBookId() {
        loanDTO.setBookId("BOOK-1");
        assertEquals("BOOK-1", loanDTO.getBookId());
    }

    @Test
    void testSetAndGetUserId() {
        loanDTO.setUserId("USER-1");
        assertEquals("USER-1", loanDTO.getUserId());
    }

    @Test
    void testSetAndGetLoanDate() {
        LocalDate date = LocalDate.of(2024, 1, 15);
        loanDTO.setLoanDate(date);
        assertEquals(date, loanDTO.getLoanDate());
    }

    @Test
    void testSetAndGetReturnDate() {
        LocalDate date = LocalDate.of(2024, 2, 15);
        loanDTO.setReturnDate(date);
        assertEquals(date, loanDTO.getReturnDate());
    }

    @Test
    void testSetAndGetStatusActive() {
        loanDTO.setStatus("ACTIVE");
        assertEquals("ACTIVE", loanDTO.getStatus());
    }

    @Test
    void testSetAndGetStatusReturned() {
        loanDTO.setStatus("RETURNED");
        assertEquals("RETURNED", loanDTO.getStatus());
    }

    @Test
    void testEqualsWithSameValues() {
        LocalDate loanDate = LocalDate.of(2024, 1, 15);
        LocalDate returnDate = LocalDate.of(2024, 2, 15);
        LoanDTO dto1 = new LoanDTO("L-001", "BOOK-1", "USER-1", loanDate, returnDate, "ACTIVE");
        LoanDTO dto2 = new LoanDTO("L-001", "BOOK-1", "USER-1", loanDate, returnDate, "ACTIVE");
        assertEquals(dto1, dto2);
    }

    @Test
    void testEqualsWithDifferentValues() {
        LoanDTO dto1 = new LoanDTO("L-001", "BOOK-1", "USER-1", null, null, "ACTIVE");
        LoanDTO dto2 = new LoanDTO("L-002", "BOOK-2", "USER-2", null, null, "RETURNED");
        assertNotEquals(dto1, dto2);
    }

    @Test
    void testEqualsWithSelf() {
        LoanDTO dto = new LoanDTO("L-001", "BOOK-1", "USER-1", null, null, "ACTIVE");
        assertEquals(dto, dto);
    }

    @Test
    void testEqualsWithNull() {
        LoanDTO dto = new LoanDTO("L-001", "BOOK-1", "USER-1", null, null, "ACTIVE");
        assertNotEquals(null, dto);
    }

    @Test
    void testHashCodeConsistency() {
        LocalDate loanDate = LocalDate.of(2024, 1, 15);
        LoanDTO dto1 = new LoanDTO("L-001", "BOOK-1", "USER-1", loanDate, null, "ACTIVE");
        LoanDTO dto2 = new LoanDTO("L-001", "BOOK-1", "USER-1", loanDate, null, "ACTIVE");
        assertEquals(dto1.hashCode(), dto2.hashCode());
    }

    @Test
    void testToStringContainsFields() {
        LocalDate loanDate = LocalDate.of(2024, 1, 15);
        LoanDTO dto = new LoanDTO("L-001", "BOOK-1", "USER-1", loanDate, null, "ACTIVE");
        String result = dto.toString();
        assertTrue(result.contains("L-001"));
        assertTrue(result.contains("BOOK-1"));
        assertTrue(result.contains("USER-1"));
        assertTrue(result.contains("ACTIVE"));
    }

    @Test
    void testDefaultValuesAreNull() {
        assertNull(loanDTO.getId());
        assertNull(loanDTO.getBookId());
        assertNull(loanDTO.getUserId());
        assertNull(loanDTO.getLoanDate());
        assertNull(loanDTO.getReturnDate());
        assertNull(loanDTO.getStatus());
    }
}
