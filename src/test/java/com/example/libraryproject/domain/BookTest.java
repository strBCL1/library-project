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
}