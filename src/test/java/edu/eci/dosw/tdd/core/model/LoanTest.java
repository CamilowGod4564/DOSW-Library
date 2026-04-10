package edu.eci.dosw.tdd.core.model;

import edu.eci.dosw.tdd.core.model.enums.Status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;

class LoanTest {

    private Loan loan;
    private User user;
    private Book book;

    @BeforeEach
    void setUp() {
        loan = new Loan();
        user = new User();
        book = new Book();

        user.setId(1L);
        user.setUsername("testuser");
        book.setId(1L);
        book.setTitle("Test Book");
    }

    @Test
    void testLoanConstructorAndSetters() {
        Long expectedId = 1L;
        Date expectedLoanDate = new Date();
        Date expectedReturnDate = new Date();
        Status expectedStatus = Status.ACTIVE;

        loan.setId(expectedId);
        loan.setUser(user);
        loan.setBook(book);
        loan.setStatus(expectedStatus);
        loan.setLoanDate(expectedLoanDate);
        loan.setReturnDate(expectedReturnDate);

        assertEquals(expectedId, loan.getId());
        assertEquals(user, loan.getUser());
        assertEquals(book, loan.getBook());
        assertEquals(expectedStatus, loan.getStatus());
        assertEquals(expectedLoanDate, loan.getLoanDate());
        assertEquals(expectedReturnDate, loan.getReturnDate());
    }

    @Test
    void testLoanRelationships() {
        loan.setUser(user);
        loan.setBook(book);

        assertEquals(user, loan.getUser());
        assertEquals(book, loan.getBook());
        assertEquals(user.getId(), loan.getUser().getId());
        assertEquals(book.getId(), loan.getBook().getId());
    }

    @Test
    void testLoanStatusEnum() {
        loan.setStatus(Status.ACTIVE);
        assertEquals(Status.ACTIVE, loan.getStatus());

        loan.setStatus(Status.RETURNED);
        assertEquals(Status.RETURNED, loan.getStatus());

        loan.setStatus(Status.ACTIVE);
        assertEquals(Status.ACTIVE, loan.getStatus());
    }

    @Test
    void testLoanDates() {
        Date now = new Date();
        Date future = new Date(now.getTime() + 86400000);

        loan.setLoanDate(now);
        loan.setReturnDate(future);

        assertEquals(now, loan.getLoanDate());
        assertEquals(future, loan.getReturnDate());
        assertTrue(loan.getReturnDate().after(loan.getLoanDate()));
    }

    @Test
    void testLoanNullValues() {
        loan.setId(null);
        loan.setUser(null);
        loan.setBook(null);
        loan.setStatus(null);
        loan.setLoanDate(null);
        loan.setReturnDate(null);

        assertNull(loan.getId());
        assertNull(loan.getUser());
        assertNull(loan.getBook());
        assertNull(loan.getStatus());
        assertNull(loan.getLoanDate());
        assertNull(loan.getReturnDate());
    }

    @Test
    void testEqualsAndHashCode() {
        Loan loan1 = new Loan();
        loan1.setId(1L);
        loan1.setStatus(Status.ACTIVE);

        Loan loan2 = new Loan();
        loan2.setId(1L);
        loan2.setStatus(Status.ACTIVE);

        Loan loan3 = new Loan();
        loan3.setId(2L);
        loan3.setStatus(Status.RETURNED);

        assertEquals(loan1, loan2);
        assertEquals(loan1.hashCode(), loan2.hashCode());
        assertNotEquals(loan1, loan3);
    }

    @Test
    void testToString() {
        Loan loan = new Loan();
        loan.setId(1L);
        loan.setStatus(Status.ACTIVE);
        Date now = new Date();
        loan.setLoanDate(now);

        String toString = loan.toString();

        assertNotNull(toString);
        assertTrue(toString.contains("ACTIVE"));
        assertTrue(toString.contains(now.toString()));
    }
}