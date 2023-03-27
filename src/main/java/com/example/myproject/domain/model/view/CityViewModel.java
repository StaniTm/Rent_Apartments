package com.example.myproject.domain.model.view;


public class CityViewModel {

    private String name;

    private String description;

    private Integer dotIndex;

    public CityViewModel() {
    }

    public CityViewModel(String name, String description) {
        this.name = name;
        this.description = description;
        this.dotIndex = 0;
    }



    public String getName() {
        return name;
    }

    public CityViewModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public CityViewModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public Integer getDotIndex() {
        return dotIndex;
    }

    public CityViewModel setDotIndex(Integer endIndex) {
        this.dotIndex = endIndex;
        return this;
    }
}
