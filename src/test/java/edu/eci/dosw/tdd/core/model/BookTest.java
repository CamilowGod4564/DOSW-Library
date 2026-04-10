package edu.eci.dosw.tdd.core.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class BookTest {

    private Book book;

    @BeforeEach
    void setUp() {
        book = new Book();
    }

    @Test
    void testBookConstructorAndSetters() {
        Long expectedId = 1L;
        String expectedTitle = "El Quijote";
        String expectedAuthor = "Miguel de Cervantes";

        book.setId(expectedId);
        book.setTitle(expectedTitle);
        book.setAuthor(expectedAuthor);

        assertEquals(expectedId, book.getId());
        assertEquals(expectedTitle, book.getTitle());
        assertEquals(expectedAuthor, book.getAuthor());
    }

    @Test
    void testBookDefaultValues() {
        assertNotNull(book.getLoans());
        assertTrue(book.getLoans().isEmpty());
    }

    @Test
    void testBookWithLoans() {
        Loan loan1 = new Loan();
        Loan loan2 = new Loan();
        List<Loan> loans = new ArrayList<>();
        loans.add(loan1);
        loans.add(loan2);

        book.setLoans(loans);

        assertEquals(2, book.getLoans().size());
        assertTrue(book.getLoans().contains(loan1));
        assertTrue(book.getLoans().contains(loan2));
    }

    @Test
    void testBookNullValues() {
        book.setId(null);
        book.setTitle(null);
        book.setAuthor(null);

        assertNull(book.getId());
        assertNull(book.getTitle());
        assertNull(book.getAuthor());
    }

    @Test
    void testBookEquality() {
        Book book1 = new Book();
        Book book2 = new Book();

        book1.setId(1L);
        book2.setId(1L);

        book1.setTitle("Same Title");
        book2.setTitle("Same Title");

        assertEquals(book1.getTitle(), book2.getTitle());
        assertEquals(book1.getId(), book2.getId());
    }


    @Test
    void testEqualsAndHashCode() {
        Book book1 = new Book();
        book1.setId(1L);
        book1.setTitle("Same Title");
        book1.setAuthor("Same Author");

        Book book2 = new Book();
        book2.setId(1L);
        book2.setTitle("Same Title");
        book2.setAuthor("Same Author");

        Book book3 = new Book();
        book3.setId(2L);
        book3.setTitle("Different");
        book3.setAuthor("Different");

        assertEquals(book1, book2);
        assertEquals(book1.hashCode(), book2.hashCode());
        assertNotEquals(book1, book3);
    }

    @Test
    void testToString() {
        Book book = new Book();
        book.setId(1L);
        book.setTitle("Test Book");
        book.setAuthor("Test Author");

        String toString = book.toString();

        assertNotNull(toString);
        assertTrue(toString.contains("Test Book"));
        assertTrue(toString.contains("Test Author"));
    }

    @Test
    void testEqualsWithNullId() {
        Book book1 = new Book();
        Book book2 = new Book();

        book1.setTitle("Same");
        book2.setTitle("Same");

        assertEquals(book1, book2);

        book1.setId(1L);
        assertNotEquals(book1, book2);
    }
}