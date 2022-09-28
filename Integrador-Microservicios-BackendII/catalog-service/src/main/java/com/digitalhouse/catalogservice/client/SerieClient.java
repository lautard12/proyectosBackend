package com.digitalhouse.catalogservice.client;

import com.digitalhouse.catalogservice.models.Serie;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "serie-service")
public interface SerieClient {
    @GetMapping("/series/{genre}")
    ResponseEntity<List<Serie>> getSerieByGenre(@PathVariable String genre);
    @PostMapping("/series")
    ResponseEntity<?> saveSeries(@RequestBody Serie serie);

}
