package edu.eci.dosw.tdd.controller.dto;
import edu.eci.dosw.tdd.core.model.enums.Status;
import java.util.Date;

public class LoanDTO {
    private long bookId;
    private long userId;
    private Date loanDate;
    private Date returnDate;
    private Status status;
}
