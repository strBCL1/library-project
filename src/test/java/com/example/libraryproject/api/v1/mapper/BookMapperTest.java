package com.example.libraryproject.api.v1.mapper;

import com.example.libraryproject.api.v1.model.BookDTO;
import com.example.libraryproject.domain.Book;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookMapperTest {

    BookMapper bookMapper = BookMapper.INSTANCE;

    private static final int ID = 1;
    private static final String TITLE = "TITLE";

    @Test
    void givenBook_whenBookToBookDto_thenBookDtoEqualsToBook() {
        Book book = new Book();
        book.setId(ID);
        book.setTitle(TITLE);

        BookDTO bookDTO = bookMapper.bookToBookDto(book);

        assertAll("BookDTO must have same fields as Book",
                () -> assertEquals(book.getId(), bookDTO.getId()),
                () -> assertEquals(book.getTitle(), bookDTO.getTitle()));
    }

    @Test
    void givenBookDto_whenBookDtoToBook_thenBookEqualsToBookDto() {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setId(ID);
        bookDTO.setTitle(TITLE);

        Book book = bookMapper.bookDtoToBook(bookDTO);

        assertAll("Book must have same fields as BookDTO",
                () -> assertEquals(bookDTO.getId(), book.getId()),
                () -> assertEquals(bookDTO.getTitle(), book.getTitle()));
    }
}