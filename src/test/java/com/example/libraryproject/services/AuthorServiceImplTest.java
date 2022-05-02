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

import static org.junit.jupiter.api.Assertions.assertEquals;
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
}