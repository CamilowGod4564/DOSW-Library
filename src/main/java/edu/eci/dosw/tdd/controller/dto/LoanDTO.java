package edu.eci.dosw.tdd.controller.dto;

import edu.eci.dosw.tdd.core.model.enums.Status;
import lombok.Data;
import java.util.Date;

@Data
public class LoanDTO {
    private Long bookId;
    private Long userId;
    private Date loanDate;
    private Date returnDate;
    private Status status;
}