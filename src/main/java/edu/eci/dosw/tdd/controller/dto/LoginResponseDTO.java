package edu.eci.dosw.tdd.controller.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponseDTO {
    private String token;
    private String username;
    private String name;
    private String role;
    private Long userId;
}