package com.example.libraryproject.repositories;

import com.example.libraryproject.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Integer> {
}
