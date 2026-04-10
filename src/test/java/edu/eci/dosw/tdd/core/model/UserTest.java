package edu.eci.dosw.tdd.core.model;

import edu.eci.dosw.tdd.core.model.enums.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
    }

    @Test
    void testUserConstructorAndSetters() {
        Long expectedId = 1L;
        String expectedUsername = "john_doe";
        String expectedName = "John Doe";
        String expectedPassword = "password123";
        Role expectedRole = Role.USER;
        String expectedEmail = "john@example.com";
        String expectedProvider = "LOCAL";
        String expectedProviderId = "google123";

        user.setId(expectedId);
        user.setUsername(expectedUsername);
        user.setName(expectedName);
        user.setPassword(expectedPassword);
        user.setRole(expectedRole);
        user.setEmail(expectedEmail);
        user.setProvider(expectedProvider);
        user.setProviderId(expectedProviderId);

        assertEquals(expectedId, user.getId());
        assertEquals(expectedUsername, user.getUsername());
        assertEquals(expectedName, user.getName());
        assertEquals(expectedPassword, user.getPassword());
        assertEquals(expectedRole, user.getRole());
        assertEquals(expectedEmail, user.getEmail());
        assertEquals(expectedProvider, user.getProvider());
        assertEquals(expectedProviderId, user.getProviderId());
    }

    @Test
    void testUserDefaultValues() {
        assertNotNull(user.getLoans());
        assertTrue(user.getLoans().isEmpty());
        assertEquals("LOCAL", user.getProvider());
    }

    @Test
    void testUserWithLoans() {
        Loan loan1 = new Loan();
        Loan loan2 = new Loan();
        List<Loan> loans = new ArrayList<>();
        loans.add(loan1);
        loans.add(loan2);

        user.setLoans(loans);

        assertEquals(2, user.getLoans().size());
        assertTrue(user.getLoans().contains(loan1));
        assertTrue(user.getLoans().contains(loan2));
    }

    @Test
    void testGetAuthorities() {
        user.setRole(Role.USER);
        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();

        assertEquals(1, authorities.size());
        assertTrue(authorities.contains(new SimpleGrantedAuthority("USER")));

        user.setRole(Role.LIBRARIAN);
        authorities = user.getAuthorities();
        assertTrue(authorities.contains(new SimpleGrantedAuthority("LIBRARIAN")));
    }

    @Test
    void testIsGoogleUser() {
        user.setProvider("LOCAL");
        assertFalse(user.isGoogleUser());

        user.setProvider("GOOGLE");
        assertTrue(user.isGoogleUser());
    }

    @Test
    void testUserDetailsMethods() {
        user.setUsername("testuser");
        user.setPassword("password");

        assertEquals("testuser", user.getUsername());
        assertEquals("password", user.getPassword());
        assertTrue(user.isAccountNonExpired());
        assertTrue(user.isAccountNonLocked());
        assertTrue(user.isCredentialsNonExpired());
        assertTrue(user.isEnabled());
    }

    @Test
    void testUserNullValues() {
        user.setId(null);
        user.setUsername(null);
        user.setName(null);
        user.setPassword(null);
        user.setRole(null);
        user.setEmail(null);
        user.setProvider(null);
        user.setProviderId(null);

        assertNull(user.getId());
        assertNull(user.getUsername());
        assertNull(user.getName());
        assertNull(user.getPassword());
        assertNull(user.getRole());
        assertNull(user.getEmail());
        assertNull(user.getProvider());
        assertNull(user.getProviderId());
    }

    @Test
    void testUserEmailUniqueness() {
        User user1 = new User();
        User user2 = new User();

        user1.setEmail("same@example.com");
        user2.setEmail("same@example.com");

        assertEquals(user1.getEmail(), user2.getEmail());
    }

    @Test
    void testUserUsernameUniqueness() {
        User user1 = new User();
        User user2 = new User();

        user1.setUsername("uniqueuser");
        user2.setUsername("uniqueuser");

        assertEquals(user1.getUsername(), user2.getUsername());
    }

    @Test
    void testUserRoleEnum() {
        user.setRole(Role.USER);
        assertEquals(Role.USER, user.getRole());

        user.setRole(Role.LIBRARIAN);
        assertEquals(Role.LIBRARIAN, user.getRole());
    }
    @Test
    void testEqualsAndHashCode() {
        User user1 = new User();
        user1.setId(1L);
        user1.setUsername("testuser");
        user1.setEmail("test@example.com");
        user1.setRole(Role.USER);

        User user2 = new User();
        user2.setId(1L);
        user2.setUsername("testuser");
        user2.setEmail("test@example.com");
        user2.setRole(Role.USER);

        User user3 = new User();
        user3.setId(2L);
        user3.setUsername("different");
        user3.setEmail("different@example.com");
        user3.setRole(Role.USER);

        assertEquals(user1, user2);
        assertEquals(user1.hashCode(), user2.hashCode());
        assertNotEquals(user1, user3);
        assertNotEquals(user1.hashCode(), user3.hashCode());
    }

    @Test
    void testEqualsWithNull() {
        User user = new User();
        user.setId(1L);

        assertNotEquals(null, user);
        assertNotEquals(user, null);
    }

    @Test
    void testEqualsWithDifferentClass() {
        User user = new User();
        user.setId(1L);

        assertNotEquals(user, "not a user");
        assertNotEquals(user, new Object());
    }

    @Test
    void testEqualsWithSameObject() {
        User user = new User();
        user.setId(1L);

        assertEquals(user, user);
    }

    @Test
    void testEqualsWithDifferentId() {
        User user1 = new User();
        User user2 = new User();

        user1.setId(1L);
        user2.setId(2L);

        assertNotEquals(user1, user2);
    }

    @Test
    void testEqualsWithNullId() {
        User user1 = new User();
        User user2 = new User();

        user1.setId(null);
        user2.setId(null);
        user1.setUsername("same");
        user2.setUsername("same");

        assertEquals(user1, user2);

        user1.setId(1L);
        user2.setId(null);

        assertNotEquals(user1, user2);
    }

    @Test
    void testToString() {
        User user = new User();
        user.setId(1L);
        user.setUsername("testuser");
        user.setName("Test User");
        user.setEmail("test@example.com");
        user.setRole(Role.USER);
        user.setProvider("LOCAL");

        String toString = user.toString();

        assertNotNull(toString);
        assertTrue(toString.contains("testuser"));
        assertTrue(toString.contains("Test User"));
        assertTrue(toString.contains("test@example.com"));
        assertTrue(toString.contains("USER"));
        assertTrue(toString.contains("LOCAL"));
    }

    @Test
    void testHashCodeConsistency() {
        User user = new User();
        user.setId(1L);
        user.setUsername("testuser");

        int hashCode1 = user.hashCode();
        int hashCode2 = user.hashCode();

        assertEquals(hashCode1, hashCode2);
    }

    @Test
    void testHashCodeWithNullFields() {
        User user = new User();
        user.setId(null);
        user.setUsername(null);
        user.setEmail(null);

        assertNotNull(user.hashCode());
    }

    @Test
    void testCanEqual() {
        User user = new User();
        User sameType = new User();
        String differentType = "string";

        assertTrue(user.canEqual(sameType));
        assertFalse(user.canEqual(differentType));
        assertFalse(user.canEqual(null));
    }
}