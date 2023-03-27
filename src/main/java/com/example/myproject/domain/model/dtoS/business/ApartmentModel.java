package com.example.myproject.domain.model.dtoS.business;


import com.example.myproject.domain.model.dtoS.user.UserModel;
import com.example.myproject.domain.model.view.ImageViewModel;
import jakarta.annotation.PostConstruct;

import java.util.List;

public class ApartmentModel {
    private Long id;
    private String address;
    private String cityName;
    private Integer roomCount;
    private String description;
    private UserModel owner;
    private List<ImageViewModel> images;
    private List<OfferModel> offers;
    private Double price;
    private String size;//square meters
    private boolean isRented;
    private boolean isListed;
    private boolean isBooked;

    public ApartmentModel() {
    }

    public ApartmentModel(Long id, String address, String cityName, Integer roomCount, String description, UserModel owner, List<ImageViewModel> images, List<OfferModel> offers, Double price, String size, boolean isRented, boolean isListed, boolean isBooked) {
        this.id = id;
        this.address = address;
        this.cityName = cityName;
        this.roomCount = roomCount;
        this.description = description;
        this.owner = owner;
        this.images = images;
        this.offers = offers;
        this.price = price;
        this.size = size;
        this.isRented = isRented;
        this.isListed = isListed;
        this.isBooked = isBooked;
    }

    @PostConstruct
    public void populateMoreImages() {
        this.images.add(new ImageViewModel().setName("apartment1.avif"));
        this.images.add(new ImageViewModel().setName("apartment2.avif"));
        this.images.add(new ImageViewModel().setName("apartment3.avif"));
        this.images.add(new ImageViewModel().setName("apartment4.avif"));
        this.images.add(new ImageViewModel().setName("apartment5.avif"));
    }

    public List<OfferModel> getOffers() {
        return offers;
    }

    public ApartmentModel setOffers(List<OfferModel> offers) {
        this.offers = offers;
        return this;
    }

    public String getSize() {
        return size;
    }

    public ApartmentModel setSize(String size) {
        this.size = size;
        return this;
    }

    public Double getPrice() {
        return price;
    }

    public ApartmentModel setPrice(Double price) {
        this.price = price;
        return this;
    }

    public Long getId() {
        return id;
    }

    public ApartmentModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getCityName() {
        return cityName;
    }

    public ApartmentModel setCityName(String cityName) {
        this.cityName = cityName;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public ApartmentModel setAddress(String address) {
        this.address = address;
        return this;
    }

    public UserModel getOwner() {
        return owner;
    }

    public ApartmentModel setOwner(UserModel owner) {
        this.owner = owner;
        return this;
    }

    public Integer getRoomCount() {
        return roomCount;
    }

    public ApartmentModel setRoomCount(Integer roomCount) {
        this.roomCount = roomCount;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ApartmentModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public List<ImageViewModel> getImages() {
        return images;
    }

    public void setImages(List<ImageViewModel> images) {
        this.images = images;
    }

    public boolean isRented() {
        return isRented;
    }

    public ApartmentModel setRented(boolean rented) {
        isRented = rented;
        return this;
    }

    public boolean isListed() {
        return isListed;
    }

    public ApartmentModel setListed(boolean listed) {
        isListed = listed;
        return this;
    }

    public boolean isBooked() {
        return isBooked;
    }

    public ApartmentModel setBooked(boolean booked) {
        isBooked = booked;
        return this;
    }
}
