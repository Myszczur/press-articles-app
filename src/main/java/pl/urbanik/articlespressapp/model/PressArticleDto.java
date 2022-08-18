package pl.urbanik.articlespressapp.model;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class PressArticleDto {

    private String title;
    private String contents;
    private Timestamp publicationdate;
    private String magazine;
    private String authorfirstname;
    private String authorlastname;
    private Timestamp created;
    private Timestamp updated;

}
