package com.digitalhouse.catalogservice.service;


import com.digitalhouse.catalogservice.client.SerieClient;
import com.digitalhouse.catalogservice.models.Catalog;
import com.digitalhouse.catalogservice.models.Serie;
import com.digitalhouse.catalogservice.repository.CatalogRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SerieService {
    private final Logger LOG = LoggerFactory.getLogger(SerieService.class);
    private final SerieClient serieClient;
    private final CatalogRepository repository;
    public SerieService(SerieClient serieClient, CatalogRepository repository) {
        this.serieClient = serieClient;
        this.repository = repository;
    }
    public List<Serie> findSerieByGenre(String genre) {
        LOG.warn("[serie-service] buscamos series por genero : "+genre);
        List<Serie> series = serieClient.getSerieByGenre(genre).getBody();
        LOG.info("[serie-service] se encontraron las siguientes series : "+series.toString());
        LOG.warn("[catalog-service] buscamos catalogo por genero : "+genre);
        Catalog catalog = repository.findByGenre(genre);
        LOG.info("[catalog-service] se encontro catalogo : "+catalog.toString());
        catalog.setSeries(series);
        LOG.warn("[catalog-service] persistiendo en mongodb");
        repository.save(catalog);
        return series;
    }
    /*Metodo para guardar*/
    @RabbitListener(queues = {"${queue.serie.name}"})
    public void saveSerie(Serie serie){
        LOG.warn("Buscando catalogo por genero : " + serie.getGenre());
        Catalog catalogo = repository.findByGenre(serie.getGenre());
        LOG.info("Se encontro catalogo : "+ catalogo);
        List<Serie> series = catalogo.getSeries();
        LOG.warn("Se agrega a catalogo la serie : "+serie);
        series.add(serie);
        catalogo.setSeries(series);
        LOG.info("Se persisite catalogo en BD : "+catalogo.toString());
        repository.save(catalogo);
    }

}
