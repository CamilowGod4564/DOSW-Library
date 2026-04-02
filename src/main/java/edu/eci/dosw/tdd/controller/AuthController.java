package edu.eci.dosw.tdd.controller;

import edu.eci.dosw.tdd.core.model.User;
import edu.eci.dosw.tdd.core.service.JwtService;
import edu.eci.dosw.tdd.persistence.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.Set;


@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    // ==================== LOGIN ====================
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@Valid @RequestBody LoginRequestDTO request) {

        // 1. Autenticar al usuario con username y password
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        // 2. Obtener los detalles del usuario autenticado
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        // 3. Obtener el usuario de la base de datos
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // 4. Preparar los roles para el token
        Set<String> roles = new HashSet<>();
        roles.add(user.getRole().name());

        // 5. Generar el token JWT
        String token = jwtService.generateToken(
                user.getId().toString(),
                user.getEmail(),
                roles
        );

        // 6. Devolver la respuesta
        return ResponseEntity.ok(new LoginResponseDTO(
                token,
                user.getUsername(),
                user.getEmail(),
                user.getRole().name(),
                user.getId()
        ));
    }

    // ==================== REGISTRO ====================
    @PostMapping("/register")
    public ResponseEntity<RegisterResponseDTO> register(@Valid @RequestBody RegisterRequestDTO request) {

        // 1. Validar si el username ya existe
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("El username '" + request.getUsername() + "' ya está en uso");
        }

        // 2. Validar si el email ya existe
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("El email '" + request.getEmail() + "' ya está registrado");
        }

        // 3. Crear nuevo usuario
        User newUser = new User();
        newUser.setUsername(request.getUsername());
        newUser.setEmail(request.getEmail());
        newUser.setFullName(request.getFullName());
        newUser.setPassword(passwordEncoder.encode(request.getPassword()));
        newUser.setRole(Role.USER);  // Por defecto, rol USER
        newUser.setEnabled(true);

        // 4. Guardar en la base de datos
        User savedUser = userRepository.save(newUser);

        // 5. Devolver respuesta
        return ResponseEntity.status(HttpStatus.CREATED).body(new RegisterResponseDTO(
                savedUser.getId(),
                savedUser.getUsername(),
                savedUser.getEmail(),
                savedUser.getFullName(),
                savedUser.getRole().name(),
                "Usuario registrado exitosamente"
        ));
    }

    // ==================== VERIFICAR TOKEN (Opcional) ====================
    @GetMapping("/verify")
    public ResponseEntity<?> verifyToken(@RequestHeader("Authorization") String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token no proporcionado");
        }

        String token = authHeader.substring(7);

        if (jwtService.isTokenValid(token)) {
            String userId = jwtService.extractUserId(token);
            String email = jwtService.extractEmail(token);
            Set<String> roles = jwtService.extractRoles(token);

            return ResponseEntity.ok(new TokenVerificationResponse(userId, email, roles, true));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token inválido o expirado");
        }
    }

    // DTO interno para verificación
    record TokenVerificationResponse(String userId, String email, Set<String> roles, boolean valid) {}
}