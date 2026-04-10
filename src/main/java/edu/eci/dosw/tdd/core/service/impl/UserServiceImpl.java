package edu.eci.dosw.tdd.core.service.impl;


import edu.eci.dosw.tdd.controller.dto.UserDTO;
import edu.eci.dosw.tdd.controller.dto.UserResponseDTO;
import edu.eci.dosw.tdd.core.model.User;
import edu.eci.dosw.tdd.core.service.UserService;
import edu.eci.dosw.tdd.persistence.mapper.UserMapper;
import edu.eci.dosw.tdd.persistence.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService {


    private UserRepository userRepository;
    private UserMapper userMapper;

    @Override

    @Transactional
    public UserResponseDTO createUser(UserDTO userDTO) {
        if (userRepository.existsByUsername(userDTO.getUsername())) {
            throw new IllegalArgumentException("El username ya está en uso");
        }
        if (userRepository.existsByEmail(userDTO.getEmail())) {
            throw new IllegalArgumentException("El email ya está en uso");
        }

        User user = userMapper.toEntity(userDTO);
        User saved = userRepository.save(user);

        log.info("Libro agregado exitosamente con ID: {}", saved.getId());
        return userMapper.toResponse(saved);
    }

    @Override
    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(u -> userMapper.toResponse(u))
                .toList();
    }

    @Override
    public UserResponseDTO getUserById(String id) {
        Long userId = Long.parseLong(id);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado con id: " + id));
        return userMapper.toResponse(user);
    }
}