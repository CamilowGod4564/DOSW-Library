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

    private BookDTO buildFullBookDTO() {
        return new BookDTO("ISBN-001", "Clean Code", "Robert C. Martin", 5, 3);
    }

    @Test
    void testNoArgsConstructorCreatesInstance() {
        assertNotNull(bookDTO);
    }

    @Test
    void testAllArgsConstructor() {
        BookDTO dto = buildFullBookDTO();
        assertEquals("ISBN-001", dto.getId());
        assertEquals("Clean Code", dto.getTitle());
        assertEquals("Robert C. Martin", dto.getAuthor());
        assertEquals(5, dto.getEjemplares());
        assertEquals(3, dto.getDisponible());
    }

    @Test
    void testSetAndGetId() {
        bookDTO.setId("ISBN-001");
        assertEquals("ISBN-001", bookDTO.getId());
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
    void testDefaultValuesAreNull() {
        assertNull(bookDTO.getId());
        assertNull(bookDTO.getTitle());
        assertNull(bookDTO.getAuthor());
        assertNull(bookDTO.getEjemplares());
        assertNull(bookDTO.getDisponible());
    }

    @Test
    void testToStringContainsFields() {
        String result = buildFullBookDTO().toString();
        assertTrue(result.contains("ISBN-001"));
        assertTrue(result.contains("Clean Code"));
        assertTrue(result.contains("Robert C. Martin"));
    }

    @Test
    void testEqualsWithSelf() {
        BookDTO dto = buildFullBookDTO();
        assertEquals(dto, dto);
    }

    @Test
    void testEqualsWithNull() {
        assertNotEquals(null, buildFullBookDTO());
    }

    @Test
    void testEqualsWithDifferentClass() {
        assertNotEquals("not a dto", buildFullBookDTO());
    }

    @Test
    void testEqualsAllFieldsEqual() {
        assertEquals(buildFullBookDTO(), buildFullBookDTO());
    }

    @Test
    void testEqualsWithDifferentId() {
        BookDTO d1 = buildFullBookDTO();
        BookDTO d2 = buildFullBookDTO();
        d2.setId("ISBN-999");
        assertNotEquals(d1, d2);
    }

    @Test
    void testEqualsWithNullIdOnOne() {
        BookDTO d1 = buildFullBookDTO();
        BookDTO d2 = buildFullBookDTO();
        d1.setId(null);
        assertNotEquals(d1, d2);
    }

    @Test
    void testEqualsWithBothIdsNull() {
        BookDTO d1 = buildFullBookDTO();
        BookDTO d2 = buildFullBookDTO();
        d1.setId(null);
        d2.setId(null);
        assertEquals(d1, d2);
    }

    @Test
    void testEqualsWithDifferentTitle() {
        BookDTO d1 = buildFullBookDTO();
        BookDTO d2 = buildFullBookDTO();
        d2.setTitle("Refactoring");
        assertNotEquals(d1, d2);
    }

    @Test
    void testEqualsWithDifferentAuthor() {
        BookDTO d1 = buildFullBookDTO();
        BookDTO d2 = buildFullBookDTO();
        d2.setAuthor("Martin Fowler");
        assertNotEquals(d1, d2);
    }

    @Test
    void testEqualsWithDifferentEjemplares() {
        BookDTO d1 = buildFullBookDTO();
        BookDTO d2 = buildFullBookDTO();
        d2.setEjemplares(10);
        assertNotEquals(d1, d2);
    }

    @Test
    void testEqualsWithDifferentDisponible() {
        BookDTO d1 = buildFullBookDTO();
        BookDTO d2 = buildFullBookDTO();
        d2.setDisponible(1);
        assertNotEquals(d1, d2);
    }

    @Test
    void testEqualsWithNullTitle() {
        BookDTO d1 = buildFullBookDTO();
        BookDTO d2 = buildFullBookDTO();
        d1.setTitle(null);
        d2.setTitle(null);
        assertEquals(d1, d2);
    }

    @Test
    void testEqualsWithNullAuthor() {
        BookDTO d1 = buildFullBookDTO();
        BookDTO d2 = buildFullBookDTO();
        d1.setAuthor(null);
        d2.setAuthor(null);
        assertEquals(d1, d2);
    }

    @Test
    void testHashCodeConsistency() {
        assertEquals(buildFullBookDTO().hashCode(), buildFullBookDTO().hashCode());
    }

    @Test
    void testHashCodeDiffersWhenIdDiffers() {
        BookDTO d1 = buildFullBookDTO();
        BookDTO d2 = buildFullBookDTO();
        d2.setId("ISBN-999");
        assertNotEquals(d1.hashCode(), d2.hashCode());
    }

    @Test
    void testHashCodeWithAllNullFields() {
        assertEquals(new BookDTO().hashCode(), new BookDTO().hashCode());
    }

    @Test
    void testHashCodeWithNullId() {
        BookDTO d1 = buildFullBookDTO();
        BookDTO d2 = buildFullBookDTO();
        d1.setId(null);
        d2.setId(null);
        assertEquals(d1.hashCode(), d2.hashCode());
    }

    @Test
    void testHashCodeWithNullTitle() {
        BookDTO d1 = buildFullBookDTO();
        BookDTO d2 = buildFullBookDTO();
        d1.setTitle(null);
        d2.setTitle(null);
        assertEquals(d1.hashCode(), d2.hashCode());
    }

    @Test
    void testHashCodeWithNullAuthor() {
        BookDTO d1 = buildFullBookDTO();
        BookDTO d2 = buildFullBookDTO();
        d1.setAuthor(null);
        d2.setAuthor(null);
        assertEquals(d1.hashCode(), d2.hashCode());
    }
}
