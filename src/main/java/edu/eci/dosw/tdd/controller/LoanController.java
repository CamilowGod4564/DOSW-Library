package edu.eci.dosw.tdd.controller;


import edu.eci.dosw.tdd.controller.dto.LoanDTO;
import edu.eci.dosw.tdd.controller.dto.LoanResponseDTO;
import edu.eci.dosw.tdd.core.service.LoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/loans")
@RequiredArgsConstructor
public class LoanController {

    private final LoanService loanService;

    @PostMapping
    public LoanResponseDTO makeLoan(LoanDTO loanDTO) {
        return null;
    }

    @GetMapping("/check")
    public LoanResponseDTO loanDisponibility(LoanDTO loanDTO){
        return null;
    }

    @GetMapping("/{id}")
    public LoanResponseDTO getLoanById(String id){
        return null;
    }

    @GetMapping
    public List<LoanResponseDTO> getAllLoans(){
        return null;
    }
}
