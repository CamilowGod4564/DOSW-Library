package edu.eci.dosw.tdd.core.model;

import java.time.LocalDate;
import edu.eci.dosw.tdd.core.model.enums.StatusLoan;
import lombok.Data;

@Data
public class Loan {
    private String id;
    private String bookId;
    private String userId;
    private Book book;
    private User user;
    private LocalDate loanDate;
    private StatusLoan status;
    private LocalDate returnDate;
}
