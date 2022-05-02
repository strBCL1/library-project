package com.example.libraryproject.services;

import com.example.libraryproject.api.v1.mapper.AuthorMapper;
import com.example.libraryproject.api.v1.model.AuthorDTO;
import com.example.libraryproject.domain.Author;
import com.example.libraryproject.repositories.AuthorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

class AuthorServiceImplTest {

    @Mock
    AuthorRepository authorRepository;

    AuthorMapper authorMapper = AuthorMapper.INSTANCE;

    AuthorService authorService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        authorService = new AuthorServiceImpl(authorRepository, authorMapper);
    }

    @Test
    void givenAuthorsList_whenGetAllAuthors_thenReturnAuthorsList() {
        Author author1 = new Author();
        author1.setId(1);

        Author author2 = new Author();
        author2.setId(2);

        List<Author> authorList = List.of(author1, author2);

        when(authorRepository.findAll()).thenReturn(authorList);

        List<AuthorDTO> authorDTOList = authorService.getAllAuthors();

        assertEquals(authorList.size(), authorDTOList.size());
    }

    @Test
    void givenIdAndAuthor_whenGetAuthorById_thenIdEqualsToAuthorDtoId() {
        final int ID = 1;

        Author author = new Author();
        author.setId(ID);

        when(authorRepository.findById(anyInt())).thenReturn(Optional.ofNullable(author));

        AuthorDTO authorDTO = authorService.getAuthorById(author.getId());

        assertEquals(ID, authorDTO.getId());
    }

    @Test
    void givenAuthor_whenCreateAuthor_thenAuthorDtoEqualsToSavedAuthorDto() {
        final int ID = 1;

        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setId(ID);
        authorDTO.setFirstname("firstname");

        Author savedAuthor = new Author();
        savedAuthor.setId(authorDTO.getId());
        savedAuthor.setFirstname(authorDTO.getFirstname());

        when(authorRepository.save(any(Author.class))).thenReturn(savedAuthor);

        AuthorDTO savedAuthorDTO = authorService.createAuthor(authorDTO);

        assertAll("AuthorDto must have same fields as savedAuthorDto",
                () -> assertEquals(savedAuthorDTO.getId(), authorDTO.getId()),
                () -> assertEquals(savedAuthorDTO.getFirstname(), authorDTO.getFirstname()));
    }
}