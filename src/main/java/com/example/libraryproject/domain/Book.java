package com.example.libraryproject.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;

    @ManyToMany(mappedBy = "books", cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
    private Set<Author> authors = new HashSet<>();

    public void setAuthors(Set<Author> authors) {
        addBookTo(authors);

        this.authors = authors;
    }

    private void addBookTo(Set<Author> authors) {
        for (Author author : authors) {
            author.getBooks().add(this);
        }
    }
}
