package com.digitalhouse.movieservice.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.digitalhouse.movieservice.models.Movie;
import com.digitalhouse.movieservice.repository.MovieRepository;

@Service
public class MovieService {
    @Value("${queue.movie.name}")
    private String movieQueue;
    private static final Logger LOG = LoggerFactory.getLogger(MovieService.class);
    private final MovieRepository repository;
    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public MovieService(MovieRepository movieRepository, RabbitTemplate rabbitTemplate) {
        this.repository = movieRepository;
        this.rabbitTemplate= rabbitTemplate;
    }

    //Peliculas por genero
    public List<Movie> findByGenre(String genre) {
        LOG.warn("Buscando las peliculas por genero : "+genre);
        List<Movie> movies = repository.findByGenre(genre);
        LOG.info("Peliculas encontradas : "+movies.toString());
        return movies;
    }

    public void saveMovie(Movie movie) {
        LOG.warn("Persistiendo pelicula en BD");
        repository.save(movie);
        LOG.info("Pelicula persisitida : "+movie.toString());
        LOG.warn("Enviando mensaje a la cola de pelicula");
        rabbitTemplate.convertAndSend(movieQueue, movie);
    }
}
