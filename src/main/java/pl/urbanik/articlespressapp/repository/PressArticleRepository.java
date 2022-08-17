package pl.urbanik.articlespressapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.urbanik.articlespressapp.model.PressArticle;

import java.util.List;

@Repository
public interface PressArticleRepository extends JpaRepository<PressArticle, Long> {

}
