package pl.urbanik.articlespressapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import pl.urbanik.articlespressapp.model.PressArticle;
import pl.urbanik.articlespressapp.repository.PressArticleRepository;
import pl.urbanik.articlespressapp.service.PressArticleService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class PressArticleControllerTest {

    private MockMvc mvc;

    @Mock
    private PressArticleService pressArticleRepository;

    @InjectMocks
    private PressArticleController pressArticleController;

    // This object will be magically initialized by the initFields method below.
    private JacksonTester<PressArticle> jsonPressArticle;

    @BeforeEach
    public void setup() {
        // We would need this line if we would not use the MockitoExtension
        // MockitoAnnotations.initMocks(this);
        // Here we can't use @AutoConfigureJsonTesters because there isn't a Spring context
        JacksonTester.initFields(this, new ObjectMapper());
        // MockMvc standalone approach
        mvc = MockMvcBuilders.standaloneSetup(pressArticleController)
                .build();
    }

    @Test
    public void canRetrieveByIdWhenExists() throws Exception {
        PressArticle pressArticle = new PressArticle();
        PressArticle pressArticle2 = new PressArticle();
        pressArticle.setId(6L);
        pressArticle2.setId(6L);
        pressArticle.setTitle("News");
        pressArticle2.setTitle("News");
        pressArticle.setContents("Contains of Title");
        pressArticle2.setContents("Contains of Title");
        pressArticle.setPublicationdate(Timestamp.valueOf(LocalDateTime.now()));
        pressArticle2.setPublicationdate(Timestamp.valueOf(LocalDateTime.now()));
        pressArticle.setAuthorfirstname("Kamil");
        pressArticle2.setAuthorfirstname("Kamil");
        pressArticle.setAuthorlastname("Urbanik");
        pressArticle2.setAuthorlastname("Urbanik");
        pressArticle.setCreated(Timestamp.valueOf(LocalDateTime.now()));
        pressArticle2.setCreated(Timestamp.valueOf(LocalDateTime.now()));
        pressArticle.setUpdated(Timestamp.valueOf(LocalDateTime.now()));
        pressArticle2.setUpdated(Timestamp.valueOf(LocalDateTime.now()));
        // given
        given(pressArticleRepository.getPressArticle(6L))
                .willReturn(pressArticle);

        // when
        MockHttpServletResponse response = mvc.perform(
                        get("/pressArticle/6")
                                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        /**
         *
         * @author kamil
         * Testy wyszły poprawnie, ale w tym momencie Builder Linków z PressArticleController powoduje błąd.
         */

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(
                jsonPressArticle.write(pressArticle2).getJson()
        );
    }

    @Test
    public void canCreateANewPressArticle() throws Exception {
        PressArticle pressArticle = new PressArticle();
        pressArticle.setId(6L);
        pressArticle.setTitle("News");
        pressArticle.setContents("Contains of Title");
        pressArticle.setPublicationdate(Timestamp.valueOf(LocalDateTime.now()));
        pressArticle.setAuthorfirstname("Kamil");
        pressArticle.setAuthorlastname("Urbanik");
        pressArticle.setCreated(Timestamp.valueOf(LocalDateTime.now()));
        pressArticle.setUpdated(Timestamp.valueOf(LocalDateTime.now()));

        // when
        MockHttpServletResponse response = mvc.perform(
                post("/pressArticle").contentType(MediaType.APPLICATION_JSON).content(
                        jsonPressArticle.write(pressArticle).getJson()
                )).andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
    }

}