package pl.urbanik.articlespressapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.urbanik.articlespressapp.model.PressArticle;
import pl.urbanik.articlespressapp.model.PressArticleDto;
import pl.urbanik.articlespressapp.service.PressArticleService;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
public class PressArticleController {

    private static final Long EMPTY_ID = null;
    private static final LocalDateTime EMPTY_UPDATED = null;

    private final PressArticleService pressArticleService;


    @GetMapping("/pressArticle/{id}")
    public PressArticle getPressArticle(@PathVariable Long id) {
        return pressArticleService.getPressArticle(id);
    }

    @PostMapping("/pressArticle")
    public ResponseEntity<PressArticle> createPressArticle(@RequestBody PressArticleDto pressArticleDto) {
        PressArticle pressArticle = pressArticleService.createPressArticle(new PressArticle(
                EMPTY_ID,
                pressArticleDto.getTitle(),
                pressArticleDto.getContents(),
                pressArticleDto.getPublicationDate(),
                pressArticleDto.getMagazine(),
                pressArticleDto.getAuthorFirstName(),
                pressArticleDto.getAuthorLastName(),
                pressArticleDto.getCreated(),
                EMPTY_UPDATED
        ));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(pressArticle);
    }

    @PutMapping("/pressArticle/{id}")
    public ResponseEntity<PressArticle> updatePressArticle(@PathVariable Long id, @RequestBody PressArticleDto pressArticleDto) {
        pressArticleService.updatePressArticle(new PressArticle(
                id,
                pressArticleDto.getTitle(),
                pressArticleDto.getContents(),
                pressArticleDto.getPublicationDate(),
                pressArticleDto.getMagazine(),
                pressArticleDto.getAuthorFirstName(),
                pressArticleDto.getAuthorLastName(),
                pressArticleDto.getCreated(),
                pressArticleDto.getUpdated()
        ));
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/pressArticle/{id}")
    public ResponseEntity<Object> deletePressArticle(@PathVariable Long id){
        pressArticleService.deletePressArticle(id);
        return ResponseEntity.noContent().build();
    }
}
