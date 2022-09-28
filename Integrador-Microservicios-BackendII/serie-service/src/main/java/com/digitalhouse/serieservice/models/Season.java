package com.digitalhouse.serieservice.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
public class Season {
    @Id
    private Long id;
    private Integer seasonNumber;

    private List<Chapter> chapters;

    public Season() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSeasonNumber() {
        return seasonNumber;
    }

    public void setSeasonNumber(Integer seasonNumber) {
        this.seasonNumber = seasonNumber;
    }

    public List<Chapter> getChapters() {
        return chapters;
    }

    public void setChapters(List<Chapter> chapters) {
        this.chapters = chapters;
    }

    @Override
    public String toString() {
        return "{\"Season\":{"
                + "\"id\":\"" + id +"\", "
                + "\"seasonNumber\":\"" + seasonNumber+"\", "
                + "\"chapters\":\"" + chapters.toString() + "\"}}";
    }
}
