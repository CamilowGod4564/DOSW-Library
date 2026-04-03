package edu.eci.dosw.tdd.persistence.mapper;

import edu.eci.dosw.tdd.controller.dto.UserDTO;
import edu.eci.dosw.tdd.controller.dto.UserResponseDTO;
import edu.eci.dosw.tdd.core.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "loans", ignore = true)
    @Mapping(target = "provider", constant = "LOCAL")
    @Mapping(target = "providerId", ignore = true)
    User toEntity(UserDTO userDTO);

    @Mapping(target = "loans", ignore = true)
    UserResponseDTO toResponse(User user);

    List<UserResponseDTO> toResponseList(List<User> users);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "loans", ignore = true)
    @Mapping(target = "provider", ignore = true)
    @Mapping(target = "providerId", ignore = true)
    void updateEntity(@MappingTarget User user, UserDTO userDTO);
}