package com.example.libraryproject.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class AuthorTest {
    Author author;

    @BeforeEach
    void setUp() {
        author = new Author();
    }

    @Test
    void givenId_whenAuthorSetId_thenIdEqualsToAuthorId() {
        int id = 1;

        author.setId(id);

        assertEquals(id, author.getId());
    }

    @Test
    void givenFirstname_whenAuthorSetFirstname_thenFirstnameEqualsToAuthorFirstname() {
        String firstname = "firstname";

        author.setFirstname(firstname);

        assertEquals(firstname, author.getFirstname());
    }

    @Test
    void givenLastname_whenAuthorSetLastname_thenLastnameEqualsToAuthorLastname() {
        String lastname = "lastname";

        author.setLastname(lastname);

        assertEquals(lastname, author.getLastname());
    }

    @Test
    void givenBooks_whenAuthorSetBooks_thenBooksEqualToAuthorBooks() {
        Book book1 = new Book();
        Book book2 = new Book();

        Set<Book> books = Set.of(book1, book2);

        author.getBooks().add(book1);
        author.getBooks().add(book2);

        assertEquals(2, author.getBooks().size());
    }
}