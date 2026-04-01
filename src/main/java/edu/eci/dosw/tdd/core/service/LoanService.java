package edu.eci.dosw.tdd.core.service;

import edu.eci.dosw.tdd.controller.dto.LoanDTO;
import edu.eci.dosw.tdd.controller.dto.LoanResponseDTO;

import java.util.List;

public interface LoanService {
    public LoanResponseDTO makeLoan(LoanDTO loanDTO);
    public LoanResponseDTO loanDisponibility(LoanResponseDTO loanDTO);
    public LoanResponseDTO getLoanById(String id);
    public List<LoanResponseDTO> getAllLoans();
}
