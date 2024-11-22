package com.example.orderfoodapp.Domain;

public class CategoryDomain {
    private String name;
    private String image;

    public CategoryDomain(String name, String image) {
        this.name = name;
        this.image = image;
    }

    // Getter cho name
    public String getName() {
        return name;
    }

    // Getter cho image
    public String getImage() {
        return image;
    }
}
