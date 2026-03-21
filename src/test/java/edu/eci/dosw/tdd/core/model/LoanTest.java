package edu.eci.dosw.tdd.core.model;

import edu.eci.dosw.tdd.core.model.enums.StatusLoan;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class LoanTest {

    private Loan loan;

    @BeforeEach
    void setUp() {
        loan = new Loan();
    }

    @Test
    void testSetAndGetId() {
        loan.setId("L-001");
        assertEquals("L-001", loan.getId());
    }

    @Test
    void testSetAndGetBookId() {
        loan.setBookId("ISBN-001");
        assertEquals("ISBN-001", loan.getBookId());
    }

    @Test
    void testSetAndGetUserId() {
        loan.setUserId("U-001");
        assertEquals("U-001", loan.getUserId());
    }

    @Test
    void testSetAndGetBook() {
        Book book = new Book();
        book.setId("ISBN-001");
        book.setTitle("Clean Code");

        loan.setBook(book);
        assertNotNull(loan.getBook());
        assertEquals("ISBN-001", loan.getBook().getId());
        assertEquals("Clean Code", loan.getBook().getTitle());
    }

    @Test
    void testSetAndGetUser() {
        User user = new User();
        user.setId("U-001");
        user.setName("John Doe");

        loan.setUser(user);
        assertNotNull(loan.getUser());
        assertEquals("U-001", loan.getUser().getId());
        assertEquals("John Doe", loan.getUser().getName());
    }

    @Test
    void testSetAndGetLoanDate() {
        LocalDate date = LocalDate.of(2024, 1, 15);
        loan.setLoanDate(date);
        assertEquals(date, loan.getLoanDate());
    }

    @Test
    void testSetAndGetReturnDate() {
        LocalDate date = LocalDate.of(2024, 2, 15);
        loan.setReturnDate(date);
        assertEquals(date, loan.getReturnDate());
    }

    @Test
    void testSetAndGetStatusActive() {
        loan.setStatus(StatusLoan.ACTIVE);
        assertEquals(StatusLoan.ACTIVE, loan.getStatus());
    }

    @Test
    void testSetAndGetStatusReturned() {
        loan.setStatus(StatusLoan.RETURNED);
        assertEquals(StatusLoan.RETURNED, loan.getStatus());
    }

    @Test
    void testEqualsWithSameValues() {
        LocalDate loanDate = LocalDate.of(2024, 1, 15);
        LocalDate returnDate = LocalDate.of(2024, 2, 15);

        Loan loan1 = new Loan();
        loan1.setId("L-001");
        loan1.setBookId("ISBN-001");
        loan1.setUserId("U-001");
        loan1.setLoanDate(loanDate);
        loan1.setReturnDate(returnDate);
        loan1.setStatus(StatusLoan.ACTIVE);

        Loan loan2 = new Loan();
        loan2.setId("L-001");
        loan2.setBookId("ISBN-001");
        loan2.setUserId("U-001");
        loan2.setLoanDate(loanDate);
        loan2.setReturnDate(returnDate);
        loan2.setStatus(StatusLoan.ACTIVE);

        assertEquals(loan1, loan2);
    }

    @Test
    void testEqualsWithDifferentIds() {
        Loan loan1 = new Loan();
        loan1.setId("L-001");

        Loan loan2 = new Loan();
        loan2.setId("L-002");

        assertNotEquals(loan1, loan2);
    }

    @Test
    void testHashCodeConsistency() {
        Loan loan1 = new Loan();
        loan1.setId("L-001");
        loan1.setBookId("ISBN-001");

        Loan loan2 = new Loan();
        loan2.setId("L-001");
        loan2.setBookId("ISBN-001");

        assertEquals(loan1.hashCode(), loan2.hashCode());
    }

    @Test
    void testToStringContainsFields() {
        loan.setId("L-001");
        loan.setBookId("ISBN-001");
        loan.setUserId("U-001");
        loan.setStatus(StatusLoan.ACTIVE);

        String result = loan.toString();
        assertTrue(result.contains("L-001"));
        assertTrue(result.contains("ISBN-001"));
        assertTrue(result.contains("U-001"));
        assertTrue(result.contains("ACTIVE"));
    }

    @Test
    void testDefaultValuesAreNull() {
        assertNull(loan.getId());
        assertNull(loan.getBookId());
        assertNull(loan.getUserId());
        assertNull(loan.getBook());
        assertNull(loan.getUser());
        assertNull(loan.getLoanDate());
        assertNull(loan.getReturnDate());
        assertNull(loan.getStatus());
    }

    @Test
    void testEqualsWithNull() {
        loan.setId("L-001");
        assertNotEquals(null, loan);
    }

    @Test
    void testEqualsWithSelf() {
        loan.setId("L-001");
        assertEquals(loan, loan);
    }
}
