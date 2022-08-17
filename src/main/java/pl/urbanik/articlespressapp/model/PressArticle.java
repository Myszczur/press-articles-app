package pl.urbanik.articlespressapp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
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
    private LocalDate created;

    @PrePersist
    public void prePersist() {
        created = LocalDate.now();
    }
}
