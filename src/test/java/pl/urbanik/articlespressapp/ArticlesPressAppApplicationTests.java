package pl.urbanik.articlespressapp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.urbanik.articlespressapp.controller.PressArticleController;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class ArticlesPressAppApplicationTests {

	@Autowired
	private PressArticleController controller;

	@Test
	public void contextLoads() throws Exception {
		assertThat(controller).isNotNull();
	}

}
