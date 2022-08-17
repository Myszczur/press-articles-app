package pl.urbanik.articlespressapp.model;

import lombok.Getter;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
public class PressArticleDto {

    private String title;
    private String contents;
    private LocalDate publicationDate;
    private String magazine;
    private String authorFirstName;
    private String authorLastName;
    private LocalDateTime created;
    private LocalDateTime updated;

    @PreUpdate
    public void preUpdate() {
        updated = LocalDateTime.now();
    }

    @PrePersist
    public void prePersist() {
        created = LocalDateTime.now();
    }
}
