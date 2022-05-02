package com.example.libraryproject.services;

import com.example.libraryproject.api.v1.model.AuthorDTO;

import java.util.List;

public interface AuthorService {
    List<AuthorDTO> getAllAuthors();
}
