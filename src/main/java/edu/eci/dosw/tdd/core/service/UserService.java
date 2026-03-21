package edu.eci.dosw.tdd.core.service;

import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import edu.eci.dosw.tdd.controller.dto.UserDTO;
import edu.eci.dosw.tdd.core.model.User;
import edu.eci.dosw.tdd.core.exception.UserNotFoundException;
import edu.eci.dosw.tdd.controller.mapper.UserMapper;
import edu.eci.dosw.tdd.core.validator.UserValidator;
import edu.eci.dosw.tdd.core.util.IdGeneratorUtil;
import java.util.stream.Collectors;

@Service
public class UserService {
    private Map<String, User> users = new HashMap<>();

    private final UserMapper userMapper;
    private final UserValidator userValidator;

    public UserService(UserMapper userMapper, UserValidator userValidator) {
        this.userMapper = userMapper;
        this.userValidator = userValidator;
    }

    public UserDTO registerUser(UserDTO userDTO) {
        userValidator.validateCreate(userDTO);

        User user = userMapper.toEntity(userDTO);

        user.setId(IdGeneratorUtil.generateUserId());

        user.setLoansActivos(new ArrayList<>());

        users.put(user.getId(), user);

        return userMapper.toDto(user);
    }

    public List<UserDTO> getAllUsers() {
        return users.values().stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }

    public UserDTO getUserById(String id) {
        User user = users.get(id);
        if (user == null) {
            throw new UserNotFoundException("Usuario no encontrado con ID: " + id);
        }
        return userMapper.toDto(user);
    }

    public User getUserEntityById(String id) {
        User user = users.get(id);
        if (user == null) {
            throw new UserNotFoundException("Usuario no encontrado con ID: " + id);
        }
        return user;
    }
}
