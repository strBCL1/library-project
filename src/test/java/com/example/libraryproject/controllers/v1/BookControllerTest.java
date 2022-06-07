package com.example.libraryproject.controllers.v1;

import com.example.libraryproject.api.v1.model.BookDTO;
import com.example.libraryproject.services.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class BookControllerTest {

    @Mock
    BookService bookService;

    @InjectMocks
    BookController bookController;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(bookController)
                .build();
    }

    @Test
    void givenBookDtoList_whenGetAllBooks_thenReturnBookDtoList() throws Exception {
        List<BookDTO> bookDTOList = Arrays.asList(new BookDTO(1, "title1"), new BookDTO(2, "title2"));

        when(bookService.getAllBooks()).thenReturn(bookDTOList);

        mockMvc.perform(get("/books")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.bookDTOList[0].title", equalTo(bookDTOList.get(0).getTitle())))
                .andExpect(jsonPath("$.bookDTOList[1].title", equalTo(bookDTOList.get(1).getTitle())));
    }

    @Test
    void givenIdAndBookDto_whenGetBookById_thenReturnBookDto() throws Exception {
        final int ID = 1;

        BookDTO bookDTO = new BookDTO(ID, "TITLE");

        when(bookService.getBookById(anyInt())).thenReturn(bookDTO);

        mockMvc.perform(get("/books/" + ID).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo(bookDTO.getId())))
                .andExpect(jsonPath("$.title", equalTo(bookDTO.getTitle())));
    }
}