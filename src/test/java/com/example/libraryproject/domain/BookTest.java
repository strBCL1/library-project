package com.example.libraryproject.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class BookTest {
    Book book;

    @BeforeEach
    void setUp() {
        book = new Book();
    }

    @Test
    void givenId_whenBookSetId_thenIdEqualsToBookId() {
        int id = 1;

        book.setId(id);

        assertEquals(id, book.getId());
    }

    @Test
    void givenTitle_whenBookSetTitle_thenTitleEqualsToBookTitle() {
        String title = "harry potter and the philosopher's stone";

        book.setTitle(title);

        assertEquals(title, book.getTitle());
    }

    @Test
    void givenAuthors_whenBookSetAuthors_thenAuthorsEqualToBookAuthors() {
        Author author1 = new Author();
        Author author2 = new Author();

        Set<Author> authors = Set.of(author1, author2);

        book.setAuthors(authors);

        assertAll("Checking book's authors",
                () -> assertTrue(author1.getBooks().contains(book)),
                () -> assertTrue(author2.getBooks().contains(book)),
                () -> assertTrue(book.getAuthors().contains(author1)),
                () -> assertTrue(book.getAuthors().contains(author2))
        );
    }
}