package com.example.myproject.domain.model.banding.business;


import jakarta.validation.constraints.NotNull;

public class ApartmentOfferForm {
    @NotNull
    private String address;
    @NotNull
    private String cityName;
    @NotNull
    private Integer roomCount;
    @NotNull
    private Double price;
    @NotNull
    private String minLength;
    @NotNull
    private String description;
    private String image;
    private String size;

    public ApartmentOfferForm() {
    }

    public ApartmentOfferForm(String address, String cityName, Integer roomCount, Double price, String minLength, String description, String size) {
        this.address = address;
        this.cityName = cityName;
        this.roomCount = roomCount;
        this.price = price;
        this.minLength = minLength;
        this.description = description;
        this.size = size;
        this.image = null;
    }

    public String getSize() {
        return size;
    }

    public ApartmentOfferForm setSize(String size) {
        this.size = size;
        return this;
    }

    public String getMinLength() {
        return minLength;
    }

    public ApartmentOfferForm setMinLength(String minLength) {
        this.minLength = minLength;
        return this;
    }

    public Double getPrice() {
        return price;
    }

    public ApartmentOfferForm setPrice(Double price) {
        this.price = price;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public ApartmentOfferForm setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getCityName() {
        return cityName;
    }

    public ApartmentOfferForm setCityName(String cityName) {
        this.cityName = cityName;
        return this;
    }

    public Integer getRoomCount() {
        return roomCount;
    }

    public ApartmentOfferForm setRoomCount(Integer roomCount) {
        this.roomCount = roomCount;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ApartmentOfferForm setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getImage() {
        return image;
    }

    public ApartmentOfferForm setImage(String image) {
        this.image = image;
        return this;
    }
}
