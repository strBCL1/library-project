package com.example.libraryproject.api.v1.mapper;

import com.example.libraryproject.api.v1.model.AuthorDTO;
import com.example.libraryproject.api.v1.model.BookDTO;
import com.example.libraryproject.domain.Author;
import com.example.libraryproject.domain.Book;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface AuthorMapper {

    AuthorMapper INSTANCE = Mappers.getMapper(AuthorMapper.class);

    default AuthorDTO authorToAuthorDto(Author author) {
        if ( author == null ) {
            return null;
        }

        AuthorDTO authorDTO = new AuthorDTO();

        authorDTO.setId( author.getId() );
        authorDTO.setFirstname( author.getFirstname() );
        authorDTO.setLastname( author.getLastname() );

        Set<BookDTO> bookDTOSet = author.getBooks().stream()
                .map(BookMapper.INSTANCE::bookToBookDto)
                .collect(Collectors.toSet());

        authorDTO.setBookDTOSet(bookDTOSet);

        return authorDTO;
    }

    default Author authorDtoToAuthor(AuthorDTO authorDTO) {
        if ( authorDTO == null ) {
            return null;
        }

        Author author = new Author();

        author.setId( authorDTO.getId() );
        author.setFirstname( authorDTO.getFirstname() );
        author.setLastname( authorDTO.getLastname() );

        Set<Book> bookSet = authorDTO.getBookDTOSet().stream()
                .map(BookMapper.INSTANCE::bookDtoToBook)
                .collect(Collectors.toSet());

        author.setBooks(bookSet);

        return author;
    }
}
