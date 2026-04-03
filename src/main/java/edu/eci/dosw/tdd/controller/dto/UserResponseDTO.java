package edu.eci.dosw.tdd.controller.dto;

import lombok.Data;

@Data
public class UserResponseDTO {
    private Long id;
    private String name;
    private String username;
    private String email;
}