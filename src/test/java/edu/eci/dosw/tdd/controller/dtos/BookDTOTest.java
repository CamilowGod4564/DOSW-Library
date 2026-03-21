package edu.eci.dosw.tdd.controller.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookDTOTest {

    private BookDTO bookDTO;

    @BeforeEach
    void setUp() {
        bookDTO = new BookDTO();
    }

    @Test
    void testNoArgsConstructorCreatesInstance() {
        assertNotNull(bookDTO);
    }

    @Test
    void testAllArgsConstructor() {
        BookDTO dto = new BookDTO("1", "Clean Code", "Robert C. Martin", 5, 3);
        assertEquals("1", dto.getId());
        assertEquals("Clean Code", dto.getTitle());
        assertEquals("Robert C. Martin", dto.getAuthor());
        assertEquals(5, dto.getEjemplares());
        assertEquals(3, dto.getDisponible());
    }

    @Test
    void testSetAndGetId() {
        bookDTO.setId("1");
        assertEquals("1", bookDTO.getId());
    }

    @Test
    void testSetAndGetTitle() {
        bookDTO.setTitle("Clean Code");
        assertEquals("Clean Code", bookDTO.getTitle());
    }

    @Test
    void testSetAndGetAuthor() {
        bookDTO.setAuthor("Robert C. Martin");
        assertEquals("Robert C. Martin", bookDTO.getAuthor());
    }

    @Test
    void testSetAndGetEjemplares() {
        bookDTO.setEjemplares(5);
        assertEquals(5, bookDTO.getEjemplares());
    }

    @Test
    void testSetAndGetDisponible() {
        bookDTO.setDisponible(3);
        assertEquals(3, bookDTO.getDisponible());
    }

    @Test
    void testEqualsWithSameValues() {
        BookDTO dto1 = new BookDTO("1", "Clean Code", "Robert C. Martin", 5, 3);
        BookDTO dto2 = new BookDTO("1", "Clean Code", "Robert C. Martin", 5, 3);
        assertEquals(dto1, dto2);
    }

    @Test
    void testEqualsWithDifferentValues() {
        BookDTO dto1 = new BookDTO("1", "Clean Code", "Robert C. Martin", 5, 3);
        BookDTO dto2 = new BookDTO("2", "Refactoring", "Martin Fowler", 2, 1);
        assertNotEquals(dto1, dto2);
    }

    @Test
    void testEqualsWithSelf() {
        BookDTO dto = new BookDTO("1", "Clean Code", "Robert C. Martin", 5, 3);
        assertEquals(dto, dto);
    }

    @Test
    void testEqualsWithNull() {
        BookDTO dto = new BookDTO("1", "Clean Code", "Robert C. Martin", 5, 3);
        assertNotEquals(null, dto);
    }

    @Test
    void testHashCodeConsistency() {
        BookDTO dto1 = new BookDTO("1", "Clean Code", "Robert C. Martin", 5, 3);
        BookDTO dto2 = new BookDTO("1", "Clean Code", "Robert C. Martin", 5, 3);
        assertEquals(dto1.hashCode(), dto2.hashCode());
    }

    @Test
    void testToStringContainsFields() {
        BookDTO dto = new BookDTO("1", "Clean Code", "Robert C. Martin", 5, 3);
        String result = dto.toString();
        assertTrue(result.contains("1"));
        assertTrue(result.contains("Clean Code"));
        assertTrue(result.contains("Robert C. Martin"));
        assertTrue(result.contains("5"));
        assertTrue(result.contains("3"));
    }

    @Test
    void testDefaultValuesAreNull() {
        assertNull(bookDTO.getId());
        assertNull(bookDTO.getTitle());
        assertNull(bookDTO.getAuthor());
        assertNull(bookDTO.getEjemplares());
        assertNull(bookDTO.getDisponible());
    }
}
