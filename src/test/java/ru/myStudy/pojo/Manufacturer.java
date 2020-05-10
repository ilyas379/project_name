package ru.myStudy.pojo;

import java.util.Arrays;

public class Manufacturer {
        private Models[] models;

    private String id;

    private String title;

    private String country;

    public Manufacturer() {
    }

    public Manufacturer(Models[] models, String id, String title, String country) {
        this.models = models;
        this.id = id;
        this.title = title;
        this.country = country;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Models[] getModels() {
        return models;
    }

    public void setModels(Models[] models) {
        this.models = models;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Manufacturer{" +
                "models=" + Arrays.toString(models) +
                ", id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
