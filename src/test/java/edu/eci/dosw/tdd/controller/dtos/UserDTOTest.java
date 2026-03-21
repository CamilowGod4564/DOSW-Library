package edu.eci.dosw.tdd.controller.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserDTOTest {

    private UserDTO userDTO;

    @BeforeEach
    void setUp() {
        userDTO = new UserDTO();
    }

    @Test
    void testNoArgsConstructorCreatesInstance() {
        assertNotNull(userDTO);
    }

    @Test
    void testAllArgsConstructor() {
        UserDTO dto = new UserDTO("U-001", "John Doe");
        assertEquals("U-001", dto.getId());
        assertEquals("John Doe", dto.getName());
    }

    @Test
    void testSetAndGetId() {
        userDTO.setId("U-001");
        assertEquals("U-001", userDTO.getId());
    }

    @Test
    void testSetAndGetName() {
        userDTO.setName("John Doe");
        assertEquals("John Doe", userDTO.getName());
    }

    @Test
    void testEqualsWithSameValues() {
        UserDTO dto1 = new UserDTO("U-001", "John Doe");
        UserDTO dto2 = new UserDTO("U-001", "John Doe");
        assertEquals(dto1, dto2);
    }

    @Test
    void testEqualsWithDifferentValues() {
        UserDTO dto1 = new UserDTO("U-001", "John Doe");
        UserDTO dto2 = new UserDTO("U-002", "Jane Doe");
        assertNotEquals(dto1, dto2);
    }

    @Test
    void testEqualsWithSelf() {
        UserDTO dto = new UserDTO("U-001", "John Doe");
        assertEquals(dto, dto);
    }

    @Test
    void testEqualsWithNull() {
        UserDTO dto = new UserDTO("U-001", "John Doe");
        assertNotEquals(null, dto);
    }

    @Test
    void testHashCodeConsistency() {
        UserDTO dto1 = new UserDTO("U-001", "John Doe");
        UserDTO dto2 = new UserDTO("U-001", "John Doe");
        assertEquals(dto1.hashCode(), dto2.hashCode());
    }

    @Test
    void testToStringContainsFields() {
        UserDTO dto = new UserDTO("U-001", "John Doe");
        String result = dto.toString();
        assertTrue(result.contains("U-001"));
        assertTrue(result.contains("John Doe"));
    }

    @Test
    void testDefaultValuesAreNull() {
        assertNull(userDTO.getId());
        assertNull(userDTO.getName());
    }
}
