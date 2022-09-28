package com.digitalhouse.serieservice.service;

import com.digitalhouse.serieservice.models.Serie;
import com.digitalhouse.serieservice.repository.SerieRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SerieService {
    @Value("${queue.serie.name}")
    private String serieQueue;
    private static final Logger LOG = LoggerFactory.getLogger(SerieService.class);
    private final RabbitTemplate rabbitTemplate;
    private final SerieRepository repository;
    @Autowired
    public SerieService(SerieRepository serieRepository, RabbitTemplate rabbitTemplate){
        this.repository=serieRepository;
        this.rabbitTemplate= rabbitTemplate;
    }

    //Buscamos por genero
    public List<Serie> findByGenre(String genre){
        LOG.warn("Buscando series por el genero: "+ genre);
        List<Serie> series = repository.findByGenre(genre);
        LOG.info("Series encontradas : "+ series.toString());
        return series;
    }

    public void saveSerie(Serie serie) {
        LOG.warn("Persistiendo serie en BD");
        repository.save(serie);
        LOG.info("Serie persistida : "+serie);
        LOG.warn("Enviando mensaje a la cola de serie");
        rabbitTemplate.convertAndSend(serieQueue, serie);
    }

}
