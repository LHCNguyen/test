package com.example.orderfoodapp.Domain;

public class FoodDomain {
    private String title;
    private String picpopular;
    private String description;
    private Double fee;
    private int numberInCart;

    public FoodDomain(String title, String picpopular, String description, Double fee) {
        this.title = title;
        this.picpopular = picpopular;
        this.description = description;
        this.fee = fee;
    }

    public FoodDomain(String title, String picpopular, String description, Double fee, int numberInCart) {
        this.title = title;
        this.picpopular = picpopular;
        this.description = description;
        this.fee = fee;
        this.numberInCart = numberInCart;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPicpopular() {
        return picpopular;
    }

    public void setPicpopular(String picpopular) {
        this.picpopular = picpopular;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getFee() {
        return fee;
    }

    public void setFee(Double fee) {
        this.fee = fee;
    }

    public int getNumberInCart() {
        return numberInCart;
    }

    public void setNumberInCart(int numberInCart) {
        this.numberInCart = numberInCart;
    }
}
