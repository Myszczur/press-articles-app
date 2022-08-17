package pl.urbanik.articlespressapp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PressArticle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String contents;
    private LocalDate publicationDate;
    private String magazine;
    private String authorFirstName;
    private String authorLastName;
    private LocalDateTime created;
    private LocalDateTime updated;
}
