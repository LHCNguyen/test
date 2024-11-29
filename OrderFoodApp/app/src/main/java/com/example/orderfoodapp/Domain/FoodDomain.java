package com.example.orderfoodapp.Domain;

import java.io.Serializable;

public class FoodDomain implements Serializable {

    private String title;
    private String picpopular;  // Thay đổi từ 'image' thành 'picpopular'
    private String id;
    private double fee;  // Đổi 'price' thành 'fee'
    private int quantity;

    // Constructor
    public FoodDomain(String title, String picpopular, String id, double fee) {
        this.title = title;
        this.picpopular = picpopular;
        this.id = id;
        this.fee = fee;
        this.quantity = 1;  // Mặc định số lượng là 1
    }

    // Getter và Setter cho các thuộc tính
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
