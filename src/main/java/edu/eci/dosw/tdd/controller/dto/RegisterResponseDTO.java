package edu.eci.dosw.tdd.controller.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegisterResponseDTO {
    private Long userId;
    private String username;
    private String name;
    private String role;
    private String message;
}