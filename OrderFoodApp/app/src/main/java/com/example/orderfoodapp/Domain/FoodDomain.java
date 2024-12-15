package com.example.orderfoodapp.Domain;

import java.io.Serializable;


public class FoodDomain implements Serializable {

    private String title;         // Tên món ăn
    private String picpopular;    // Hình ảnh của món ăn
    private String id;            // Mã món ăn (ID)
    private double fee;           // Giá món ăn
    private int quantity;         // Số lượng món ăn trong giỏ hàng

    // Constructor
    public FoodDomain(String title, String picpopular, String id, double fee, int quantity) {
        this.title = title;
        this.picpopular = picpopular;
        this.id = id;
        this.fee = fee;
        this.quantity = quantity;
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
