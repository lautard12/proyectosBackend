package com.digitalhouse.catalogservice.config;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQSenderConfig {

    /*Defino temple para movie*/
    @Value("${queue.movie.name}")
    private String movieQueue;

    @Bean
    public Queue queueMovie() {
        return new Queue(this.movieQueue, true);
    }

    /*Defino temple para serie*/
    @Value("${queue.serie.name}")
    private String serieQueue;

    @Bean
    public Queue queueSerie() {
        return new Queue(this.serieQueue, true);
    }
}
