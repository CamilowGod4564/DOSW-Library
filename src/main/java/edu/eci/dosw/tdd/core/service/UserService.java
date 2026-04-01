package edu.eci.dosw.tdd.core.service;

import edu.eci.dosw.tdd.controller.dto.UserDTO;
import edu.eci.dosw.tdd.controller.dto.UserResponseDTO;

import java.util.List;

public interface UserService {
    public UserResponseDTO createUser(UserDTO userDTO);
    public List<UserResponseDTO> getAllUsers();
    public UserResponseDTO getUserById(String id);
}
