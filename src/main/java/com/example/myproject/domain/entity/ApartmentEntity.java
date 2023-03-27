package com.example.myproject.domain.entity;

import jakarta.persistence.*;

import java.util.List;


@Entity
@Table(name = "apartments")
public class ApartmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String address;

    @ManyToOne(targetEntity = UserClientEntity.class)
    private UserClientEntity owner;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = CityEntity.class, cascade = { CascadeType.PERSIST, CascadeType.REFRESH})
    private CityEntity city;

    @Column(nullable = false)
    private Integer roomCount;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private List<ImageEntity> images;

    @OneToMany(mappedBy = "apartment", cascade = CascadeType.REMOVE)
    private List<OfferEntity> offers;

    @Column(nullable = false)
    private String size;//square meters

    @Column(nullable = false)
    private Boolean isRented;

    @Column(nullable = false)
    private Boolean isListed;

    @Column(nullable = false)
    private Boolean isBooked;


    public ApartmentEntity() {
    }

    public Long getId() {
        return id;
    }

    public ApartmentEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public String getSize() {
        return size;
    }

    public ApartmentEntity setSize(String size) {
        this.size = size;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public ApartmentEntity setAddress(String name) {
        this.address = name;
        return this;
    }

    public Integer getRoomCount() {
        return roomCount;
    }

    public ApartmentEntity setRoomCount(Integer roomCount) {
        this.roomCount = roomCount;
        return this;
    }

    public UserClientEntity getOwner() {
        return owner;
    }

    public ApartmentEntity setOwner(UserClientEntity owner) {
        this.owner = owner;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ApartmentEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    public CityEntity getCity() {
        return city;
    }

    public ApartmentEntity setCity(CityEntity cityName) {
        this.city = cityName;
        return this;
    }

    public List<ImageEntity> getImages() {
        return images;
    }

    public ApartmentEntity setImages(List<ImageEntity> image) {
        this.images = image;
        return this;
    }

    public List<OfferEntity> getOffers() {
        return offers;
    }

    public ApartmentEntity setOffers(List<OfferEntity> offer) {
        this.offers = offer;
        return this;
    }

    public Boolean isRented() {
        return isRented;
    }

    public ApartmentEntity setRented(Boolean rented) {
        isRented = rented;
        return this;
    }

    public Boolean isListed() {
        return isListed;
    }

    public ApartmentEntity setListed(Boolean listed) {
        isListed = listed;
        return this;
    }

    public Boolean isBooked() {
        return isBooked;
    }

    public ApartmentEntity setBooked(Boolean booked) {
        isBooked = booked;
        return this;
    }
}
