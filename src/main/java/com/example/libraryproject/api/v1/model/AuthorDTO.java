package com.example.libraryproject.api.v1.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthorDTO {
    private int id;
    private String firstname;
    private String lastname;
    private Set<BookDTO> bookDTOSet;
}
