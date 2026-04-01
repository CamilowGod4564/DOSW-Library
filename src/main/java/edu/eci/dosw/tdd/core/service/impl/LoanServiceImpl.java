package edu.eci.dosw.tdd.core.service.impl;

import edu.eci.dosw.tdd.controller.dto.LoanDTO;
import edu.eci.dosw.tdd.controller.dto.LoanResponseDTO;
import edu.eci.dosw.tdd.core.service.LoanService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanServiceImpl implements LoanService {
    @Override
    public LoanResponseDTO makeLoan(LoanDTO loanDTO) {
        return null;
    }

    @Override
    public LoanResponseDTO loanDisponibility(LoanDTO loanDTO) {
        return null;
    }

    @Override
    public LoanResponseDTO getLoanById(String id) {
        return null;
    }

    @Override
    public List<LoanResponseDTO> getAllLoans() {
        return List.of();
    }
}
