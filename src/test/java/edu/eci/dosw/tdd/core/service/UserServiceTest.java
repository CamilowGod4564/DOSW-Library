package edu.eci.dosw.tdd.core.service;

import edu.eci.dosw.tdd.controller.dto.UserDTO;
import edu.eci.dosw.tdd.controller.mapper.UserMapper;
import edu.eci.dosw.tdd.core.exception.UserNotFoundException;
import edu.eci.dosw.tdd.core.model.User;
import edu.eci.dosw.tdd.core.validator.UserValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserMapper userMapper;

    @Mock
    private UserValidator userValidator;

    @InjectMocks
    private UserService userService;

    private UserDTO userDTO;
    private User user;

    @BeforeEach
    void setUp() {
        userDTO = new UserDTO(null, "John Doe");

        user = new User();
        user.setName("John Doe");
    }

    @Test
    void testRegisterUserReturnsDTO() {
        UserDTO expectedDTO = new UserDTO("1", "John Doe");

        when(userMapper.toEntity(userDTO)).thenReturn(user);
        when(userMapper.toDto(any(User.class))).thenReturn(expectedDTO);

        UserDTO result = userService.registerUser(userDTO);

        assertNotNull(result);
        assertEquals("John Doe", result.getName());
    }

    @Test
    void testRegisterUserSetsLoansActivosEmpty() {
        when(userMapper.toEntity(userDTO)).thenReturn(user);
        when(userMapper.toDto(any(User.class))).thenReturn(userDTO);

        userService.registerUser(userDTO);

        assertNotNull(user.getLoansActivos());
        assertTrue(user.getLoansActivos().isEmpty());
    }

    @Test
    void testRegisterUserCallsValidator() {
        when(userMapper.toEntity(userDTO)).thenReturn(user);
        when(userMapper.toDto(any(User.class))).thenReturn(userDTO);

        userService.registerUser(userDTO);

        verify(userValidator, times(1)).validateCreate(userDTO);
    }

    @Test
    void testGetAllUsersReturnsEmptyList() {
        List<UserDTO> result = userService.getAllUsers();
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void testGetAllUsersReturnsAllRegisteredUsers() {
        User user2 = new User();
        user2.setName("Jane Doe");

        UserDTO dto1 = new UserDTO("1", "John Doe");
        UserDTO dto2 = new UserDTO("2", "Jane Doe");
        UserDTO userDTO2 = new UserDTO(null, "Jane Doe");

        when(userMapper.toEntity(userDTO)).thenReturn(user);
        when(userMapper.toEntity(userDTO2)).thenReturn(user2);
        when(userMapper.toDto(user)).thenReturn(dto1);
        when(userMapper.toDto(user2)).thenReturn(dto2);

        userService.registerUser(userDTO);
        userService.registerUser(userDTO2);

        List<UserDTO> result = userService.getAllUsers();
        assertEquals(2, result.size());
    }

    @Test
    void testGetUserByIdReturnsDTO() {
        UserDTO expectedDTO = new UserDTO("1", "John Doe");

        when(userMapper.toEntity(userDTO)).thenReturn(user);
        when(userMapper.toDto(any(User.class))).thenReturn(expectedDTO);

        userService.registerUser(userDTO);
        String generatedId = user.getId();

        UserDTO result = userService.getUserById(generatedId);
        assertNotNull(result);
        assertEquals("John Doe", result.getName());
    }

    @Test
    void testGetUserByIdThrowsWhenNotFound() {
        assertThrows(UserNotFoundException.class, () -> userService.getUserById("nonexistent"));
    }

    @Test
    void testGetUserEntityByIdReturnsUser() {
        when(userMapper.toEntity(userDTO)).thenReturn(user);
        when(userMapper.toDto(any(User.class))).thenReturn(userDTO);

        userService.registerUser(userDTO);
        String generatedId = user.getId();

        User result = userService.getUserEntityById(generatedId);
        assertNotNull(result);
        assertEquals("John Doe", result.getName());
    }

    @Test
    void testGetUserEntityByIdThrowsWhenNotFound() {
        assertThrows(UserNotFoundException.class, () -> userService.getUserEntityById("nonexistent"));
    }

    @Test
    void testRegisteredUserHasGeneratedId() {
        when(userMapper.toEntity(userDTO)).thenReturn(user);
        when(userMapper.toDto(any(User.class))).thenReturn(userDTO);

        userService.registerUser(userDTO);

        assertNotNull(user.getId());
        assertFalse(user.getId().isBlank());
    }
}
