package com.example.myproject.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.util.Date;
import java.util.List;


@Table(name = "users")
@Entity
public class UserClientEntity extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Email
    @NotNull(message = "Email cannot be null")
    private String email;

    private String phoneNumber;

    private String firstName;

    private String lastName;

    @PastOrPresent
    private Date createdOn;

    @FutureOrPresent
    private Date modifiedOn;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<UserRoleEntity> role;

    @OneToMany(targetEntity = BookingEntity.class, mappedBy = "client", orphanRemoval = true)
    private List<BookingEntity> bookings;


    public UserClientEntity() {
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public UserClientEntity setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserClientEntity setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserClientEntity setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserClientEntity setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserClientEntity setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserClientEntity setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public UserClientEntity setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    public Date getModifiedOn() {
        return modifiedOn;
    }

    public UserClientEntity setModifiedOn(Date modifiedOn) {
        this.modifiedOn = modifiedOn;
        return this;
    }

    public List<UserRoleEntity> getRole() {
        return role;
    }

    public UserClientEntity setRole(List<UserRoleEntity> role) {
        this.role = role;
        return this;
    }

    public List<BookingEntity> getBookings() {
        return bookings;
    }

    public UserClientEntity setBookings(List<BookingEntity> bookings) {
        this.bookings = bookings;
        return this;
    }
}
