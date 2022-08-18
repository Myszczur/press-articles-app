package pl.urbanik.articlespressapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.urbanik.articlespressapp.model.PressArticle;
import pl.urbanik.articlespressapp.model.PressArticleDto;
import pl.urbanik.articlespressapp.service.PressArticleService;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequiredArgsConstructor
public class PressArticleController {

    private static final Long EMPTY_ID = null;
    private static final Timestamp EMPTY_UPDATED = null;

    private final PressArticleService pressArticleService;


    @GetMapping("/pressArticles")
    public CollectionModel<EntityModel<PressArticle>> getAllPressArticles() {
        List<EntityModel<PressArticle>> pressArticleList = pressArticleService.getAllPressArticles().stream()
                .map(it -> EntityModel.of(it,
                        linkTo(methodOn(PressArticleController.class).getPressArticle(it.getId())).withSelfRel()
                ))
                .collect(Collectors.toList());

        return CollectionModel.of(pressArticleList,
                linkTo(methodOn(PressArticleController.class).getAllPressArticles()).withSelfRel()
        );
    }

    @GetMapping("/pressArticles/{keyWord}")
    public List<PressArticle> getAllPressArticlesByKeyWord(@PathVariable String keyWord) {
        return pressArticleService.getAllPressArticlesByKeyWord(keyWord);
    }

    @GetMapping("/pressArticle/{id}")
    public EntityModel<PressArticle> getPressArticle(@PathVariable Long id) {
        return EntityModel.of(pressArticleService.getPressArticle(id),
                linkTo(methodOn(PressArticleController.class).getPressArticle(id)).withSelfRel(),
                linkTo(methodOn(PressArticleController.class).getAllPressArticles()).withRel("pressArticles")
        );
    }

    @PostMapping("/pressArticle")
    public ResponseEntity<EntityModel<PressArticle>> createPressArticle(@RequestBody PressArticleDto pressArticleDto) {
        PressArticle pressArticle = pressArticleService.createPressArticle(new PressArticle(
                EMPTY_ID,
                pressArticleDto.getTitle(),
                pressArticleDto.getContents(),
                pressArticleDto.getPublicationdate(),
                pressArticleDto.getMagazine(),
                pressArticleDto.getAuthorfirstname(),
                pressArticleDto.getAuthorlastname(),
                pressArticleDto.getCreated(),
                EMPTY_UPDATED
        ));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(EntityModel.of(pressArticle,
                        linkTo(methodOn(PressArticleController.class).createPressArticle(pressArticleDto)).withSelfRel(),
                        linkTo(methodOn(PressArticleController.class).getAllPressArticles()).withRel("pressArticles")
                ));
    }

    @PutMapping("/pressArticle/{id}")
    public ResponseEntity<PressArticle> updatePressArticle(@PathVariable Long id, @RequestBody PressArticleDto pressArticleDto) {
        pressArticleService.updatePressArticle(new PressArticle(
                id,
                pressArticleDto.getTitle(),
                pressArticleDto.getContents(),
                pressArticleDto.getPublicationdate(),
                pressArticleDto.getMagazine(),
                pressArticleDto.getAuthorfirstname(),
                pressArticleDto.getAuthorlastname(),
                pressArticleDto.getCreated(),
                pressArticleDto.getUpdated()
        ));
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/pressArticle/{id}")
    public ResponseEntity<Object> deletePressArticle(@PathVariable Long id) {
        pressArticleService.deletePressArticle(id);
        return ResponseEntity.noContent().build();
    }
}
