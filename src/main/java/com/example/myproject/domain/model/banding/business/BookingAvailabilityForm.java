package com.example.myproject.domain.model.banding.business;

import jakarta.validation.constraints.NotNull;

import java.util.Date;

public class BookingAvailabilityForm {


    @NotNull
    private Date viewDate;
    private String phoneNumber;


    public BookingAvailabilityForm() {
    }

    public BookingAvailabilityForm(Date viewDate, String phoneNumber) {
        this.viewDate = viewDate;
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public BookingAvailabilityForm setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public Date getViewDate() {
        return viewDate;
    }

    public BookingAvailabilityForm setViewDate(Date viewDate) {
        this.viewDate = viewDate;
        return this;
    }
}
