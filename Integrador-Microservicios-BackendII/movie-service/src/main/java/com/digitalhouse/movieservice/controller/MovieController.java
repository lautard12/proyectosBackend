package com.digitalhouse.movieservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.digitalhouse.movieservice.service.MovieService;
import com.digitalhouse.movieservice.models.Movie;

@RestController
@RequestMapping("/movies")
public class MovieController {
    private final MovieService service;
    @Autowired
    public MovieController(MovieService movieService) {
        this.service = movieService;
    }

    @GetMapping("/{genre}")
    ResponseEntity<List<Movie>> getMovieByGenre(@PathVariable String genre) {
        return ResponseEntity.ok().body(service.findByGenre(genre));
    }
    @PostMapping
    ResponseEntity<?> saveMovies(@RequestBody Movie movie){
        service.saveMovie(movie);
        return ResponseEntity.ok("La pelicula fue guardada correctamente");
    }
}
