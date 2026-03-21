package edu.eci.dosw.tdd.core.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
    }

    private User buildFullUser() {
        User u = new User();
        u.setId("U-001");
        u.setName("John Doe");
        u.setLoansActivos(new ArrayList<>());
        return u;
    }

    @Test
    void testSetAndGetName() {
        user.setName("John Doe");
        assertEquals("John Doe", user.getName());
    }

    @Test
    void testSetAndGetId() {
        user.setId("U-001");
        assertEquals("U-001", user.getId());
    }

    @Test
    void testSetAndGetLoansActivos() {
        List<Loan> loans = new ArrayList<>();
        Loan loan = new Loan();
        loan.setId("L-001");
        loans.add(loan);

        user.setLoansActivos(loans);
        assertEquals(1, user.getLoansActivos().size());
        assertEquals("L-001", user.getLoansActivos().get(0).getId());
    }

    @Test
    void testLoansActivosDefaultNull() {
        assertNull(user.getLoansActivos());
    }

    @Test
    void testSetEmptyLoansList() {
        user.setLoansActivos(new ArrayList<>());
        assertNotNull(user.getLoansActivos());
        assertTrue(user.getLoansActivos().isEmpty());
    }

    @Test
    void testToStringContainsFields() {
        User u = buildFullUser();
        String result = u.toString();
        assertTrue(result.contains("U-001"));
        assertTrue(result.contains("John Doe"));
    }

    @Test
    void testEqualsWithSelf() {
        User u = buildFullUser();
        assertEquals(u, u);
    }

    @Test
    void testEqualsWithNull() {
        assertNotEquals(null, buildFullUser());
    }

    @Test
    void testEqualsWithDifferentClass() {
        assertNotEquals("not a user", buildFullUser());
    }

    @Test
    void testEqualsAllFieldsEqual() {
        assertEquals(buildFullUser(), buildFullUser());
    }

    @Test
    void testEqualsWithDifferentId() {
        User u1 = buildFullUser();
        User u2 = buildFullUser();
        u2.setId("U-999");
        assertNotEquals(u1, u2);
    }

    @Test
    void testEqualsWithNullIdOnOne() {
        User u1 = buildFullUser();
        User u2 = buildFullUser();
        u1.setId(null);
        assertNotEquals(u1, u2);
    }

    @Test
    void testEqualsWithBothIdsNull() {
        User u1 = buildFullUser();
        User u2 = buildFullUser();
        u1.setId(null);
        u2.setId(null);
        assertEquals(u1, u2);
    }

    @Test
    void testEqualsWithDifferentName() {
        User u1 = buildFullUser();
        User u2 = buildFullUser();
        u2.setName("Jane Doe");
        assertNotEquals(u1, u2);
    }

    @Test
    void testEqualsWithNullName() {
        User u1 = buildFullUser();
        User u2 = buildFullUser();
        u1.setName(null);
        u2.setName(null);
        assertEquals(u1, u2);
    }

    @Test
    void testEqualsWithDifferentLoansActivos() {
        User u1 = buildFullUser();
        User u2 = buildFullUser();
        Loan loan = new Loan();
        loan.setId("L-001");
        u2.getLoansActivos().add(loan);
        assertNotEquals(u1, u2);
    }

    @Test
    void testEqualsWithNullLoansActivos() {
        User u1 = buildFullUser();
        User u2 = buildFullUser();
        u1.setLoansActivos(null);
        u2.setLoansActivos(null);
        assertEquals(u1, u2);
    }

    @Test
    void testHashCodeConsistency() {
        assertEquals(buildFullUser().hashCode(), buildFullUser().hashCode());
    }

    @Test
    void testHashCodeDiffersWhenIdDiffers() {
        User u1 = buildFullUser();
        User u2 = buildFullUser();
        u2.setId("U-999");
        assertNotEquals(u1.hashCode(), u2.hashCode());
    }

    @Test
    void testHashCodeWithAllNullFields() {
        assertEquals(new User().hashCode(), new User().hashCode());
    }

    @Test
    void testHashCodeWithNullId() {
        User u1 = buildFullUser();
        User u2 = buildFullUser();
        u1.setId(null);
        u2.setId(null);
        assertEquals(u1.hashCode(), u2.hashCode());
    }

    @Test
    void testHashCodeWithNullName() {
        User u1 = buildFullUser();
        User u2 = buildFullUser();
        u1.setName(null);
        u2.setName(null);
        assertEquals(u1.hashCode(), u2.hashCode());
    }

    @Test
    void testHashCodeWithNullLoansActivos() {
        User u1 = buildFullUser();
        User u2 = buildFullUser();
        u1.setLoansActivos(null);
        u2.setLoansActivos(null);
        assertEquals(u1.hashCode(), u2.hashCode());
    }
}
