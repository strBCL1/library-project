package com.example.libraryproject.api.v1.mapper;

import com.example.libraryproject.api.v1.model.AuthorDTO;
import com.example.libraryproject.api.v1.model.BookDTO;
import com.example.libraryproject.domain.Author;
import com.example.libraryproject.domain.Book;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

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

        Book book1 = new Book();
        book1.setId(1);
        book1.setTitle("title1");

        Book book2 = new Book();
        book2.setId(2);
        book2.setTitle("title2");

        Set<Book> bookSet = Set.of(book1, book2);

        author.setBooks(bookSet);

        AuthorDTO authorDTO = authorMapper.authorToAuthorDto(author);

        assertAll("AuthorDTO must have same fields as Author",
                () -> assertEquals(author.getId(), authorDTO.getId()),
                () -> assertEquals(author.getFirstname(), authorDTO.getFirstname()),
                () -> assertEquals(author.getLastname(), authorDTO.getLastname()),
                () -> assertEquals(author.getBooks().size(), authorDTO.getBookDTOSet().size()));
    }

    @Test
    void givenAuthorDto_whenAuthorDtoToAuthor_thenAuthorEqualsToAuthorDto() {
        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setId(ID);
        authorDTO.setFirstname(FIRSTNAME);
        authorDTO.setLastname(LASTNAME);

        BookDTO bookDTO1 = new BookDTO();
        bookDTO1.setId(1);
        bookDTO1.setTitle("dto_title1");

        BookDTO bookDTO2 = new BookDTO();
        bookDTO2.setId(2);
        bookDTO2.setTitle("dto_title2");

        Set<BookDTO> bookDTOSet = Set.of(bookDTO1, bookDTO2);

        authorDTO.setBookDTOSet(bookDTOSet);

        Author author = authorMapper.authorDtoToAuthor(authorDTO);

        assertAll("Author must have same fields as AuthorDTO",
                () -> assertEquals(authorDTO.getId(), author.getId()),
                () -> assertEquals(authorDTO.getFirstname(), author.getFirstname()),
                () -> assertEquals(authorDTO.getLastname(), author.getLastname()),
                () -> assertEquals(authorDTO.getBookDTOSet().size(), author.getBooks().size()));
    }
}