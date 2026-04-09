package edu.eci.dosw.tdd.persistence.mapper;

import edu.eci.dosw.tdd.controller.dto.BookDTO;
import edu.eci.dosw.tdd.controller.dto.BookResponseDTO;
import edu.eci.dosw.tdd.core.model.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import java.util.List;

@Mapper(componentModel = "spring")
public interface BookMapper {

    Book toEntity(BookDTO bookDTO);
    BookResponseDTO toResponse(Book book);
    List<BookResponseDTO> toResponseList(List<Book> books);
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "loans", ignore = true)
    void updateEntity(@MappingTarget Book book, BookDTO bookDTO);
}