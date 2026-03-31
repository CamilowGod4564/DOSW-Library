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

    private UserDTO buildFullUserDTO() {
        return new UserDTO("U-001", "John Doe");
    }

    @Test
    void testNoArgsConstructorCreatesInstance() {
        assertNotNull(userDTO);
    }

    @Test
    void testAllArgsConstructor() {
        UserDTO dto = buildFullUserDTO();
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
    void testDefaultValuesAreNull() {
        assertNull(userDTO.getId());
        assertNull(userDTO.getName());
    }

    @Test
    void testToStringContainsFields() {
        String result = buildFullUserDTO().toString();
        assertTrue(result.contains("U-001"));
        assertTrue(result.contains("John Doe"));
    }

    @Test
    void testEqualsWithSelf() {
        UserDTO dto = buildFullUserDTO();
        assertEquals(dto, dto);
    }

    @Test
    void testEqualsWithNull() {
        assertNotEquals(null, buildFullUserDTO());
    }

    @Test
    void testEqualsWithDifferentClass() {
        assertNotEquals("not a dto", buildFullUserDTO());
    }

    @Test
    void testEqualsAllFieldsEqual() {
        assertEquals(buildFullUserDTO(), buildFullUserDTO());
    }

    @Test
    void testEqualsWithDifferentId() {
        UserDTO d1 = buildFullUserDTO();
        UserDTO d2 = buildFullUserDTO();
        d2.setId("U-999");
        assertNotEquals(d1, d2);
    }

    @Test
    void testEqualsWithNullIdOnOne() {
        UserDTO d1 = buildFullUserDTO();
        UserDTO d2 = buildFullUserDTO();
        d1.setId(null);
        assertNotEquals(d1, d2);
    }

    @Test
    void testEqualsWithBothIdsNull() {
        UserDTO d1 = buildFullUserDTO();
        UserDTO d2 = buildFullUserDTO();
        d1.setId(null);
        d2.setId(null);
        assertEquals(d1, d2);
    }

    @Test
    void testEqualsWithDifferentName() {
        UserDTO d1 = buildFullUserDTO();
        UserDTO d2 = buildFullUserDTO();
        d2.setName("Jane Doe");
        assertNotEquals(d1, d2);
    }

    @Test
    void testEqualsWithNullName() {
        UserDTO d1 = buildFullUserDTO();
        UserDTO d2 = buildFullUserDTO();
        d1.setName(null);
        d2.setName(null);
        assertEquals(d1, d2);
    }

    @Test
    void testHashCodeConsistency() {
        assertEquals(buildFullUserDTO().hashCode(), buildFullUserDTO().hashCode());
    }

    @Test
    void testHashCodeDiffersWhenIdDiffers() {
        UserDTO d1 = buildFullUserDTO();
        UserDTO d2 = buildFullUserDTO();
        d2.setId("U-999");
        assertNotEquals(d1.hashCode(), d2.hashCode());
    }

    @Test
    void testHashCodeWithAllNullFields() {
        assertEquals(new UserDTO().hashCode(), new UserDTO().hashCode());
    }

    @Test
    void testHashCodeWithNullId() {
        UserDTO d1 = buildFullUserDTO();
        UserDTO d2 = buildFullUserDTO();
        d1.setId(null);
        d2.setId(null);
        assertEquals(d1.hashCode(), d2.hashCode());
    }

    @Test
    void testHashCodeWithNullName() {
        UserDTO d1 = buildFullUserDTO();
        UserDTO d2 = buildFullUserDTO();
        d1.setName(null);
        d2.setName(null);
        assertEquals(d1.hashCode(), d2.hashCode());
    }
}
