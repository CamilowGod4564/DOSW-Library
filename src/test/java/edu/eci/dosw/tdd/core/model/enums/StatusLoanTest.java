package edu.eci.dosw.tdd.core.model.enums;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StatusLoanTest {

    @Test
    void testEnumHasTwoValues() {
        assertEquals(2, StatusLoan.values().length);
    }

    @Test
    void testActiveValueExists() {
        assertNotNull(StatusLoan.valueOf("ACTIVE"));
        assertEquals(StatusLoan.ACTIVE, StatusLoan.valueOf("ACTIVE"));
    }

    @Test
    void testReturnedValueExists() {
        assertNotNull(StatusLoan.valueOf("RETURNED"));
        assertEquals(StatusLoan.RETURNED, StatusLoan.valueOf("RETURNED"));
    }

    @Test
    void testActiveOrdinal() {
        assertEquals(0, StatusLoan.ACTIVE.ordinal());
    }

    @Test
    void testReturnedOrdinal() {
        assertEquals(1, StatusLoan.RETURNED.ordinal());
    }

    @Test
    void testActiveToString() {
        assertEquals("ACTIVE", StatusLoan.ACTIVE.name());
    }

    @Test
    void testReturnedToString() {
        assertEquals("RETURNED", StatusLoan.RETURNED.name());
    }

    @Test
    void testValuesOrder() {
        StatusLoan[] values = StatusLoan.values();
        assertEquals(StatusLoan.ACTIVE, values[0]);
        assertEquals(StatusLoan.RETURNED, values[1]);
    }

    @Test
    void testInvalidValueThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> StatusLoan.valueOf("PENDING"));
    }

    @Test
    void testEnumEquality() {
        assertEquals(StatusLoan.ACTIVE, StatusLoan.ACTIVE);
        assertNotEquals(StatusLoan.ACTIVE, StatusLoan.RETURNED);
    }
}
