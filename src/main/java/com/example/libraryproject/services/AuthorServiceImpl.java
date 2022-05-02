package com.example.libraryproject.services;

import com.example.libraryproject.api.v1.mapper.AuthorMapper;
import com.example.libraryproject.api.v1.model.AuthorDTO;
import com.example.libraryproject.domain.Author;
import com.example.libraryproject.repositories.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;

    public AuthorServiceImpl(AuthorRepository authorRepository, AuthorMapper authorMapper) {
        this.authorRepository = authorRepository;
        this.authorMapper = authorMapper;
    }

    @Override
    public List<AuthorDTO> getAllAuthors() {
        return authorRepository.findAll().stream()
                .map(authorMapper::authorToAuthorDto)
                .toList();
    }

    @Override
    public AuthorDTO getAuthorById(int id) {
        return authorRepository.findById(id)
                .map(authorMapper::authorToAuthorDto)
                .orElse(null);
    }

    @Override
    public AuthorDTO createAuthor(AuthorDTO authorDTO) {
        return saveAndReturn(authorDTO);
    }

    @Override
    public AuthorDTO updateAuthorById(int id, AuthorDTO authorDTO) {
        authorDTO.setId(id);

        return saveAndReturn(authorDTO);
    }

    private AuthorDTO saveAndReturn(AuthorDTO authorDTO) {
        Author author = authorMapper.authorDtoToAuthor(authorDTO);

        Author savedAuthor = authorRepository.save(author);

        return authorMapper.authorToAuthorDto(savedAuthor);
    }
}
