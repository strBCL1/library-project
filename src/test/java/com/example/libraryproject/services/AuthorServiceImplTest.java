package com.example.libraryproject.services;

import com.example.libraryproject.api.v1.mapper.AuthorMapper;
import com.example.libraryproject.api.v1.mapper.BookMapper;
import com.example.libraryproject.api.v1.model.AuthorDTO;
import com.example.libraryproject.api.v1.model.BookDTO;
import com.example.libraryproject.domain.Author;
import com.example.libraryproject.domain.Book;
import com.example.libraryproject.repositories.AuthorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

class AuthorServiceImplTest {

    @Mock
    AuthorRepository authorRepository;

    AuthorMapper authorMapper = AuthorMapper.INSTANCE;

    AuthorService authorService;

    private final static int ID = 1;
    private final static String FIRSTNAME = "FIRSTNAME";
    private final static String LASTNAME = "LASTNAME";

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        authorService = new AuthorServiceImpl(authorRepository, authorMapper);
    }

    @Test
    void givenAuthorsList_whenGetAllAuthors_thenReturnAuthorsDtoList() {
        Author author1 = new Author();
        author1.setId(ID);
        author1.setFirstname(FIRSTNAME + "1");
        author1.setLastname(LASTNAME + "1");

        Author author2 = new Author();
        author2.setId(ID + 1);
        author2.setFirstname(FIRSTNAME + "2");
        author2.setLastname(LASTNAME + "2");

        Book book1 = new Book();
        book1.setId(ID);
        book1.setTitle("book1");
        author1.getBooks().add(book1);
        author2.getBooks().add(book1);

        Book book2 = new Book();
        book2.setId(ID + 1);
        book2.setTitle("book2");
        author2.getBooks().add(book2);

        List<Author> authorList = Arrays.asList(author1, author2);

        when(authorRepository.findAll()).thenReturn(authorList);

        List<AuthorDTO> authorDTOList = authorService.getAllAuthors();

        assertAll("AuthorDto list must have same content as AuthorList",
                () -> assertEquals(authorList.size(), authorDTOList.size()),
                () -> assertEquals(author1.getId(), authorDTOList.get(0).getId()),
                () -> assertEquals(author2.getId(), authorDTOList.get(1).getId()));

    }

    @Test
    void givenIdAndAuthor_whenGetAuthorById_thenIdEqualsToAuthorDtoId() {
        Author author = new Author();
        author.setId(ID);

        Book book1 = new Book();
        book1.setId(ID);
        book1.setTitle("book1");
        author.getBooks().add(book1);

        Book book2 = new Book();
        book2.setId(ID + 1);
        book2.setTitle("book2");
        author.getBooks().add(book2);

        when(authorRepository.findById(anyInt())).thenReturn(Optional.ofNullable(author));

        AuthorDTO authorDTO = authorService.getAuthorById(author.getId());

        assertAll("AuthorDto must have same content as Author",
                () -> assertEquals(author.getId(), authorDTO.getId()),
                () -> assertEquals(author.getFirstname(), authorDTO.getFirstname()),
                () -> assertEquals(author.getLastname(), authorDTO.getLastname()),
                () -> assertEquals(author.getBooks().size(), authorDTO.getBookDTOSet().size()));
    }

    @Test
    void givenAuthorDto_whenCreateAuthor_thenAuthorDtoEqualsToSavedAuthorDto() {
        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setId(ID);
        authorDTO.setFirstname("firstname");
        authorDTO.setLastname("lastname");

        BookDTO bookDto1 = new BookDTO();
        bookDto1.setId(ID);
        bookDto1.setTitle("book1");

        BookDTO bookDto2 = new BookDTO();
        bookDto2.setId(ID + 1);
        bookDto2.setTitle("book2");

        authorDTO.setBookDTOSet(Set.of(bookDto1, bookDto2));


        Author savedAuthor = new Author();
        savedAuthor.setId(authorDTO.getId());
        savedAuthor.setFirstname(authorDTO.getFirstname());
        savedAuthor.setLastname(authorDTO.getLastname());

        Book book1 = new Book();
        book1.setId(bookDto1.getId());
        book1.setTitle(bookDto1.getTitle());
        savedAuthor.getBooks().add(book1);

        Book book2 = new Book();
        book2.setId(bookDto2.getId());
        book2.setTitle(bookDto2.getTitle());
        savedAuthor.getBooks().add(book2);

        when(authorRepository.save(any(Author.class))).thenReturn(savedAuthor);

        AuthorDTO savedAuthorDTO = authorService.createAuthor(authorDTO);

        assertAll("AuthorDto must have same fields as savedAuthorDto",
                () -> assertEquals(authorDTO.getId(), savedAuthorDTO.getId()),
                () -> assertEquals(authorDTO.getFirstname(), savedAuthorDTO.getFirstname()),
                () -> assertEquals(authorDTO.getLastname(), savedAuthorDTO.getLastname()),
                () -> assertEquals(authorDTO.getBookDTOSet().size(), savedAuthorDTO.getBookDTOSet().size()));
    }

    @Test
    void givenIdAndAuthorDto_whenUpdateAuthorById_thenAuthorDtoEqualsToUpdatedAuthorDto() {
        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setId(ID);
        authorDTO.setFirstname("firstname");
        authorDTO.setLastname("lastname");

        BookDTO bookDto1 = new BookDTO();
        bookDto1.setId(ID);
        bookDto1.setTitle("book1");

        BookDTO bookDto2 = new BookDTO();
        bookDto2.setId(ID + 1);
        bookDto2.setTitle("book2");

        authorDTO.setBookDTOSet(Set.of(bookDto1, bookDto2));

        Author savedAuthor = new Author();
        savedAuthor.setId(authorDTO.getId());
        savedAuthor.setFirstname(authorDTO.getFirstname());
        savedAuthor.setLastname(authorDTO.getLastname());

        Book book1 = new Book();
        book1.setId(bookDto1.getId());
        book1.setTitle(bookDto1.getTitle());
        savedAuthor.getBooks().add(book1);

        Book book2 = new Book();
        book2.setId(bookDto2.getId());
        book2.setTitle(bookDto2.getTitle());
        savedAuthor.getBooks().add(book2);

        when(authorRepository.save(any(Author.class))).thenReturn(savedAuthor);

        AuthorDTO updatedAuthorDTO = authorService.updateAuthorById(ID, authorDTO);

        assertAll("AuthorDto must have same fields as updatedAuthorDto",
                () -> assertEquals(authorDTO.getId(), updatedAuthorDTO.getId()),
                () -> assertEquals(authorDTO.getFirstname(), updatedAuthorDTO.getFirstname()),
                () -> assertEquals(authorDTO.getLastname(), updatedAuthorDTO.getLastname()),
                () -> assertEquals(authorDTO.getBookDTOSet().size(), updatedAuthorDTO.getBookDTOSet().size()));

    }

    @Test
    void givenAuthorId_whenDeleteAuthorById_thenVerifyDeleteWasCalledOnce() {

        authorService.deleteAuthorById(ID);

        verify(authorRepository, times(1)).deleteById(anyInt());
    }
}