package com.example.myproject.domain.entity;

import jakarta.persistence.*;

import java.sql.Date;


@Entity
@Table(name = "contracts")
public class ContractEntity extends BaseEntity {

    private Integer length;

    private Date moveInDate;

    private Date moveOutDate;

    private Double rentPerMonth;

    @OneToOne(targetEntity = ApartmentEntity.class, cascade =  CascadeType.MERGE)
    private  ApartmentEntity apartment;

    @ManyToOne(cascade =  CascadeType.MERGE)
    private UserClientEntity tenant;

    public ContractEntity() {
    }

    public Integer getLength() {
        return length;
    }

    public ContractEntity setLength(Integer length) {
        this.length = length;
        return this;
    }

    public Date getMoveInDate() {
        return moveInDate;
    }

    public ContractEntity setMoveInDate(Date startDate) {
        this.moveInDate = startDate;
        return this;
    }

    public Date getMoveOutDate() {
        return moveOutDate;
    }

    public ContractEntity setMoveOutDate(Date endDate) {
        this.moveOutDate = endDate;
        return this;
    }

    public Double getRentPerMonth() {
        return rentPerMonth;
    }

    public ContractEntity setRentPerMonth(Double rentPerMonth) {
        this.rentPerMonth = rentPerMonth;
        return this;
    }

    public ApartmentEntity getApartment() {
        return apartment;
    }

    public ContractEntity setApartment(ApartmentEntity apartment) {
        this.apartment = apartment;
        return this;
    }

    public UserClientEntity getTenant() {
        return tenant;
    }

    public ContractEntity setTenant(UserClientEntity tenant) {
        this.tenant = tenant;
        return this;
    }
}
