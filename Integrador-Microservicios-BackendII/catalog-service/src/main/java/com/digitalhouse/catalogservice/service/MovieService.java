package com.digitalhouse.catalogservice.service;

import java.util.List;
import com.digitalhouse.catalogservice.client.MovieClient;
import com.digitalhouse.catalogservice.models.Catalog;
import com.digitalhouse.catalogservice.repository.CatalogRepository;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import com.digitalhouse.catalogservice.models.Movie;

@Service
public class MovieService {
    private String genre;
    private final Logger LOG = LoggerFactory.getLogger(MovieService.class);
    private final MovieClient movieClient;
    private final CatalogRepository repository;
    public MovieService(MovieClient movieClient, CatalogRepository repository) {
        this.movieClient = movieClient;
        this.repository = repository;
    }
    @CircuitBreaker(name="movies", fallbackMethod = "moviesFallBackMethod")
    public List<Movie> findMovieByGenre(String genre) {
        this.genre= genre;
        LOG.warn("Buscamos movies a [movie-service] por genero : "+genre);
        List<Movie> movies = movieClient.getMovieByGenre(genre).getBody();
        LOG.info("Se encontraron las siguientes peliculas : "+movies.toString());
        LOG.warn("Buscamos catalogo por genero : "+genre);
        Catalog catalogo = repository.findByGenre(genre);
        LOG.info("Se encontro catalogo : "+catalogo.toString());
        catalogo.setMovies(movies);
        LOG.warn("Persistiendo en BD");
        repository.save(catalogo);
        return movies;
    }
    private List<Movie> moviesFallBackMethod(CallNotPermittedException exception) {
        LOG.info("Se activó el circuitbreaker");
        LOG.warn("Se busca información en base de datos local");
        Catalog catalogo = repository.findByGenre(this.genre);
        LOG.info("Se encontro el siguiente catalogo : "+catalogo.toString());
        return catalogo.getMovies();
    }
    @RabbitListener(queues = {"${queue.movie.name}"})
    public void saveMovie(Movie movie){
        LOG.warn("Buscando catalogo por genero : "+movie.getGenre());
        Catalog catalogo = repository.findByGenre(movie.getGenre());
        LOG.info("Se encontro catalogo : "+ catalogo);
        List<Movie> movies = catalogo.getMovies();
        LOG.warn("Se agrega a catalogo la pelicula : "+movie);
        movies.add(movie);
        catalogo.setMovies(movies);
        LOG.info("Se persisite catalogo en BD : "+catalogo.toString());
        repository.save(catalogo);
    }

}