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

    private LoanDTO buildFullLoanDTO() {
        return new LoanDTO(
                "L-001",
                "ISBN-001",
                "U-001",
                LocalDate.of(2024, 1, 15),
                LocalDate.of(2024, 2, 15),
                "ACTIVE"
        );
    }

    @Test
    void testNoArgsConstructorCreatesInstance() {
        assertNotNull(loanDTO);
    }

    @Test
    void testAllArgsConstructor() {
        LoanDTO dto = buildFullLoanDTO();
        assertEquals("L-001", dto.getId());
        assertEquals("ISBN-001", dto.getBookId());
        assertEquals("U-001", dto.getUserId());
        assertEquals(LocalDate.of(2024, 1, 15), dto.getLoanDate());
        assertEquals(LocalDate.of(2024, 2, 15), dto.getReturnDate());
        assertEquals("ACTIVE", dto.getStatus());
    }

    @Test
    void testSetAndGetId() {
        loanDTO.setId("L-001");
        assertEquals("L-001", loanDTO.getId());
    }

    @Test
    void testSetAndGetBookId() {
        loanDTO.setBookId("ISBN-001");
        assertEquals("ISBN-001", loanDTO.getBookId());
    }

    @Test
    void testSetAndGetUserId() {
        loanDTO.setUserId("U-001");
        assertEquals("U-001", loanDTO.getUserId());
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
    void testDefaultValuesAreNull() {
        assertNull(loanDTO.getId());
        assertNull(loanDTO.getBookId());
        assertNull(loanDTO.getUserId());
        assertNull(loanDTO.getLoanDate());
        assertNull(loanDTO.getReturnDate());
        assertNull(loanDTO.getStatus());
    }

    @Test
    void testToStringContainsFields() {
        String result = buildFullLoanDTO().toString();
        assertTrue(result.contains("L-001"));
        assertTrue(result.contains("ISBN-001"));
        assertTrue(result.contains("U-001"));
        assertTrue(result.contains("ACTIVE"));
    }

    @Test
    void testEqualsWithSelf() {
        LoanDTO dto = buildFullLoanDTO();
        assertEquals(dto, dto);
    }

    @Test
    void testEqualsWithNull() {
        assertNotEquals(null, buildFullLoanDTO());
    }

    @Test
    void testEqualsWithDifferentClass() {
        assertNotEquals("not a dto", buildFullLoanDTO());
    }

    @Test
    void testEqualsAllFieldsEqual() {
        assertEquals(buildFullLoanDTO(), buildFullLoanDTO());
    }

    @Test
    void testEqualsWithDifferentId() {
        LoanDTO d1 = buildFullLoanDTO();
        LoanDTO d2 = buildFullLoanDTO();
        d2.setId("L-999");
        assertNotEquals(d1, d2);
    }

    @Test
    void testEqualsWithNullIdOnOne() {
        LoanDTO d1 = buildFullLoanDTO();
        LoanDTO d2 = buildFullLoanDTO();
        d1.setId(null);
        assertNotEquals(d1, d2);
    }

    @Test
    void testEqualsWithBothIdsNull() {
        LoanDTO d1 = buildFullLoanDTO();
        LoanDTO d2 = buildFullLoanDTO();
        d1.setId(null);
        d2.setId(null);
        assertEquals(d1, d2);
    }

    @Test
    void testEqualsWithDifferentBookId() {
        LoanDTO d1 = buildFullLoanDTO();
        LoanDTO d2 = buildFullLoanDTO();
        d2.setBookId("ISBN-999");
        assertNotEquals(d1, d2);
    }

    @Test
    void testEqualsWithDifferentUserId() {
        LoanDTO d1 = buildFullLoanDTO();
        LoanDTO d2 = buildFullLoanDTO();
        d2.setUserId("U-999");
        assertNotEquals(d1, d2);
    }

    @Test
    void testEqualsWithDifferentLoanDate() {
        LoanDTO d1 = buildFullLoanDTO();
        LoanDTO d2 = buildFullLoanDTO();
        d2.setLoanDate(LocalDate.of(2023, 6, 1));
        assertNotEquals(d1, d2);
    }

    @Test
    void testEqualsWithDifferentReturnDate() {
        LoanDTO d1 = buildFullLoanDTO();
        LoanDTO d2 = buildFullLoanDTO();
        d2.setReturnDate(LocalDate.of(2023, 7, 1));
        assertNotEquals(d1, d2);
    }

    @Test
    void testEqualsWithDifferentStatus() {
        LoanDTO d1 = buildFullLoanDTO();
        LoanDTO d2 = buildFullLoanDTO();
        d2.setStatus("RETURNED");
        assertNotEquals(d1, d2);
    }

    @Test
    void testEqualsWithNullBookId() {
        LoanDTO d1 = buildFullLoanDTO();
        LoanDTO d2 = buildFullLoanDTO();
        d1.setBookId(null);
        d2.setBookId(null);
        assertEquals(d1, d2);
    }

    @Test
    void testEqualsWithNullUserId() {
        LoanDTO d1 = buildFullLoanDTO();
        LoanDTO d2 = buildFullLoanDTO();
        d1.setUserId(null);
        d2.setUserId(null);
        assertEquals(d1, d2);
    }

    @Test
    void testEqualsWithNullLoanDate() {
        LoanDTO d1 = buildFullLoanDTO();
        LoanDTO d2 = buildFullLoanDTO();
        d1.setLoanDate(null);
        d2.setLoanDate(null);
        assertEquals(d1, d2);
    }

    @Test
    void testEqualsWithNullReturnDate() {
        LoanDTO d1 = buildFullLoanDTO();
        LoanDTO d2 = buildFullLoanDTO();
        d1.setReturnDate(null);
        d2.setReturnDate(null);
        assertEquals(d1, d2);
    }

    @Test
    void testEqualsWithNullStatus() {
        LoanDTO d1 = buildFullLoanDTO();
        LoanDTO d2 = buildFullLoanDTO();
        d1.setStatus(null);
        d2.setStatus(null);
        assertEquals(d1, d2);
    }

    @Test
    void testHashCodeConsistency() {
        assertEquals(buildFullLoanDTO().hashCode(), buildFullLoanDTO().hashCode());
    }

    @Test
    void testHashCodeDiffersWhenIdDiffers() {
        LoanDTO d1 = buildFullLoanDTO();
        LoanDTO d2 = buildFullLoanDTO();
        d2.setId("L-999");
        assertNotEquals(d1.hashCode(), d2.hashCode());
    }

    @Test
    void testHashCodeWithAllNullFields() {
        assertEquals(new LoanDTO().hashCode(), new LoanDTO().hashCode());
    }

    @Test
    void testHashCodeWithNullId() {
        LoanDTO d1 = buildFullLoanDTO();
        LoanDTO d2 = buildFullLoanDTO();
        d1.setId(null);
        d2.setId(null);
        assertEquals(d1.hashCode(), d2.hashCode());
    }

    @Test
    void testHashCodeWithNullBookId() {
        LoanDTO d1 = buildFullLoanDTO();
        LoanDTO d2 = buildFullLoanDTO();
        d1.setBookId(null);
        d2.setBookId(null);
        assertEquals(d1.hashCode(), d2.hashCode());
    }

    @Test
    void testHashCodeWithNullStatus() {
        LoanDTO d1 = buildFullLoanDTO();
        LoanDTO d2 = buildFullLoanDTO();
        d1.setStatus(null);
        d2.setStatus(null);
        assertEquals(d1.hashCode(), d2.hashCode());
    }

    @Test
    void testHashCodeWithNullDates() {
        LoanDTO d1 = buildFullLoanDTO();
        LoanDTO d2 = buildFullLoanDTO();
        d1.setLoanDate(null);
        d2.setLoanDate(null);
        d1.setReturnDate(null);
        d2.setReturnDate(null);
        assertEquals(d1.hashCode(), d2.hashCode());
    }
}
