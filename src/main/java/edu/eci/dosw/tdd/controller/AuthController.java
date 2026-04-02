package edu.eci.dosw.tdd.controller;


import edu.eci.dosw.tdd.controller.dto.LoginRequestDTO;
import edu.eci.dosw.tdd.controller.dto.LoginResponseDTO;
import edu.eci.dosw.tdd.controller.dto.RegisterRequestDTO;
import edu.eci.dosw.tdd.controller.dto.RegisterResponseDTO;
import edu.eci.dosw.tdd.core.model.User;
import edu.eci.dosw.tdd.core.model.enums.Role;
import edu.eci.dosw.tdd.core.service.JwtService;
import edu.eci.dosw.tdd.persistence.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@Valid @RequestBody LoginRequestDTO request) {

        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("Usuario o contraseña incorrectos"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Usuario o contraseña incorrectos");
        }

        if (!user.isEnabled()) {
            throw new RuntimeException("Usuario deshabilitado");
        }

        String token = jwtService.generateToken(
                user.getId(),
                user.getUsername(),
                Set.of(user.getRole().name())
        );

        return ResponseEntity.ok(new LoginResponseDTO(
                token,
                user.getUsername(),
                user.getName(),
                user.getRole().name(),
                user.getId()
        ));
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponseDTO> register(@Valid @RequestBody RegisterRequestDTO request) {

        if (userRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("El username '" + request.getUsername() + "' ya está en uso");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setName(request.getName());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.USER);  // Por defecto, rol USER

        User savedUser = userRepository.save(user);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new RegisterResponseDTO(
                        savedUser.getId(),
                        savedUser.getUsername(),
                        savedUser.getName(),
                        savedUser.getRole().name(),
                        "Usuario registrado exitosamente"
                ));
    }
}