package com.example.libraryproject.api.v1.mapper;

import com.example.libraryproject.api.v1.model.AuthorDTO;
import com.example.libraryproject.domain.Author;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AuthorMapper {

    AuthorMapper INSTANCE = Mappers.getMapper(AuthorMapper.class);

    AuthorDTO authorToAuthorDto(Author author);

    Author authorDtoToAuthor(AuthorDTO authorDTO);
}
