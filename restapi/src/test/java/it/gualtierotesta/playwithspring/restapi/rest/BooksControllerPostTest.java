package it.gualtierotesta.playwithspring.restapi.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import it.gualtierotesta.playwithspring.restapi.dto.Book;
import it.gualtierotesta.playwithspring.restapi.service.BookService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(BooksController.class)
public class BooksControllerPostTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService service;

    @Test
    @WithMockUser(username = "testuser", password = "testpass")
    public void testPost_HappyPath() throws Exception {

        Book book = createNewBook();
        String json = convertToJson(book);

        mockMvc.perform(post("/books")
                .content(json).contentType(MediaType.APPLICATION_JSON_UTF8).with(csrf()))
                .andExpect(status().isOk())
                .andExpect(content().json("{'errorCode':0}"));
    }

    private String convertToJson(Book book) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        String json = mapper.writeValueAsString(book);
        System.out.println("JSON=" + json);
        return json;
    }

    private Book createNewBook() {
        Book book = new Book();
        book.setAuthor("John Doe");
        book.setTitle("I'm dead");
        book.setPublishingDate(LocalDate.now().minusYears(2));
        return book;
    }
}