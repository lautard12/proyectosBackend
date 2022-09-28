package com.digitalhouse.serieservice.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Chapter {

    @Id
    private Long id;
    private String name;
    private Integer number;

    private String urlStream;

    public Chapter() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getUrlStream() {
        return urlStream;
    }

    public void setUrlStream(String urlStream) {
        this.urlStream = urlStream;
    }

    @Override
    public String toString() {
        return "{\"Chapter\":{"
                + "\"id\":\"" + id +"\" ,"
                + "\"name\":\"" + name +"\" ,"
                + "\"number\":\"" + number + "\" ,"
                + "\"urlStream\":\"" + urlStream + "\"}}";
    }
}
