package com.example.libraryproject.controllers.v1;

import com.example.libraryproject.api.v1.model.AuthorDTO;
import com.example.libraryproject.services.AuthorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class AuthorControllerTest {

    @Mock
    AuthorService authorService;

    @InjectMocks
    AuthorController authorController;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(authorController)
                .build();
    }

    @Test
    void getAllAuthors() throws Exception {
        AuthorDTO authorDTO1 = new AuthorDTO();
        authorDTO1.setId(1);
        authorDTO1.setFirstname("firstname_1");
        authorDTO1.setLastname("lastname_1");

        AuthorDTO authorDTO2 = new AuthorDTO();
        authorDTO2.setId(2);
        authorDTO2.setFirstname("firstname_2");
        authorDTO2.setLastname("lastname_2");

        List<AuthorDTO> authorDTOList = List.of(authorDTO1, authorDTO2);

        when(authorService.getAllAuthors()).thenReturn(authorDTOList);

        mockMvc.perform(get("/authors")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.authorDTOList[0].id", equalTo(authorDTO1.getId())));

    }
}