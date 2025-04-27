package com.example.text.dto;

public class Product {
    private int maSP;
    private String tenSP;
    private String moTa;
    private double gia;
    private int soLuong;
    private String hinhAnh;
    private Category category; // Đối tượng Category

    // Constructor
    public Product(int maSP, String tenSP, String moTa, double gia, int soLuong, String hinhAnh, Category category) {
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.moTa = moTa;
        this.gia = gia;
        this.soLuong = soLuong;
        this.hinhAnh = hinhAnh;
        this.category = category;
    }

    // Getter and Setter
    public int getMaSP() {
        return maSP;
    }

    public void setMaSP(int maSP) {
        this.maSP = maSP;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public double getGia() {
        return gia;
    }

    public void setGia(double gia) {
        this.gia = gia;
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    // To String
    @Override
    public String toString() {
        return "Product{" +
                "maSP=" + maSP +
                ", tenSP='" + tenSP + '\'' +
                ", moTa='" + moTa + '\'' +
                ", gia=" + gia +
                ", soLuong=" + soLuong +
                ", hinhAnh='" + hinhAnh + '\'' +
                ", category=" + category +
                '}';
    }
}

