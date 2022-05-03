package com.example.libraryproject.api.v1.mapper;

import com.example.libraryproject.api.v1.model.BookDTO;
import com.example.libraryproject.domain.Book;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface BookMapper {

    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    BookDTO bookToBookDto(Book book);

    Book bookDtoToBook(BookDTO bookDTO);
}
