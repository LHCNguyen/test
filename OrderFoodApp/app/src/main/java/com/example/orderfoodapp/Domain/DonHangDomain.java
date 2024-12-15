package com.example.orderfoodapp.Domain;

import com.example.orderfoodapp.Domain.MonAnDomain;

import java.util.List;

public class DonHangDomain {
    private String tenDonHang;
    private List<MonAnDomain> danhSachMonAn;

    public DonHangDomain(String tenDonHang, List<MonAnDomain> danhSachMonAn) {
        this.tenDonHang = tenDonHang;
        this.danhSachMonAn = danhSachMonAn;
    }

    public String getTenDonHang() {
        return tenDonHang;
    }

    public void setTenDonHang(String tenDonHang) {
        this.tenDonHang = tenDonHang;
    }

    public List<MonAnDomain> getDanhSachMonAn() {
        return danhSachMonAn;
    }

    public void setDanhSachMonAn(List<MonAnDomain> danhSachMonAn) {
        this.danhSachMonAn = danhSachMonAn;
    }
}
