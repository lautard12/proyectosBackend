package com.digitalhouse.catalogservice.controller;

import com.digitalhouse.catalogservice.models.Catalog;
import com.digitalhouse.catalogservice.repository.CatalogRepository;
import com.digitalhouse.catalogservice.service.SerieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.digitalhouse.catalogservice.service.MovieService;
import com.digitalhouse.catalogservice.models.Movie;

@RestController
@RequestMapping("/catalogs")
public class CatalogController {

    private final MovieService movieService;
    private final SerieService serieService;
    private final CatalogRepository catalogoRepository;

    public CatalogController(MovieService movieService, CatalogRepository catalogoRepository, SerieService serieService){
        this.serieService = serieService;
        this.movieService = movieService;
        this.catalogoRepository = catalogoRepository;
    }


    @GetMapping("/{genre}")
    ResponseEntity<Catalog> getCatalog(@PathVariable String genre){
        Catalog catalogo = new Catalog();
        catalogo.setGenre(genre);

        catalogo.setSeries(serieService.findSerieByGenre(genre));

        catalogo.setMovies(movieService.findMovieByGenre(genre));

        return ResponseEntity.ok().body(catalogo);
    }



    @PostMapping
    ResponseEntity<String> saveMovie(@RequestBody Movie movieDTO) {
        movieService.saveMovie(movieDTO);

        return ResponseEntity.ok("La pelicula fue creada con exito");
    }
}
