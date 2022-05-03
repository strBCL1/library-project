package com.example.libraryproject.api.v1.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class BookListDTO {
    List<BookDTO> bookDTOList;
}
