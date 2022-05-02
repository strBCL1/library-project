package com.example.libraryproject.services;

import com.example.libraryproject.api.v1.mapper.AuthorMapper;
import com.example.libraryproject.repositories.AuthorRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthorServicesImpl implements AuthorServices {

    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;

    public AuthorServicesImpl(AuthorRepository authorRepository, AuthorMapper authorMapper) {
        this.authorRepository = authorRepository;
        this.authorMapper = authorMapper;
    }
}
