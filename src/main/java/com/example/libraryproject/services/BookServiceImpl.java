package com.example.libraryproject.services;

import com.example.libraryproject.api.v1.mapper.BookMapper;
import com.example.libraryproject.api.v1.model.BookDTO;
import com.example.libraryproject.domain.Book;
import com.example.libraryproject.repositories.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    public BookServiceImpl(BookRepository bookRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }

    @Override
    public List<BookDTO> getAllBooks() {
        return bookRepository.findAll().stream()
                .map(bookMapper::bookToBookDto)
                .toList();
    }

    @Override
    public BookDTO getBookById(int id) {
        return bookRepository.findById(id)
                .map(bookMapper::bookToBookDto)
                .orElse(null);
    }

    @Override
    public BookDTO createBook(BookDTO bookDTO) {
        return saveAndReturn(bookDTO);
    }

    private BookDTO saveAndReturn(BookDTO bookDTO) {
        Book book = bookMapper.bookDtoToBook(bookDTO);

        Book savedBook = bookRepository.save(book);

        return bookMapper.bookToBookDto(savedBook);
    }
}
