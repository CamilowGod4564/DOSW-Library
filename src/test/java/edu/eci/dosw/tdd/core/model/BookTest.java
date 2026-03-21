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

    private Book buildFullBook() {
        Book b = new Book();
        b.setId("ISBN-001");
        b.setTitle("Clean Code");
        b.setAuthor("Robert C. Martin");
        b.setEjemplares(5);
        b.setDisponibles(3);
        return b;
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
    void testEjemplaresDefaultValue() {
        assertEquals(0, book.getEjemplares());
    }

    @Test
    void testDisponiblesDefaultValue() {
        assertEquals(0, book.getDisponibles());
    }

    @Test
    void testToStringContainsFields() {
        Book b = buildFullBook();
        String result = b.toString();
        assertTrue(result.contains("ISBN-001"));
        assertTrue(result.contains("Clean Code"));
        assertTrue(result.contains("Robert C. Martin"));
    }

    @Test
    void testEqualsWithSelf() {
        Book b = buildFullBook();
        assertEquals(b, b);
    }

    @Test
    void testEqualsWithNull() {
        assertNotEquals(null, buildFullBook());
    }

    @Test
    void testEqualsWithDifferentClass() {
        assertNotEquals("not a book", buildFullBook());
    }

    @Test
    void testEqualsAllFieldsEqual() {
        assertEquals(buildFullBook(), buildFullBook());
    }

    @Test
    void testEqualsWithDifferentId() {
        Book b1 = buildFullBook();
        Book b2 = buildFullBook();
        b2.setId("ISBN-999");
        assertNotEquals(b1, b2);
    }

    @Test
    void testEqualsWithNullIdOnOne() {
        Book b1 = buildFullBook();
        Book b2 = buildFullBook();
        b1.setId(null);
        assertNotEquals(b1, b2);
    }

    @Test
    void testEqualsWithBothIdsNull() {
        Book b1 = buildFullBook();
        Book b2 = buildFullBook();
        b1.setId(null);
        b2.setId(null);
        assertEquals(b1, b2);
    }

    @Test
    void testEqualsWithDifferentTitle() {
        Book b1 = buildFullBook();
        Book b2 = buildFullBook();
        b2.setTitle("Refactoring");
        assertNotEquals(b1, b2);
    }

    @Test
    void testEqualsWithDifferentAuthor() {
        Book b1 = buildFullBook();
        Book b2 = buildFullBook();
        b2.setAuthor("Martin Fowler");
        assertNotEquals(b1, b2);
    }

    @Test
    void testEqualsWithDifferentEjemplares() {
        Book b1 = buildFullBook();
        Book b2 = buildFullBook();
        b2.setEjemplares(10);
        assertNotEquals(b1, b2);
    }

    @Test
    void testEqualsWithDifferentDisponibles() {
        Book b1 = buildFullBook();
        Book b2 = buildFullBook();
        b2.setDisponibles(1);
        assertNotEquals(b1, b2);
    }

    @Test
    void testEqualsWithNullTitle() {
        Book b1 = buildFullBook();
        Book b2 = buildFullBook();
        b1.setTitle(null);
        b2.setTitle(null);
        assertEquals(b1, b2);
    }

    @Test
    void testEqualsWithNullAuthor() {
        Book b1 = buildFullBook();
        Book b2 = buildFullBook();
        b1.setAuthor(null);
        b2.setAuthor(null);
        assertEquals(b1, b2);
    }

    @Test
    void testHashCodeConsistency() {
        assertEquals(buildFullBook().hashCode(), buildFullBook().hashCode());
    }

    @Test
    void testHashCodeDiffersWhenIdDiffers() {
        Book b1 = buildFullBook();
        Book b2 = buildFullBook();
        b2.setId("ISBN-999");
        assertNotEquals(b1.hashCode(), b2.hashCode());
    }

    @Test
    void testHashCodeWithAllNullFields() {
        assertEquals(new Book().hashCode(), new Book().hashCode());
    }

    @Test
    void testHashCodeWithNullId() {
        Book b1 = buildFullBook();
        Book b2 = buildFullBook();
        b1.setId(null);
        b2.setId(null);
        assertEquals(b1.hashCode(), b2.hashCode());
    }

    @Test
    void testHashCodeWithNullTitle() {
        Book b1 = buildFullBook();
        Book b2 = buildFullBook();
        b1.setTitle(null);
        b2.setTitle(null);
        assertEquals(b1.hashCode(), b2.hashCode());
    }

    @Test
    void testHashCodeWithNullAuthor() {
        Book b1 = buildFullBook();
        Book b2 = buildFullBook();
        b1.setAuthor(null);
        b2.setAuthor(null);
        assertEquals(b1.hashCode(), b2.hashCode());
    }
}
