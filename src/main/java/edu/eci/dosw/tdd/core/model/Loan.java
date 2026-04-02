package edu.eci.dosw.tdd.core.model;

import edu.eci.dosw.tdd.core.model.enums.Status;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;


@Entity
@Table(name = "loans")
@Data
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @Enumerated(EnumType.STRING)
    private Status status;


    private Date loanDate;
    private Date returnDate;


}
