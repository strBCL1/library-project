package com.example.libraryproject.controllers.v1;

import com.example.libraryproject.api.v1.model.AuthorListDTO;
import com.example.libraryproject.services.AuthorService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public AuthorListDTO getAllAuthors() {
        return new AuthorListDTO(authorService.getAllAuthors());
    }
}
