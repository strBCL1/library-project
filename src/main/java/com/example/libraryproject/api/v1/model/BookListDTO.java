package com.example.libraryproject.api.v1.model;

import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class BookListDTO {
    List<BookDTO> bookDTOList;
}
