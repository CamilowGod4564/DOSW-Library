package edu.eci.dosw.tdd.controller;

import edu.eci.dosw.tdd.controller.dto.LoanDTO;
import edu.eci.dosw.tdd.core.service.LoanService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/loans")
public class LoanController {

    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @PostMapping
    public ResponseEntity<LoanDTO> borrowBook(@RequestBody LoanDTO loanDTO) {
        LoanDTO loan = loanService.borrowBook(loanDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(loan);
    }

    @PutMapping("/{id}/return")
    public ResponseEntity<LoanDTO> returnBook(@PathVariable String id) {
        LoanDTO loan = loanService.returnBook(id);
        return ResponseEntity.ok(loan);
    }

    @GetMapping
    public ResponseEntity<List<LoanDTO>> getAllLoans() {
        List<LoanDTO> loans = loanService.getAllLoans();
        return ResponseEntity.ok(loans);
    }

    @GetMapping("/active")
    public ResponseEntity<List<LoanDTO>> getActiveLoans() {
        List<LoanDTO> activeLoans = loanService.getActiveLoans();
        return ResponseEntity.ok(activeLoans);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<LoanDTO>> getLoansByUser(@PathVariable String userId) {
        List<LoanDTO> loans = loanService.getLoansByUser(userId);
        return ResponseEntity.ok(loans);
    }
}
