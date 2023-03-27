package com.example.myproject.domain.model.dtoS.business;


import java.util.Date;

public class OfferModel {
    private Long id;
    private Double price;//per month
    private ApartmentModel apartment;
    private Integer minLength;
    private Date createdOn;

    public OfferModel() {
    }

    public OfferModel(Long id, Double price, ApartmentModel apartment, Integer minLength, Date createdOn) {
        this.id = id;
        this.price = price;
        this.apartment = apartment;
        this.minLength = minLength;
        this.createdOn = createdOn;
    }

    public Long getId() {
        return id;
    }

    public OfferModel setId(Long id) {
        this.id = id;
        return this;
    }

    public Double getPrice() {
        return price;
    }

    public OfferModel setPrice(Double price) {
        this.price = price;
        return this;
    }

    public ApartmentModel getApartment() {
        return apartment;
    }

    public OfferModel setApartment(ApartmentModel apartment) {
        this.apartment = apartment;
        return this;
    }

    public Integer getMinLength() {
        return minLength;
    }

    public OfferModel setMinLength(Integer minLength) {
        this.minLength = minLength;
        return this;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public OfferModel setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
        return this;
    }
}
