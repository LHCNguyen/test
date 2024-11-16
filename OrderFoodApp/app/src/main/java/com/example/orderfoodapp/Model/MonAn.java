package com.example.orderfoodapp.Model;

public class MonAn {
    private int maMonAn;
    private String tenMonAn;
    private String giaTien;
    private int maLoai;
    private String hinhAnh;

    public MonAn(int maMonAn, String tenMonAn, String giaTien, int maLoai, String hinhAnh) {
        this.maMonAn = maMonAn;
        this.tenMonAn = tenMonAn;
        this.giaTien = giaTien;
        this.maLoai = maLoai;
        this.hinhAnh = hinhAnh;
    }

    // Getter và Setter
    public int getMaMonAn() { return maMonAn; }
    public void setMaMonAn(int maMonAn) { this.maMonAn = maMonAn; }

    public String getTenMonAn() { return tenMonAn; }
    public void setTenMonAn(String tenMonAn) { this.tenMonAn = tenMonAn; }

    public String getGiaTien() { return giaTien; }
    public void setGiaTien(String giaTien) { this.giaTien = giaTien; }

    public int getMaLoai() { return maLoai; }
    public void setMaLoai(int maLoai) { this.maLoai = maLoai; }

    public String getHinhAnh() { return hinhAnh; }
    public void setHinhAnh(String hinhAnh) { this.hinhAnh = hinhAnh; }
}
