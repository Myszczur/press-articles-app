package pl.urbanik.articlespressapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.coyote.Response;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import pl.urbanik.articlespressapp.model.PressArticle;
import pl.urbanik.articlespressapp.service.PressArticleService;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
class PressArticleControllerTest {

    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext context;
    @MockBean
    private PressArticleService pressArticleService;

    ObjectMapper om = new ObjectMapper();

    @BeforeAll
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    void getPressArticle() throws Exception {
        MvcResult result = mockMvc
                .perform(get("/pressArticles").content(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk()).andReturn();
        String resultContent = result.getResponse().getContentAsString();
        Response response = om.readValue(resultContent, Response.class);
        assertEquals(response.isReady(), (boolean) Boolean.TRUE);
    }

    @Test
    void createPressArticle() throws Exception {
        //given
        PressArticle pressArticle = new PressArticle();
        pressArticle.setId(1L);
        pressArticle.setTitle("News");
        pressArticle.setContents("Contains of Title");
        pressArticle.setPublicationdate(Timestamp.valueOf(LocalDateTime.now()));
        pressArticle.setAuthorfirstname("Kamil");
        pressArticle.setAuthorlastname("Urbanik");
        pressArticle.setCreated(Timestamp.valueOf(LocalDateTime.now()));
        pressArticle.setUpdated(Timestamp.valueOf(LocalDateTime.now()));
        pressArticleService.createPressArticle(pressArticle);

        String jsonRequest = om.writeValueAsString(pressArticle);
        MvcResult result = mockMvc.perform(post("/pressArticle").content(jsonRequest)
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk()).andReturn();
        String resultContent = result.getResponse().getContentAsString();
        Response response = om.readValue(resultContent, Response.class);
        Assert.assertSame(response.isReady(), Boolean.TRUE);
    }
}