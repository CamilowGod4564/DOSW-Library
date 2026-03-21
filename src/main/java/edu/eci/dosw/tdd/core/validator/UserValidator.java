package edu.eci.dosw.tdd.core.validator;



import edu.eci.dosw.tdd.controller.dto.UserDTO;
import edu.eci.dosw.tdd.core.util.ValidationUtil;

public class UserValidator {

    public void validateCreate(UserDTO userDTO) {
        if (!ValidationUtil.isNotBlank(userDTO.getName())) {
            throw new IllegalArgumentException("El nombre del usuario es obligatorio");
        }
    }
}