package com.example.myproject.domain.model.view;


import com.example.myproject.domain.model.dtoS.business.BookingModel;

import java.util.Date;
import java.util.List;

public class UserViewModel {
    private Long id;
    private String username;
    private String email;
    private String phoneNumber;
    private String firstName;
    private String lastName;
    private Date createdOn;
    private Date modifiedOn;
    private List<UserRoleViewModel> role;
    private List<BookingModel> bookings;

    public UserViewModel() {
    }

    public UserViewModel(Long id, String username, String email, String phoneNumber, String firstName, String lastName, Date createdOn, Date modifiedOn, List<UserRoleViewModel> role, List<BookingModel> bookings) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.createdOn = createdOn;
        this.modifiedOn = modifiedOn;
        this.role = role;
        this.bookings = bookings;
    }

    public String getUsername() {
        return username;
    }

    public UserViewModel setUsername(String username) {
        this.username = username;
        return this;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public UserViewModel setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    public Date getModifiedOn() {
        return modifiedOn;
    }

    public UserViewModel setModifiedOn(Date modifiedOn) {
        this.modifiedOn = modifiedOn;
        return this;
    }

    public List<UserRoleViewModel> getRole() {
        return role;
    }

    public UserViewModel setRole(List<UserRoleViewModel> role) {
        this.role = role;
        return this;
    }

    public List<BookingModel> getBookings() {
        return bookings;
    }

    public UserViewModel setBookings(List<BookingModel> bookings) {
        this.bookings = bookings;
        return this;
    }

    public Long getId() {
        return id;
    }

    public UserViewModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserViewModel setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public UserViewModel setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserViewModel setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserViewModel setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }
}
