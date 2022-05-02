package com.example.libraryproject.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String firstname;
    private String lastname;

    @ManyToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
    @JoinTable(
            name = "AUTHOR_BOOK",
            joinColumns = @JoinColumn(name = "AUTHOR_ID"),
            inverseJoinColumns = @JoinColumn(name = "BOOK_ID")
    )
    private Set<Book> books = new HashSet<>();

    public void setBooks(Set<Book> books) {
        addAuthorTo(books);

        this.books = books;
    }

    private void addAuthorTo(Set<Book> books) {
        for (Book book : books) {
            book.getAuthors().add(this);
        }
    }
}
