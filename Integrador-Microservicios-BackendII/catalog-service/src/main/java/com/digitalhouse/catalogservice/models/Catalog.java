package com.digitalhouse.catalogservice.models;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
public class Catalog {
    @Id
    private Long id;
    private String genre;
    private List<Movie> movies;
    private List<Serie> series;

    public Catalog() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    public List<Serie> getSeries() {
        return series;
    }

    public void setSeries(List<Serie> series) {
        this.series = series;
    }

    @Override
    public String toString() {
        return "Catalog{" +
                "id=" + id +
                ", genre='" + genre + '\'' +
                ", movies=" + movies +
                ", series=" + series +
                '}';
    }
}
