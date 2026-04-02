package edu.eci.dosw.tdd.core.model;


import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "books")
@Data
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String author;

    @OneToMany(mappedBy = "book", fetch = FetchType.LAZY)
    private List<Loan> loans = new ArrayList<>();
}
