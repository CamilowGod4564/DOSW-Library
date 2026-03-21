package edu.eci.dosw.tdd.core.model;

import lombok.Data;

import java.util.List;

@Data
public class User {
    private String name;
    private String id;
    private List<Loan> loansActivos;
}
