package com.digitalhouse.catalogservice.repository;


import com.digitalhouse.catalogservice.models.Catalog;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatalogRepository extends MongoRepository<Catalog, String> {
    Catalog findByGenre(String genre);
}
