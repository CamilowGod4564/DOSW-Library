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

    private Loan buildFullLoan() {
        Book book = new Book();
        book.setId("ISBN-001");
        book.setTitle("Clean Code");

        User user = new User();
        user.setId("U-001");
        user.setName("John Doe");

        Loan l = new Loan();
        l.setId("L-001");
        l.setBookId("ISBN-001");
        l.setUserId("U-001");
        l.setBook(book);
        l.setUser(user);
        l.setLoanDate(LocalDate.of(2024, 1, 15));
        l.setReturnDate(LocalDate.of(2024, 2, 15));
        l.setStatus(StatusLoan.ACTIVE);
        return l;
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
    void testEqualsWithSelf() {
        Loan l = buildFullLoan();
        assertEquals(l, l);
    }

    @Test
    void testEqualsWithNull() {
        assertNotEquals(null, buildFullLoan());
    }

    @Test
    void testEqualsWithDifferentClass() {
        assertNotEquals("not a loan", buildFullLoan());
    }

    @Test
    void testEqualsAllFieldsEqual() {
        Loan l1 = buildFullLoan();
        Loan l2 = buildFullLoan();
        assertEquals(l1, l2);
    }

    @Test
    void testEqualsWithDifferentId() {
        Loan l1 = buildFullLoan();
        Loan l2 = buildFullLoan();
        l2.setId("L-999");
        assertNotEquals(l1, l2);
    }

    @Test
    void testEqualsWithNullIdOnOne() {
        Loan l1 = buildFullLoan();
        Loan l2 = buildFullLoan();
        l1.setId(null);
        assertNotEquals(l1, l2);
    }

    @Test
    void testEqualsWithBothIdsNull() {
        Loan l1 = buildFullLoan();
        Loan l2 = buildFullLoan();
        l1.setId(null);
        l2.setId(null);
        assertEquals(l1, l2);
    }

    @Test
    void testEqualsWithDifferentBookId() {
        Loan l1 = buildFullLoan();
        Loan l2 = buildFullLoan();
        l2.setBookId("ISBN-999");
        assertNotEquals(l1, l2);
    }

    @Test
    void testEqualsWithDifferentUserId() {
        Loan l1 = buildFullLoan();
        Loan l2 = buildFullLoan();
        l2.setUserId("U-999");
        assertNotEquals(l1, l2);
    }

    @Test
    void testEqualsWithDifferentBook() {
        Loan l1 = buildFullLoan();
        Loan l2 = buildFullLoan();
        Book otherBook = new Book();
        otherBook.setId("ISBN-999");
        l2.setBook(otherBook);
        assertNotEquals(l1, l2);
    }

    @Test
    void testEqualsWithDifferentUser() {
        Loan l1 = buildFullLoan();
        Loan l2 = buildFullLoan();
        User otherUser = new User();
        otherUser.setId("U-999");
        l2.setUser(otherUser);
        assertNotEquals(l1, l2);
    }

    @Test
    void testEqualsWithDifferentLoanDate() {
        Loan l1 = buildFullLoan();
        Loan l2 = buildFullLoan();
        l2.setLoanDate(LocalDate.of(2023, 6, 1));
        assertNotEquals(l1, l2);
    }

    @Test
    void testEqualsWithDifferentReturnDate() {
        Loan l1 = buildFullLoan();
        Loan l2 = buildFullLoan();
        l2.setReturnDate(LocalDate.of(2023, 7, 1));
        assertNotEquals(l1, l2);
    }

    @Test
    void testEqualsWithDifferentStatus() {
        Loan l1 = buildFullLoan();
        Loan l2 = buildFullLoan();
        l2.setStatus(StatusLoan.RETURNED);
        assertNotEquals(l1, l2);
    }

    @Test
    void testEqualsWithNullBook() {
        Loan l1 = buildFullLoan();
        Loan l2 = buildFullLoan();
        l1.setBook(null);
        l2.setBook(null);
        assertEquals(l1, l2);
    }

    @Test
    void testEqualsWithNullUser() {
        Loan l1 = buildFullLoan();
        Loan l2 = buildFullLoan();
        l1.setUser(null);
        l2.setUser(null);
        assertEquals(l1, l2);
    }

    @Test
    void testHashCodeConsistency() {
        Loan l1 = buildFullLoan();
        Loan l2 = buildFullLoan();
        assertEquals(l1.hashCode(), l2.hashCode());
    }

    @Test
    void testHashCodeDiffersWhenIdDiffers() {
        Loan l1 = buildFullLoan();
        Loan l2 = buildFullLoan();
        l2.setId("L-999");
        assertNotEquals(l1.hashCode(), l2.hashCode());
    }

    @Test
    void testHashCodeWithAllNullFields() {
        Loan l1 = new Loan();
        Loan l2 = new Loan();
        assertEquals(l1.hashCode(), l2.hashCode());
    }

    @Test
    void testHashCodeWithNullId() {
        Loan l1 = buildFullLoan();
        Loan l2 = buildFullLoan();
        l1.setId(null);
        l2.setId(null);
        assertEquals(l1.hashCode(), l2.hashCode());
    }

    @Test
    void testHashCodeWithNullBook() {
        Loan l1 = buildFullLoan();
        Loan l2 = buildFullLoan();
        l1.setBook(null);
        l2.setBook(null);
        assertEquals(l1.hashCode(), l2.hashCode());
    }
}
