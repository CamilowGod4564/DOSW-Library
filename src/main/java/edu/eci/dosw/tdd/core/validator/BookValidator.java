package edu.eci.dosw.tdd.core.validator;


import edu.eci.dosw.tdd.controller.dto.BookDTO;
import edu.eci.dosw.tdd.core.util.ValidationUtil;

public class BookValidator {

    public void validateCreate(BookDTO bookDTO) {
        if (!ValidationUtil.isNotBlank(bookDTO.getTitle())) {
            throw new IllegalArgumentException("El título del libro es obligatorio");
        }

        if (!ValidationUtil.isNotBlank(bookDTO.getAuthor())) {
            throw new IllegalArgumentException("El autor del libro es obligatorio");
        }

        if (!ValidationUtil.isPositive(bookDTO.getEjemplares())) {
            throw new IllegalArgumentException("La cantidad de ejemplares debe ser mayor a 0");
        }
    }
}