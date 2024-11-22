package com.example.orderfoodapp.Model;

public class Category {
    private int maLoai;  // Mã loại món ăn
    private String tenLoai;  // Tên loại món ăn
    private String moTa;  // Mô tả loại món ăn

    // Constructor
    public Category(int maLoai, String tenLoai, String moTa) {
        this.maLoai = maLoai;
        this.tenLoai = tenLoai;
        this.moTa = moTa;
    }

    // Getters and Setters
    public int getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(int maLoai) {
        this.maLoai = maLoai;
    }

    public String getTenLoai() {
        return tenLoai;
    }

    public void setTenLoai(String tenLoai) {
        this.tenLoai = tenLoai;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }
}


