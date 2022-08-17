package pl.urbanik.articlespressapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.urbanik.articlespressapp.model.PressArticle;
import pl.urbanik.articlespressapp.repository.PressArticleRepository;

@Service
@RequiredArgsConstructor
public class PressArticleService {

    private final PressArticleRepository pressArticleRepository;


    public PressArticle createPressArticle(PressArticle pressArticle) {
        return pressArticleRepository.save(pressArticle);
    }

    public PressArticle updatePressArticle(PressArticle pressArticle) {
        return pressArticleRepository.save(pressArticle);
    }

    public PressArticle getPressArticle(Long id) {
        return pressArticleRepository.findById(id).orElseThrow();
    }

    public void deletePressArticle(Long id) {
        pressArticleRepository.deleteById(id);
    }
}
