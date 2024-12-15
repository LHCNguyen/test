package com.example.orderfoodapp.Model;

public class ChiTietGoiMon {
    private int maCTGM;
    private int maGoiMon;
    private int maMonAn;
    private int soLuong;
    private String trangThai;
    private String tenMonAn;
    private String maGoi;
    private double giaTien; // Thêm thuộc tính giaTien

    // Constructor
    public ChiTietGoiMon(int maCTGM, int maGoiMon, int maMonAn, int soLuong, String trangThai,
                         String tenMonAn, String maGoi, double giaTien) {
        this.maCTGM = maCTGM;
        this.maGoiMon = maGoiMon;
        this.maMonAn = maMonAn;
        this.soLuong = soLuong;
        this.trangThai = trangThai;
        this.tenMonAn = tenMonAn;
        this.maGoi = maGoi;
        this.giaTien = giaTien;
    }

    // Getter và Setter cho tất cả các thuộc tính
    public int getMaCTGM() {
        return maCTGM;
    }

    public void setMaCTGM(int maCTGM) {
        this.maCTGM = maCTGM;
    }

    public int getMaGoiMon() {
        return maGoiMon;
    }

    public void setMaGoiMon(int maGoiMon) {
        this.maGoiMon = maGoiMon;
    }

    public int getMaMonAn() {
        return maMonAn;
    }

    public void setMaMonAn(int maMonAn) {
        this.maMonAn = maMonAn;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public String getTenMonAn() {
        return tenMonAn;
    }

    public void setTenMonAn(String tenMonAn) {
        this.tenMonAn = tenMonAn;
    }

    public String getMaGoi() {
        return maGoi;
    }

    public void setMaGoi(String maGoi) {
        this.maGoi = maGoi;
    }

    public double getGiaTien() {
        return giaTien;
    }

    public void setGiaTien(double giaTien) {
        this.giaTien = giaTien;
    }
}
