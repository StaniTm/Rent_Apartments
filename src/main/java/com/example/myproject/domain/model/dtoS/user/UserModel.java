package com.example.myproject.domain.model.dtoS.user;

import jakarta.annotation.PostConstruct;

import java.util.Date;
import java.util.List;


public class UserModel {
    private Long id;

    private String username;

    private String password;

    private String email;

    private String phoneNumber;

    private String firstName;

    private String lastName;

    private Date createdOn;

    private Date modifiedOn;

    private List<UserRoleModel> role;

    public UserModel() {
    }

    public Long getId() {
        return id;
    }

    public UserModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public UserModel setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserModel setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserModel setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserModel setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserModel setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserModel setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public UserModel setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    public Date getModifiedOn() {
        return modifiedOn;
    }

    public UserModel setModifiedOn(Date modifiedOn) {
        this.modifiedOn = modifiedOn;
        return this;
    }

    public List<UserRoleModel> getRole() {
        return role;
    }

    public UserModel setRole(List<UserRoleModel> role) {
        this.role = role;
        return this;
    }

    @PostConstruct
    public void setCreatedOn() {
        this.setCreatedOn(new Date());
    }
}
