package com.example.practice06;

public class SimilarGame {
    private String name;
    private Integer image;
    private String manufacturer;
    private Float rating;

    public SimilarGame(String name, Integer image, String manufacturer, Float rating) {
        this.name = name;
        this.image = image;
        this.manufacturer = manufacturer;
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getImage() {
        return image;
    }

    public void setImage(Integer image) {
        this.image = image;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }
}
