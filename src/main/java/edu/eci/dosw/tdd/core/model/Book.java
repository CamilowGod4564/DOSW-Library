package edu.eci.dosw.tdd.core.model;

import lombok.Data;

@Data
public class Book {
    private String title;
    private String author;
    private String id;
    private int ejemplares;
    private int disponibles;
}
