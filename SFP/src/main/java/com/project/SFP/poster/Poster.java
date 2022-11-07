package com.project.SFP.poster;

import com.project.SFP.user.PUser;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
public class Poster {

    @Id
    @SequenceGenerator(
            name = "poster_sequence",
            sequenceName = "poster_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "poster_sequence"
    )
    private Long id;
    private String title;
    private String bodyText;

    @ManyToOne
    private PUser user;

    public Long getId() {
        return id;
    }

    public void setId() {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public String getBodyText() {
        return bodyText;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setBodyText(String bodyText) {
        this.bodyText = bodyText;
    }

    public Poster(String title,
                  String bodyText, PUser user) {
        this.user = user;
        this.title = title;
        this.bodyText = bodyText;
    }
}
