package com.example.orderfoodapp.Model;

public class Role {
    private int maRole;
    private String chucVu;
    private String moTa;

    // Constructor không tham số
    public Role() {
    }

    // Constructor có tham số
    public Role(int maRole, String chucVu, String moTa) {
        this.maRole = maRole;
        this.chucVu = chucVu;
        this.moTa = moTa;
    }

    // Getter và Setter
    public int getMaRole() {
        return maRole;
    }

    public void setMaRole(int maRole) {
        this.maRole = maRole;
    }

    public String getChucVu() {
        return chucVu;
    }

    public void setChucVu(String chucVu) {
        this.chucVu = chucVu;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    // Phương thức toString
    @Override
    public String toString() {
        return "Role{" +
                "maRole=" + maRole +
                ", chucVu='" + chucVu + '\'' +
                ", moTa='" + moTa + '\'' +
                '}';
    }
}
