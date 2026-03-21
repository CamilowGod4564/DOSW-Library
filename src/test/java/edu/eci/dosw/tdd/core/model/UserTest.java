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
    void testEqualsWithSameValues() {
        User user1 = new User();
        user1.setName("John Doe");
        user1.setId("U-001");
        user1.setLoansActivos(new ArrayList<>());

        User user2 = new User();
        user2.setName("John Doe");
        user2.setId("U-001");
        user2.setLoansActivos(new ArrayList<>());

        assertEquals(user1, user2);
    }

    @Test
    void testEqualsWithDifferentValues() {
        User user1 = new User();
        user1.setId("U-001");

        User user2 = new User();
        user2.setId("U-002");

        assertNotEquals(user1, user2);
    }

    @Test
    void testHashCodeConsistency() {
        User user1 = new User();
        user1.setName("John Doe");
        user1.setId("U-001");

        User user2 = new User();
        user2.setName("John Doe");
        user2.setId("U-001");

        assertEquals(user1.hashCode(), user2.hashCode());
    }

    @Test
    void testToStringContainsFields() {
        user.setName("John Doe");
        user.setId("U-001");

        String result = user.toString();
        assertTrue(result.contains("John Doe"));
        assertTrue(result.contains("U-001"));
    }

    @Test
    void testEqualsWithNull() {
        user.setId("U-001");
        assertNotEquals(null, user);
    }

    @Test
    void testEqualsWithSelf() {
        user.setId("U-001");
        assertEquals(user, user);
    }

    @Test
    void testSetEmptyLoansList() {
        user.setLoansActivos(new ArrayList<>());
        assertNotNull(user.getLoansActivos());
        assertTrue(user.getLoansActivos().isEmpty());
    }
}
