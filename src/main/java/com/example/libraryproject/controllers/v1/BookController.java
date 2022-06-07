package com.example.libraryproject.controllers.v1;

import com.example.libraryproject.api.v1.model.BookDTO;
import com.example.libraryproject.api.v1.model.BookListDTO;
import com.example.libraryproject.services.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public BookListDTO getAllBooks() {
        return new BookListDTO(bookService.getAllBooks());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BookDTO getBookById(@PathVariable int id) {
        return bookService.getBookById(id);
    }
}
