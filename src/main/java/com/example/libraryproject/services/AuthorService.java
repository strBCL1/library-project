package com.example.libraryproject.services;

import com.example.libraryproject.api.v1.model.AuthorDTO;

import java.util.List;

public interface AuthorService {

    List<AuthorDTO> getAllAuthors();

    AuthorDTO getAuthorById(int id);

    AuthorDTO createAuthor(AuthorDTO authorDTO);

    AuthorDTO updateAuthorById(int id, AuthorDTO authorDTO);

    void deleteAuthorById(int id);
}
