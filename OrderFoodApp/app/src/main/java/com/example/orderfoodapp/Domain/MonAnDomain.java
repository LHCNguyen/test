package com.example.orderfoodapp.Domain;

public class MonAnDomain {
    private String tenMonAn;
    private int soLuong;
    private String hinhAnh;

    public MonAnDomain(String tenMonAn, int soLuong, String hinhAnh) {
        this.tenMonAn = tenMonAn;
        this.soLuong = soLuong;
        this.hinhAnh = hinhAnh;
    }

    public String getTenMonAn() {
        return tenMonAn;
    }

    public void setTenMonAn(String tenMonAn) {
        this.tenMonAn = tenMonAn;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }
}
