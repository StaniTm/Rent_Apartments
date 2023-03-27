package com.example.myproject.domain.model.banding.business;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public class RentForm {
    @NotNull
    private String phoneNumber;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @Email
    @NotNull
    private String email;
    @NotNull
    @FutureOrPresent
    private Date moveInDate;

    public RentForm() {
    }

    public RentForm(String phoneNumber, String firstName, String lastName, String email, Date moveInDate) {
        this.phoneNumber = phoneNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.moveInDate = moveInDate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public RentForm setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public RentForm setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public RentForm setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public RentForm setEmail(String email) {
        this.email = email;
        return this;
    }

    public Date getMoveInDate() {
        return moveInDate;
    }

    public RentForm setMoveInDate(Date moveInDate) {
        this.moveInDate = moveInDate;
        return this;
    }
}
