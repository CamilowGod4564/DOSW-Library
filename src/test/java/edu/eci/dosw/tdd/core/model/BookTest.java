package edu.eci.dosw.tdd.core.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookTest {

    private Book book;

    @BeforeEach
    void setUp() {
        book = new Book();
    }

    @Test
    void testSetAndGetTitle() {
        book.setTitle("Clean Code");
        assertEquals("Clean Code", book.getTitle());
    }

    @Test
    void testSetAndGetAuthor() {
        book.setAuthor("Robert C. Martin");
        assertEquals("Robert C. Martin", book.getAuthor());
    }

    @Test
    void testSetAndGetId() {
        book.setId("ISBN-001");
        assertEquals("ISBN-001", book.getId());
    }

    @Test
    void testSetAndGetEjemplares() {
        book.setEjemplares(5);
        assertEquals(5, book.getEjemplares());
    }

    @Test
    void testSetAndGetDisponibles() {
        book.setDisponibles(3);
        assertEquals(3, book.getDisponibles());
    }

    @Test
    void testEqualsWithSameValues() {
        Book book1 = new Book();
        book1.setTitle("Clean Code");
        book1.setAuthor("Robert C. Martin");
        book1.setId("ISBN-001");
        book1.setEjemplares(5);
        book1.setDisponibles(3);

        Book book2 = new Book();
        book2.setTitle("Clean Code");
        book2.setAuthor("Robert C. Martin");
        book2.setId("ISBN-001");
        book2.setEjemplares(5);
        book2.setDisponibles(3);

        assertEquals(book1, book2);
    }

    @Test
    void testEqualsWithDifferentValues() {
        Book book1 = new Book();
        book1.setTitle("Clean Code");

        Book book2 = new Book();
        book2.setTitle("The Pragmatic Programmer");

        assertNotEquals(book1, book2);
    }

    @Test
    void testHashCodeConsistency() {
        Book book1 = new Book();
        book1.setTitle("Clean Code");
        book1.setId("ISBN-001");

        Book book2 = new Book();
        book2.setTitle("Clean Code");
        book2.setId("ISBN-001");

        assertEquals(book1.hashCode(), book2.hashCode());
    }

    @Test
    void testToStringContainsFields() {
        book.setTitle("Clean Code");
        book.setAuthor("Robert C. Martin");
        book.setId("ISBN-001");

        String result = book.toString();
        assertTrue(result.contains("Clean Code"));
        assertTrue(result.contains("Robert C. Martin"));
        assertTrue(result.contains("ISBN-001"));
    }

    @Test
    void testEjemplareDefaultValue() {
        assertEquals(0, book.getEjemplares());
    }

    @Test
    void testDisponiblesDefaultValue() {
        assertEquals(0, book.getDisponibles());
    }

    @Test
    void testEqualsWithNull() {
        book.setTitle("Clean Code");
        assertNotEquals(null, book);
    }

    @Test
    void testEqualsWithSelf() {
        book.setTitle("Clean Code");
        assertEquals(book, book);
    }
}
