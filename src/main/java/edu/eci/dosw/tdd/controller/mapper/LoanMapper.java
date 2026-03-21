package edu.eci.dosw.tdd.controller.mapper;


import edu.eci.dosw.tdd.controller.dto.LoanDTO;
import edu.eci.dosw.tdd.core.model.Loan;
import org.springframework.stereotype.Component;

@Component
public class LoanMapper {

    public Loan toEntity(LoanDTO dto) {
        if (dto == null) {
            return null;
        }

        Loan loan = new Loan();
        loan.setBookId(dto.getBookId());
        loan.setUserId(dto.getUserId());

        return loan;
    }

    public LoanDTO toDto(Loan loan) {
        if (loan == null) {
            return null;
        }

        return new LoanDTO(
                loan.getId(),
                loan.getBookId(),
                loan.getUserId(),
                loan.getLoanDate(),
                loan.getReturnDate(),
                loan.getStatus() != null ? loan.getStatus().name() : null
        );
    }
}