package edu.eci.dosw.tdd.controller;

import edu.eci.dosw.tdd.controller.dto.UserDTO;
import edu.eci.dosw.tdd.controller.dto.UserResponseDTO;
import edu.eci.dosw.tdd.core.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private UserService userService;


    @PostMapping
    public UserResponseDTO createUser(UserDTO userDTO){
        return null;
    }

    @GetMapping
    public List<UserResponseDTO> getAllUsers(){
        return null;
    }

    @GetMapping("/{id}")
    public UserResponseDTO getUserById(String id){
        return null;
    }
}
