package com.example.myproject.domain.model.view;


import com.example.myproject.domain.model.dtoS.business.ApartmentModel;

import java.util.List;

public class OfferViewModel {
    private Long id;
    private ApartmentModel apartment;
    private Double price;
    private String minLength;

    public OfferViewModel() {
    }

    public OfferViewModel(Long id, Double price, ApartmentModel apartment, String minLength) {
        this.id = id;
        this.apartment = apartment;
        this.price = price;
        this.minLength = minLength;
    }


    public String getMinLength() {
        return minLength;
    }

    public OfferViewModel setMinLength(String minLength) {
        this.minLength = minLength;
        return this;
    }

    public OfferViewModel setImages(List<ImageViewModel> images) {
        this.apartment.setImages(images);
        return this;
    }

    public Long getId() {
        return id;
    }

    public OfferViewModel setId(Long id) {
        this.id = id;
        return this;
    }

    public Double getPrice() {
        return price;
    }

    public OfferViewModel setPrice(Double price) {
        this.price = price;
        return this;
    }

    public ApartmentModel getApartment() {
        return apartment;
    }

    public OfferViewModel setApartment(ApartmentModel apartment) {
        this.apartment = apartment;
        return this;
    }
}
