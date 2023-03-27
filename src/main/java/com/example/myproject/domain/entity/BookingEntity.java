package com.example.myproject.domain.entity;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "bookings")
public class BookingEntity extends BaseEntity {

    @ManyToOne
    private UserClientEntity client;

    @ManyToOne
    private OfferEntity offer;

    private Date bookedOn;


    public BookingEntity() {
    }


    public OfferEntity getOffer() {
        return offer;
    }

    public BookingEntity setOffer(OfferEntity offer) {
        this.offer = offer;
        return this;
    }

    public UserClientEntity getClient() {
        return client;
    }

    public BookingEntity setClient(UserClientEntity user) {
        this.client = user;
        return this;
    }

    public Date getBookedOn() {
        return bookedOn;
    }

    public BookingEntity setBookedOn(Date bookedOn) {
        this.bookedOn = bookedOn;
        return this;
    }

}
