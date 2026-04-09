package edu.eci.dosw.tdd.controller.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserResponseDTO {
    private Long id;
    private String name;
    private String username;
    private String email;
    private List<LoanResponseDTO> loans;
}