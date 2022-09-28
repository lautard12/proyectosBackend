package com.digitalhouse.serieservice.repository;

import com.digitalhouse.serieservice.models.Serie;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SerieRepository extends MongoRepository<Serie, String> {
    List<Serie> findByGenre(String genre);
}
