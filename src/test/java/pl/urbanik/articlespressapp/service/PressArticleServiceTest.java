package pl.urbanik.articlespressapp.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.urbanik.articlespressapp.model.PressArticle;
import pl.urbanik.articlespressapp.repository.PressArticleRepository;

import javax.persistence.EntityNotFoundException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PressArticleServiceTest {

    @Autowired
    PressArticleService pressArticleService;

    @Autowired
    PressArticleRepository pressArticleRepository;

    @BeforeAll
    void setUp() {
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
    }

    @Test
    void createPressArticle() {
        PressArticle pressArticle = new PressArticle();
        pressArticle.setId(2L);
        pressArticle.setTitle("News");
        pressArticle.setContents("Contains of Title");
        pressArticle.setPublicationdate(Timestamp.valueOf(LocalDateTime.now()));
        pressArticle.setAuthorfirstname("Kamil");
        pressArticle.setAuthorlastname("Urbanik");
        pressArticle.setCreated(Timestamp.valueOf(LocalDateTime.now()));
        pressArticle.setUpdated(Timestamp.valueOf(LocalDateTime.now()));
        pressArticleService.createPressArticle(pressArticle);
        PressArticle pressArticle2 = pressArticleRepository.findById(1L).orElseThrow(EntityNotFoundException::new);
        assertEquals("Kamil", pressArticle2.getAuthorfirstname());

    }

    @Test
    void updatePressArticle() {
        PressArticle pressArticle = pressArticleRepository.findById(1L).orElseThrow(EntityNotFoundException::new);
        pressArticle.setAuthorfirstname("Maciek");
        PressArticle updatedArticle = pressArticleService.updatePressArticle(pressArticle);
        assertEquals("Maciek", updatedArticle.getAuthorfirstname());
    }

    @Test
    void getPressArticle() {
        PressArticle pressArticle = pressArticleService.getPressArticle(1L);
        assertEquals("News", pressArticle.getTitle());
    }

    @Test
    void testDeletePressArticle() {
        pressArticleService.deletePressArticle(1L);
    }

    @Test
    void testGetAllPressArticles() {
        assertFalse(pressArticleService.getAllPressArticles().isEmpty());
    }

    @Test
    void testGetAllPressArticlesByKeyWord() {
        assertFalse(pressArticleService.getAllPressArticlesByKeyWord("News").isEmpty());
        assertFalse(pressArticleService.getAllPressArticlesByKeyWord("Contains of Title").isEmpty());
    }
}