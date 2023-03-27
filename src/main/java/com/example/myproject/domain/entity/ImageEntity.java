package com.example.myproject.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "images")
public class ImageEntity extends BaseEntity {
    @Column(length = 64)
    private String name;

    public ImageEntity() {
    }


    public String getName() {
        return name;
    }

    public ImageEntity setName(String name) {
        this.name = name;
        return this;
    }
}
