package com.example.libraryproject.repositories;

import com.example.libraryproject.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer> {
}
