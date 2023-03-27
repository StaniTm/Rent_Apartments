package com.example.myproject.domain.model.view;

public class ImageViewModel {
    private Long id;
    private String name;

    public ImageViewModel() {
    }

    public ImageViewModel(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public ImageViewModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ImageViewModel setName(String name) {
        this.name = name;
        return this;
    }
}
