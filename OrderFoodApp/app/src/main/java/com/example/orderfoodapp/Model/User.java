package com.example.orderfoodapp.Model;

public class User {
    private String tenDangNhap;
    private String matKhau;
    private String gioiTinh;
    private String ngaySinh;
    private String email;
    private int maRole;

    // Constructor
    public User(String tenDangNhap, String matKhau, String gioiTinh, String ngaySinh, String email, int maRole) {
        this.tenDangNhap = tenDangNhap;
        this.matKhau = matKhau;
        this.gioiTinh = gioiTinh;
        this.ngaySinh = ngaySinh;
        this.email = email;
        this.maRole = maRole;
    }

    // Getter và Setter
    public String getTenDangNhap() {
        return tenDangNhap;
    }

    public void setTenDangNhap(String tenDangNhap) {
        this.tenDangNhap = tenDangNhap;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getMaRole() {
        return maRole;
    }

    public void setMaRole(int maRole) {
        this.maRole = maRole;
    }

    // Override toString() để dễ dàng in thông tin người dùng
    @Override
    public String toString() {
        return "User{" +
                "tenDangNhap='" + tenDangNhap + '\'' +
                ", gioiTinh='" + gioiTinh + '\'' +
                ", ngaySinh='" + ngaySinh + '\'' +
                ", email='" + email + '\'' +
                ", maRole=" + maRole +
                '}';
    }
}
