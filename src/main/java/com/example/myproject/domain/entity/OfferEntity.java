package com.example.myproject.domain.entity;

import jakarta.persistence.*;

import java.sql.Date;


@Entity
@Table(name = "offers")
public class OfferEntity extends BaseEntity {

    @Column(nullable = false)
    private Double price;//per month

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY)
    private ApartmentEntity apartment;

    private Integer minLength;

    private Date createdOn;

    public OfferEntity() {
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public OfferEntity setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    public Integer getMinLength() {
        return minLength;
    }

    public OfferEntity setMinLength(Integer minLength) {
        this.minLength = minLength;
        return this;
    }

    public Double getPrice() {
        return price;
    }

    public OfferEntity setPrice(Double price) {
        this.price = price;
        return this;
    }

    public ApartmentEntity getApartment() {
        return apartment;
    }

    public OfferEntity setApartment(ApartmentEntity hotel) {
        this.apartment = hotel;
        return this;
    }
}

