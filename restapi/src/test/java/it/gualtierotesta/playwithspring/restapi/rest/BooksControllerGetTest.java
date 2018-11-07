package it.gualtierotesta.playwithspring.restapi.rest;

import it.gualtierotesta.playwithspring.restapi.dto.Book;
import it.gualtierotesta.playwithspring.restapi.service.BookService;
import it.gualtierotesta.playwithspring.restapi.service.ServiceResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(BooksController.class)
public class BooksControllerGetTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService service;

    @Test
    @WithMockUser(username = "testuser", password = "testpass")
    public void testGetById_HappyPath() throws Exception {
        String bookId = "123";
        given(service.findBookById(bookId))
                .willReturn(ServiceResult.success(createBook()));

        mockMvc.perform(get("/books/" + bookId))
                .andExpect(status().isOk())
                .andExpect(content().json("{ 'payload' : {'author':'Gualtiero'}}"));
    }


    @Test
    @WithMockUser(username = "testuser", password = "testpass")
    public void testGetById_FailingService() throws Exception {
        String bookId = "123";
        given(service.findBookById(bookId)).willReturn(ServiceResult.failure());

        mockMvc.perform(get("/books/" + bookId))
                .andExpect(status().isOk())
                .andExpect(content().json("{ 'errorCode' : 9}"));
    }

    @Test
    public void testGetById_NotAuthenticated() throws Exception {

        mockMvc.perform(get("/books/123")).andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(username = "testuser", password = "testpass", roles = "GUEST")
    public void testGetById_AuthenticatedButWithoutRightRole() throws Exception {

        mockMvc.perform(get("/books/123")).andExpect(status().is(403));
    }

    @Test
    @WithMockUser(username = "testuser", password = "testpass")
    public void testGetById_InvalidRequest() throws Exception {

        mockMvc.perform(get("/books/x123x")).andExpect(status().isBadRequest());
        mockMvc.perform(get("/books/0")).andExpect(status().isBadRequest());
    }

    private Book createBook() {
        Book book = new Book();
        book.setAuthor("Gualtiero");
        return book;
    }
}