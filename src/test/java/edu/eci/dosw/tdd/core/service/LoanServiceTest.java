package edu.eci.dosw.tdd.core.service;

import edu.eci.dosw.tdd.controller.dto.LoanDTO;
import edu.eci.dosw.tdd.controller.mapper.LoanMapper;
import edu.eci.dosw.tdd.core.exception.LoanLimitExceededException;
import edu.eci.dosw.tdd.core.model.Book;
import edu.eci.dosw.tdd.core.model.Loan;
import edu.eci.dosw.tdd.core.model.User;
import edu.eci.dosw.tdd.core.model.enums.StatusLoan;
import edu.eci.dosw.tdd.core.validator.LoanValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LoanServiceTest {

    @Mock
    private LoanMapper loanMapper;

    @Mock
    private LoanValidator loanValidator;

    @Mock
    private BookService bookService;

    @Mock
    private UserService userService;

    @InjectMocks
    private LoanService loanService;

    private LoanDTO loanDTO;
    private Loan loan;
    private Book book;
    private User user;

    @BeforeEach
    void setUp() {
        loanDTO = new LoanDTO(null, "BOOK-1", "USER-1", null, null, null);

        book = new Book();
        book.setId("BOOK-1");
        book.setTitle("Clean Code");
        book.setEjemplares(5);
        book.setDisponibles(5);

        user = new User();
        user.setId("USER-1");
        user.setName("John Doe");
        user.setLoansActivos(new ArrayList<>());

        loan = new Loan();
        loan.setBookId("BOOK-1");
        loan.setUserId("USER-1");
        loan.setStatus(StatusLoan.ACTIVE);
        loan.setLoanDate(LocalDate.now());
    }

    private void stubBorrowBook() {
        when(userService.getUserEntityById("USER-1")).thenReturn(user);
        when(bookService.getBookEntityById("BOOK-1")).thenReturn(book);
        when(loanValidator.hasAvailableCopies(book)).thenReturn(true);
        when(loanValidator.doesNotExceedLoanLimit(anyInt())).thenReturn(true);
        when(loanMapper.toEntity(loanDTO)).thenReturn(loan);
    }

    private String borrowAndGetLoanId() {
        stubBorrowBook();
        when(loanMapper.toDto(any(Loan.class))).thenReturn(
                new LoanDTO(null, "BOOK-1", "USER-1", LocalDate.now(), null, "ACTIVE"));
        loanService.borrowBook(loanDTO);
        return loan.getId();
    }

    @Test
    void testBorrowBookReturnsDTO() {
        LoanDTO expectedDTO = new LoanDTO(null, "BOOK-1", "USER-1", LocalDate.now(), null, "ACTIVE");
        stubBorrowBook();
        when(loanMapper.toDto(any(Loan.class))).thenReturn(expectedDTO);

        LoanDTO result = loanService.borrowBook(loanDTO);

        assertNotNull(result);
        assertEquals("ACTIVE", result.getStatus());
        assertEquals("BOOK-1", result.getBookId());
    }

    @Test
    void testBorrowBookCallsValidator() {
        stubBorrowBook();
        when(loanMapper.toDto(any(Loan.class))).thenReturn(
                new LoanDTO(null, "BOOK-1", "USER-1", LocalDate.now(), null, "ACTIVE"));

        loanService.borrowBook(loanDTO);

        verify(loanValidator, times(1)).validateBasicData(loanDTO);
    }

    @Test
    void testBorrowBookUpdatesAvailability() {
        stubBorrowBook();
        when(loanMapper.toDto(any(Loan.class))).thenReturn(
                new LoanDTO(null, "BOOK-1", "USER-1", LocalDate.now(), null, "ACTIVE"));

        loanService.borrowBook(loanDTO);

        verify(bookService, times(1)).updateAvailability("BOOK-1", -1);
    }

    @Test
    void testBorrowBookAddsLoanToUserLoansActivos() {
        stubBorrowBook();
        when(loanMapper.toDto(any(Loan.class))).thenReturn(
                new LoanDTO(null, "BOOK-1", "USER-1", LocalDate.now(), null, "ACTIVE"));

        loanService.borrowBook(loanDTO);

        assertEquals(1, user.getLoansActivos().size());
    }

    @Test
    void testBorrowBookThrowsWhenNoAvailableCopies() {
        when(userService.getUserEntityById("USER-1")).thenReturn(user);
        when(bookService.getBookEntityById("BOOK-1")).thenReturn(book);
        when(loanValidator.hasAvailableCopies(book)).thenReturn(false);

        assertThrows(IllegalArgumentException.class, () -> loanService.borrowBook(loanDTO));
    }

    @Test
    void testBorrowBookThrowsWhenLoanLimitExceeded() {
        Loan l1 = new Loan(); l1.setStatus(StatusLoan.ACTIVE);
        Loan l2 = new Loan(); l2.setStatus(StatusLoan.ACTIVE);
        Loan l3 = new Loan(); l3.setStatus(StatusLoan.ACTIVE);
        user.getLoansActivos().add(l1);
        user.getLoansActivos().add(l2);
        user.getLoansActivos().add(l3);

        when(userService.getUserEntityById("USER-1")).thenReturn(user);
        when(bookService.getBookEntityById("BOOK-1")).thenReturn(book);
        when(loanValidator.hasAvailableCopies(book)).thenReturn(true);
        when(loanValidator.doesNotExceedLoanLimit(3)).thenReturn(false);

        assertThrows(LoanLimitExceededException.class, () -> loanService.borrowBook(loanDTO));
    }

    @Test
    void testBorrowBookSetsStatusActive() {
        stubBorrowBook();
        when(loanMapper.toDto(any(Loan.class))).thenReturn(
                new LoanDTO(null, "BOOK-1", "USER-1", LocalDate.now(), null, "ACTIVE"));

        loanService.borrowBook(loanDTO);

        assertEquals(StatusLoan.ACTIVE, loan.getStatus());
    }

    @Test
    void testBorrowBookSetsLoanDate() {
        stubBorrowBook();
        when(loanMapper.toDto(any(Loan.class))).thenReturn(
                new LoanDTO(null, "BOOK-1", "USER-1", LocalDate.now(), null, "ACTIVE"));

        loanService.borrowBook(loanDTO);

        assertNotNull(loan.getLoanDate());
    }

    @Test
    void testBorrowBookGeneratesId() {
        stubBorrowBook();
        when(loanMapper.toDto(any(Loan.class))).thenReturn(
                new LoanDTO(null, "BOOK-1", "USER-1", LocalDate.now(), null, "ACTIVE"));

        loanService.borrowBook(loanDTO);

        assertNotNull(loan.getId());
        assertFalse(loan.getId().isBlank());
    }

    @Test
    void testReturnBookSetsStatusReturned() {
        String loanId = borrowAndGetLoanId();

        loanService.returnBook(loanId);

        assertEquals(StatusLoan.RETURNED, loan.getStatus());
    }

    @Test
    void testReturnBookSetsReturnDate() {
        String loanId = borrowAndGetLoanId();

        loanService.returnBook(loanId);

        assertNotNull(loan.getReturnDate());
    }

    @Test
    void testReturnBookUpdatesAvailability() {
        String loanId = borrowAndGetLoanId();

        loanService.returnBook(loanId);

        verify(bookService).updateAvailability("BOOK-1", 1);
    }

    @Test
    void testReturnBookRemovesFromUserLoansActivos() {
        String loanId = borrowAndGetLoanId();

        loanService.returnBook(loanId);

        assertTrue(user.getLoansActivos().isEmpty());
    }

    @Test
    void testReturnBookThrowsWhenNotFound() {
        assertThrows(IllegalArgumentException.class, () -> loanService.returnBook("nonexistent"));
    }

    @Test
    void testReturnBookThrowsWhenAlreadyReturned() {
        String loanId = borrowAndGetLoanId();
        loanService.returnBook(loanId);

        assertThrows(IllegalArgumentException.class, () -> loanService.returnBook(loanId));
    }

    @Test
    void testGetAllLoansReturnsEmptyList() {
        List<LoanDTO> result = loanService.getAllLoans();
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void testGetAllLoansReturnsAllLoans() {
        borrowAndGetLoanId();

        List<LoanDTO> result = loanService.getAllLoans();
        assertEquals(1, result.size());
    }

    @Test
    void testGetActiveLoansReturnsOnlyActive() {
        borrowAndGetLoanId();

        List<LoanDTO> result = loanService.getActiveLoans();
        assertEquals(1, result.size());
    }

    @Test
    void testGetActiveLoansExcludesReturned() {
        String loanId = borrowAndGetLoanId();
        loanService.returnBook(loanId);

        List<LoanDTO> result = loanService.getActiveLoans();
        assertTrue(result.isEmpty());
    }

    @Test
    void testGetLoansByUserReturnsLoansForUser() {
        borrowAndGetLoanId();

        List<LoanDTO> result = loanService.getLoansByUser("USER-1");
        assertEquals(1, result.size());
    }

    @Test
    void testGetLoansByUserReturnsEmptyForOtherUser() {
        User otherUser = new User();
        otherUser.setId("USER-2");
        otherUser.setLoansActivos(new ArrayList<>());

        borrowAndGetLoanId();
        when(userService.getUserEntityById("USER-2")).thenReturn(otherUser);

        List<LoanDTO> result = loanService.getLoansByUser("USER-2");
        assertTrue(result.isEmpty());
    }
}
