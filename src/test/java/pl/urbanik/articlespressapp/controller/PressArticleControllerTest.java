package pl.urbanik.articlespressapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import pl.urbanik.articlespressapp.model.PressArticle;
import pl.urbanik.articlespressapp.service.PressArticleService;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class PressArticleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PressArticleService pressArticleService;
    private static ObjectMapper mapper = new ObjectMapper();

    @BeforeAll
    static List<PressArticle> beforeAllTests() {
        PressArticle pressArticle = new PressArticle();
        pressArticle.setId(2L);
        pressArticle.setTitle("News");
        pressArticle.setContents("Contains of Title");
        pressArticle.setPublicationdate(Timestamp.valueOf(LocalDateTime.now()));
        pressArticle.setAuthorfirstname("Kamil");
        pressArticle.setAuthorlastname("Urbanik");
        pressArticle.setCreated(Timestamp.valueOf(LocalDateTime.now()));
        pressArticle.setUpdated(Timestamp.valueOf(LocalDateTime.now()));
        List<PressArticle> pressArticleList = new ArrayList<>();
        pressArticleList.add(pressArticle);
        return pressArticleList;
    }

    @Test
    void getAllPressArticles() throws Exception {
        Mockito.when(pressArticleService.getAllPressArticles()).thenReturn(beforeAllTests());
        mockMvc.perform(get("/pressArticles")).andExpect(status().isOk()).andExpect(jsonPath("$", Matchers.hasSize(1)))
                .andExpect(jsonPath("$[0].title", Matchers.equalTo("News")));
    }

    @Test
    void getAllPressArticlesByKeyWord() {
    }

    @Test
    void createPressArticle() throws Exception {
        Mockito.when(pressArticleService.createPressArticle(ArgumentMatchers.any())).thenReturn((PressArticle) beforeAllTests());
        String json = mapper.writeValueAsString(beforeAllTests());
        mockMvc.perform(post("/pressArticle").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                        .content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", Matchers.equalTo(2)))
                .andExpect(jsonPath("$.title", Matchers.equalTo("News")));
    }

    @Test
    void updatePressArticle() throws Exception {
        Mockito.when(pressArticleService.getPressArticle(ArgumentMatchers.any())).thenReturn((PressArticle) beforeAllTests());
        String json = mapper.writeValueAsString(beforeAllTests());
        mockMvc.perform(put("/pressArticle/2").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                        .content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(jsonPath("$.id", Matchers.equalTo(2)))
                .andExpect(jsonPath("$.title", Matchers.equalTo("News")));
    }
}