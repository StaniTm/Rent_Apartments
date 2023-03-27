package com.example.myproject.domain.model.dtoS.business;


import com.example.myproject.domain.model.dtoS.user.UserModel;

import java.time.LocalDate;

public class ContractModel {
    private Long id;
    private Integer length;
    private LocalDate moveInDate;
    private LocalDate moveOutDate;
    private ApartmentModel apartment;
    private UserModel tenant;
    private Double rentPerMonth;

    public ContractModel() {
    }

    public ContractModel(Long id, Integer length, LocalDate moveInDate, ApartmentModel apartment, UserModel tenant, Double rentPerMonth) {
        this.id = id;
        this.length = length;
        this.moveInDate = moveInDate;
        this.apartment = apartment;
        this.tenant = tenant;
        this.rentPerMonth = rentPerMonth;
    }

    public Long getId() {
        return id;
    }

    public ContractModel setId(Long id) {
        this.id = id;
        return this;
    }

    public Integer getLength() {
        return length;
    }

    public ContractModel setLength(Integer length) {
        this.length = length;
        return this;
    }

    public LocalDate getMoveInDate() {
        return moveInDate;
    }

    public ContractModel setMoveInDate(LocalDate moveInDate) {
        this.moveInDate = moveInDate;
        return this;
    }

    public LocalDate getMoveOutDate() {
        return moveOutDate;
    }

    public ContractModel setMoveOutDate(LocalDate moveOutDate) {
        this.moveOutDate = moveOutDate;
        return this;
    }

    public ApartmentModel getApartment() {
        return apartment;
    }

    public ContractModel setApartment(ApartmentModel apartment) {
        this.apartment = apartment;
        return this;
    }

    public Double getRentPerMonth() {
        return rentPerMonth;
    }

    public ContractModel setRentPerMonth(Double rentPerMonth) {
        this.rentPerMonth = rentPerMonth;
        return this;
    }

    public UserModel getTenant() {
        return tenant;
    }

    public ContractModel setTenant(UserModel tenant) {
        this.tenant = tenant;
        return this;
    }
}
