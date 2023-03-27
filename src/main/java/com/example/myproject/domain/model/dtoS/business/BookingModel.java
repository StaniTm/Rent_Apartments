package com.example.myproject.domain.model.dtoS.business;


import com.example.myproject.domain.model.view.OfferViewModel;

import java.sql.Date;

public class BookingModel {
    private OfferViewModel offer;
    private Date bookedOn;

    public BookingModel() {
    }

    public BookingModel(OfferViewModel offer, Date bookedOn) {
        this.offer = offer;
        this.bookedOn = bookedOn;
    }

    public OfferViewModel getOffer() {
        return offer;
    }

    public BookingModel setOffer(OfferViewModel offer) {
        this.offer = offer;
        return this;
    }

    public Date getBookedOn() {
        return bookedOn;
    }

    public BookingModel setBookedOn(Date bookedOn) {
        this.bookedOn = bookedOn;
        return this;
    }
}
