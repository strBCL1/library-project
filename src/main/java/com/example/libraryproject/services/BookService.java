package com.example.libraryproject.services;

import com.example.libraryproject.api.v1.model.BookDTO;

import java.util.List;

public interface BookService {
    List<BookDTO> getAllBooks();
}
