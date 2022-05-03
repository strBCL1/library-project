package com.example.libraryproject.services;

import com.example.libraryproject.api.v1.mapper.BookMapper;
import com.example.libraryproject.api.v1.model.BookDTO;
import com.example.libraryproject.domain.Book;
import com.example.libraryproject.repositories.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

class BookServiceImplTest {

    @Mock
    BookRepository bookRepository;

    BookMapper bookMapper = BookMapper.INSTANCE;

    BookService bookService;

    private static final int ID = 1;
    private static final String TITLE = "TITLE";

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        bookService = new BookServiceImpl(bookRepository, bookMapper);
    }

    @Test
    void givenBooksDtoList_whenGetAllBooks_BooksDtoListEqualsToReturnBooksDtoList() {
        Book book1 = new Book();
        book1.setId(ID);
        book1.setTitle(TITLE + "1");

        Book book2 = new Book();
        book2.setId(ID + 1);
        book2.setTitle(TITLE + "2");

        List<Book> bookList = List.of(book1, book2);

        when(bookRepository.findAll()).thenReturn(bookList);

        List<BookDTO> bookDTOList = bookService.getAllBooks();

        assertAll("BookDtoList must have same content as BookList",
                () -> assertEquals(bookList.size(), bookDTOList.size()),
                () -> assertEquals(bookList.get(0).getTitle(), bookDTOList.get(0).getTitle()));
    }

    @Test
    void givenIdAndBookDto_whenGetBookById_thenBookDtoEqualsToReturnBookDto() {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setId(ID);
        bookDTO.setTitle("book");

        Book returnBook = new Book();
        returnBook.setId(ID);
        returnBook.setTitle("book");

        when(bookRepository.findById(anyInt())).thenReturn(Optional.of(returnBook));

        BookDTO returnBookDTO = bookService.getBookById(bookDTO.getId());

        assertAll("returnBookDto must have same fields as bookDto",
                () -> assertEquals(bookDTO.getId(), returnBookDTO.getId()),
                () -> assertEquals(bookDTO.getTitle(), returnBookDTO.getTitle()));
    }
}