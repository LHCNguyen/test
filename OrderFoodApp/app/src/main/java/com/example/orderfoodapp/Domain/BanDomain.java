package com.example.orderfoodapp.Domain;

public class BanDomain {
    private int id;
    private String tenBan;
    private String tinhTrang;

    public BanDomain(int id, String tenBan, String tinhTrang) {
        this.id = id;
        this.tenBan = tenBan;
        this.tinhTrang = tinhTrang;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenBan() {
        return tenBan;
    }

    public void setTenBan(String tenBan) {
        this.tenBan = tenBan;
    }

    public String getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(String tinhTrang) {
        this.tinhTrang = tinhTrang;
    }
}
