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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

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
        Book book = new Book();
        book.setId(ID);
        book.setTitle("book");

        when(bookRepository.findById(anyInt())).thenReturn(Optional.of(book));

        BookDTO bookDTO = bookService.getBookById(book.getId());

        assertAll("returnBookDto must have same fields as bookDto",
                () -> assertEquals(book.getId(), bookDTO.getId()),
                () -> assertEquals(book.getTitle(), bookDTO.getTitle()));
    }

    @Test
    void givenBookDto_whenCreateBook_thenBookDtoEqualsToSavedBookDto() {
        BookDTO bookDTO = new BookDTO(ID, TITLE);

        Book book = new Book();
        book.setId(bookDTO.getId());
        book.setTitle(bookDTO.getTitle());

        when(bookRepository.save(any(Book.class))).thenReturn(book);

        BookDTO savedBookDto = bookService.createBook(bookDTO);

        assertAll("SavedBookDto must have same fields as BookDto",
                () -> assertEquals(bookDTO.getId(), savedBookDto.getId()),
                () -> assertEquals(bookDTO.getTitle(), savedBookDto.getTitle()));
    }

    @Test
    void givenIdAndBookDto_whenUpdateBookById_thenBookDtoEqualsToUpdatedBookDto() {
        BookDTO bookDTO = new BookDTO(ID, TITLE);

        Book book = new Book();
        book.setId(bookDTO.getId());
        book.setTitle(bookDTO.getTitle());

        when(bookRepository.save(any(Book.class))).thenReturn(book);

        BookDTO updatedBookDTO = bookService.updateBookById(ID, bookDTO);

        assertAll("UpdatedBookDto must have same fields as BookDto",
                () -> assertEquals(bookDTO.getId(), updatedBookDTO.getId()),
                () -> assertEquals(bookDTO.getTitle(), updatedBookDTO.getTitle()));
    }

    @Test
    void givenId_whenDeleteBookById_thenVerifyDeleteBookByIdCalledOnce() {
        bookRepository.deleteById(ID);

        verify(bookRepository, times(1)).deleteById(anyInt());
    }
}