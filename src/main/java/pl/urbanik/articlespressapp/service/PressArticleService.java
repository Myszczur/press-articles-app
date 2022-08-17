package pl.urbanik.articlespressapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.urbanik.articlespressapp.model.PressArticle;
import pl.urbanik.articlespressapp.repository.PressArticleRepository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PressArticleService {

    private final PressArticleRepository pressArticleRepository;


    public PressArticle createPressArticle(PressArticle pressArticle) {
        pressArticle.setCreated(Timestamp.valueOf(LocalDateTime.now()));
        return pressArticleRepository.save(pressArticle);
    }

    public PressArticle updatePressArticle(PressArticle pressArticle) {
        pressArticle.setUpdated(Timestamp.valueOf(LocalDateTime.now()));
        return pressArticleRepository.save(pressArticle);
    }

    public PressArticle getPressArticle(Long id) {
        return pressArticleRepository.findById(id).orElseThrow();
    }

    public void deletePressArticle(Long id) {
        pressArticleRepository.deleteById(id);
    }

    public List<PressArticle> getAllPressArticles() {
        return pressArticleRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(PressArticle::getCreated))
                .collect(Collectors.toList());
    }

    public List<PressArticle> getAllPressArticlesByKeyWord(String keyWord) {
        return pressArticleRepository.findAll()
                .stream()
                .filter(it -> it.getTitle().contains(keyWord) || it.getContents().contains(keyWord))
                .collect(Collectors.toList());
    }
}
