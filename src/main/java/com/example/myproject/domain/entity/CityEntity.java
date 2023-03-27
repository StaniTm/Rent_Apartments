package com.example.myproject.domain.entity;


import com.example.myproject.domain.enums.City;
import jakarta.persistence.*;

@Entity
@Table(name = "cities")
public class CityEntity extends BaseEntity {

    @Enumerated(EnumType.ORDINAL)
    private City name;

    @Column(columnDefinition = "TEXT")
    private String description;

    public CityEntity() {
    }

    public City getName() {
        return name;
    }

    public CityEntity setName(City city) {
        this.name = city;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public CityEntity setDescription(String description) {
        this.description = description;
        return this;
    }
}
