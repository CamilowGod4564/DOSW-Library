package edu.eci.dosw.tdd.core.validator;

import edu.eci.dosw.tdd.controller.dto.LoanDTO;
import edu.eci.dosw.tdd.core.model.Book;
import edu.eci.dosw.tdd.core.model.User;
import edu.eci.dosw.tdd.core.util.ValidationUtil;

public class LoanValidator {

    private static final int MAX_LOANS_PER_USER = 3;

    public void validateBasicData(LoanDTO loanDTO) {
        if (!ValidationUtil.isNotBlank(loanDTO.getBookId())) {
            throw new IllegalArgumentException("El ID del libro es obligatorio");
        }

        if (!ValidationUtil.isNotBlank(loanDTO.getUserId())) {
            throw new IllegalArgumentException("El ID del usuario es obligatorio");
        }
    }

    public boolean userExists(User user) {
        return user != null;
    }

    public boolean bookExists(Book book) {
        return book != null;
    }

    public boolean hasAvailableCopies(Book book) {
        return book != null && book.getDisponibles() > 0;
    }

    public boolean doesNotExceedLoanLimit(int activeLoansCount) {
        return activeLoansCount < MAX_LOANS_PER_USER;
    }
}