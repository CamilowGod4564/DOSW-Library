package edu.eci.dosw.tdd.core.service;

import edu.eci.dosw.tdd.core.model.Book;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import edu.eci.dosw.tdd.controller.dto.LoanDTO;
import edu.eci.dosw.tdd.core.model.Loan;
import edu.eci.dosw.tdd.core.model.enums.StatusLoan;
import edu.eci.dosw.tdd.core.model.User;
import edu.eci.dosw.tdd.core.exception.*;
import edu.eci.dosw.tdd.controller.mapper.LoanMapper;
import edu.eci.dosw.tdd.core.validator.LoanValidator;
import edu.eci.dosw.tdd.core.util.*;
import java.util.stream.Collectors;

@Service
public class LoanService {

    private List<Loan> loans = new ArrayList<>();
    private Map<String, Loan> loansById = new HashMap<>();

    private final LoanMapper loanMapper;
    private final LoanValidator loanValidator;
    private final BookService bookService;
    private final UserService userService;

    public LoanService(LoanMapper loanMapper, LoanValidator loanValidator,
                       BookService bookService, UserService userService) {
        this.loanMapper = loanMapper;
        this.loanValidator = loanValidator;
        this.bookService = bookService;
        this.userService = userService;
    }

    public LoanDTO borrowBook(LoanDTO loanDTO) {
        loanValidator.validateBasicData(loanDTO);

        User user = userService.getUserEntityById(loanDTO.getUserId());

        Book book = bookService.getBookEntityById(loanDTO.getBookId());

        if (!loanValidator.hasAvailableCopies(book)) {
            throw new IllegalArgumentException("No hay ejemplares disponibles de este libro");
        }

        int activeLoansCount = (int) user.getLoansActivos().stream()
                .filter(loan -> loan.getStatus() == StatusLoan.ACTIVE)
                .count();

        if (!loanValidator.doesNotExceedLoanLimit(activeLoansCount)) {
            throw new LoanLimitExceededException("El usuario ya tiene 3 libros prestados");
        }

        Loan loan = loanMapper.toEntity(loanDTO);
        loan.setId(IdGeneratorUtil.generateLoanId());
        loan.setLoanDate(DateUtil.now());
        loan.setStatus(StatusLoan.ACTIVE);

        bookService.updateAvailability(book.getId(), -1);

        loans.add(loan);
        loansById.put(loan.getId(), loan);

        user.getLoansActivos().add(loan);

        return loanMapper.toDto(loan);
    }

    public LoanDTO returnBook(String loanId) {
        Loan loan = loansById.get(loanId);
        if (loan == null) {
            throw new IllegalArgumentException("Préstamo no encontrado con ID: " + loanId);
        }

        if (loan.getStatus() == StatusLoan.RETURNED) {
            throw new IllegalArgumentException("Este préstamo ya fue devuelto");
        }

        loan.setStatus(StatusLoan.RETURNED);
        loan.setReturnDate(DateUtil.now());

        bookService.updateAvailability(loan.getBookId(), 1);

        User user = userService.getUserEntityById(loan.getUserId());
        user.getLoansActivos().removeIf(l -> l.getId().equals(loanId));

        return loanMapper.toDto(loan);
    }

    public List<LoanDTO> getAllLoans() {
        return loans.stream()
                .map(loanMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<LoanDTO> getActiveLoans() {
        return loans.stream()
                .filter(loan -> loan.getStatus() == StatusLoan.ACTIVE)
                .map(loanMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<LoanDTO> getLoansByUser(String userId) {
        userService.getUserEntityById(userId);

        return loans.stream()
                .filter(loan -> loan.getUserId().equals(userId))
                .map(loanMapper::toDto)
                .collect(Collectors.toList());
    }
}
