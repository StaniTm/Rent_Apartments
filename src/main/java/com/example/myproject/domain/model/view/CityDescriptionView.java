package com.example.myproject.domain.model.view;

public class CityDescriptionView {

    private String description;

    private Integer endIndex;

    public CityDescriptionView() {
    }

    public CityDescriptionView(String description, Integer endIndex) {
        this.description = description;
        this.endIndex = endIndex;
    }

    public Integer getEndIndex() {
        return endIndex;
    }

    public CityDescriptionView setEndIndex(Integer endIndex) {
        this.endIndex = endIndex;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public CityDescriptionView setDescription(String description) {
        this.description = description;
        return this;
    }
}
