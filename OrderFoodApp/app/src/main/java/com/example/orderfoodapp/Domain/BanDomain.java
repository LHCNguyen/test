package com.example.orderfoodapp.Domain;

public class BanDomain {
    private int id;
    private String name;
    private String tinhTrang;  // "Đang sử dụng" hoặc "Trống"

    public BanDomain(int id, String name, String tinhTrang) {
        this.id = id;
        this.name = name;
        this.tinhTrang = tinhTrang;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(String tinhTrang) {
        this.tinhTrang = tinhTrang;
    }
}
