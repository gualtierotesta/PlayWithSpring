package it.gualtierotesta.playwithspring.restapi.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import it.gualtierotesta.playwithspring.restapi.dto.MyData;
import it.gualtierotesta.playwithspring.restapi.service.MyService;
import it.gualtierotesta.playwithspring.restapi.service.ServiceResult;
import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(HomeController.class)
public class HomeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MyService service;

    @Test
    public void testGet_HappyPath() throws Exception {
        // given
        given(service.doGet()).willReturn(ServiceResult.success("x-test"));
        // when + then
        mockMvc.perform(get("/home"))
                .andExpect(status().isOk())
                .andExpect(content().json(
                        "{'errorCode':0, 'payload'='x-test'}"));
    }


    @Test
    public void testPost_HappyPath() throws Exception {
        // given
        MyData data = createData();
        String json = convertToJson(data);
        given(service.doPost(data)).willReturn(ServiceResult.success(data));

        // when + then
        mockMvc.perform(post("/home")
                .content(json).contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(content().json(
                        "{'errorCode':0}"));
    }

    @Test
    public void testPost_InvalidData() throws Exception {
        // given
        MyData data = createInvalidData();
        String json = convertToJson(data);

        // when + then
        mockMvc.perform(post("/home")
                .content(json).contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().is(400));
    }

    @Test
    public void testPost_InvalidProcessing() throws Exception {
        // given
        MyData data = createData();
        String json = convertToJson(data);
        given(service.doPost(data)).willReturn(ServiceResult.failure());

        // when + then
        mockMvc.perform(post("/home")
                .content(json).contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(content().json(
                        "{'errorCode':9}"));
    }



    private String convertToJson(MyData data) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        String json = mapper.writeValueAsString(data);
        System.out.println("JSON="+json);
        return json;
    }

    private MyData createData() {
        MyData myData = new MyData();
        myData.setName("x-name");
        myData.setBirthDate(LocalDate.now().minusYears(30));
        return myData;
    }
    private MyData createInvalidData() {
        MyData myData = new MyData();
        myData.setName("x");
        myData.setBirthDate(LocalDate.now().plusYears(30));
        return myData;
    }

}