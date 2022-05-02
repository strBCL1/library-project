package com.example.libraryproject.api.v1.mapper;

import com.example.libraryproject.api.v1.model.AuthorDTO;
import com.example.libraryproject.domain.Author;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AuthorMapperTest {

    AuthorMapper authorMapper = AuthorMapper.INSTANCE;

    private static final int ID = 1;
    private static final String FIRSTNAME = "FIRSTNAME";
    private static final String LASTNAME = "LASTNAME";

    @Test
    void givenAuthor_whenAuthorToAuthorDto_thenAuthorDtoEqualsToAuthor() {
        Author author = new Author();
        author.setId(ID);
        author.setFirstname(FIRSTNAME);
        author.setLastname(LASTNAME);

        AuthorDTO authorDTO = authorMapper.authorToAuthorDto(author);

        assertAll("AuthorDTO must have same fields as Author",
                () -> assertEquals(author.getId(), authorDTO.getId()),
                () -> assertEquals(author.getFirstname(), authorDTO.getFirstname()),
                () -> assertEquals(author.getLastname(), authorDTO.getLastname()));
    }

    @Test
    void givenAuthorDto_whenAuthorDtoToAuthor_thenAuthorEqualsToAuthorDto() {
        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setId(ID);
        authorDTO.setFirstname(FIRSTNAME);
        authorDTO.setLastname(LASTNAME);

        Author author = authorMapper.authorDtoToAuthor(authorDTO);

        assertAll("Author must have same fields as AuthorDTO",
                () -> assertEquals(authorDTO.getId(), author.getId()),
                () -> assertEquals(authorDTO.getFirstname(), author.getFirstname()),
                () -> assertEquals(authorDTO.getLastname(), author.getLastname()));
    }
}