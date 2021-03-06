package com.example.libraryproject.controllers.v1;

import com.example.libraryproject.api.v1.model.AuthorDTO;
import com.example.libraryproject.api.v1.model.AuthorListDTO;
import com.example.libraryproject.services.AuthorService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AuthorDTO getAuthorById(@PathVariable int id) {
        return authorService.getAuthorById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AuthorDTO createAuthor(@RequestBody AuthorDTO authorDTO) {
        return authorService.createAuthor(authorDTO);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public AuthorDTO updateAuthorById(@PathVariable int id,
                                      @RequestBody AuthorDTO authorDTO) {
        return authorService.updateAuthorById(id, authorDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAuthorById(@PathVariable int id) {
        authorService.deleteAuthorById(id);
    }
}
