package pl.urbanik.articlespressapp.model;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class PressArticleDto {

    private String title;
    private String contents;
    private LocalDate publicationDate;
    private String magazine;
    private String authorFirstName;
    private String authorLastName;
    private LocalDate created;
}
