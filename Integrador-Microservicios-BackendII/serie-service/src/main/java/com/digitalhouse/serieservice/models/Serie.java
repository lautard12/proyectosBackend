package com.digitalhouse.serieservice.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
public class Serie {
    @Id
    private Long id;
    private String name;
    private String genre;
    private List<Season> seasons;

    public Serie() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public List<Season> getSeasons() {
        return seasons;
    }

    public void setSeasons(List<Season> seasons) {
        this.seasons = seasons;
    }

    @Override
    public String toString() {
        return "{\"Series\":{"
                + "\"id\":\"" + id +"\", "
                + "\"name\":\"" + name + "\", "
                + "\"genre\":\"" + genre + "\", "
                + "\"seasons\":\"" + seasons.toString() + "\"}}";
    }
}
