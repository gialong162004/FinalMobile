package com.example.shop.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "sanpham")
public class SanPham {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int MaSP; // Mã sản phẩm

    private String TenSP; // Tên sản phẩm
    private String MoTa; // Mô tả sản phẩm
    private double Gia; // Giá sản phẩm
    private int SoLuong; // Số lượng tồn kho
    private String HinhAnh; // Đường dẫn hình ảnh

    @ManyToOne
    @JoinColumn(name = "MaDM")
    private DanhMuc category; // Danh mục sản phẩm

	public int getMaSP() {
		return MaSP;
	}

	public void setMaSP(int maSP) {
		MaSP = maSP;
	}

	public String getTenSP() {
		return TenSP;
	}

	public void setTenSP(String tenSP) {
		TenSP = tenSP;
	}

	public String getMoTa() {
		return MoTa;
	}

	public void setMoTa(String moTa) {
		MoTa = moTa;
	}

	public double getGia() {
		return Gia;
	}

	public void setGia(double gia) {
		Gia = gia;
	}

	public int getSoLuong() {
		return SoLuong;
	}

	public void setSoLuong(int soLuong) {
		SoLuong = soLuong;
	}

	public String getHinhAnh() {
		return HinhAnh;
	}

	public void setHinhAnh(String hinhAnh) {
		HinhAnh = hinhAnh;
	}

	public DanhMuc getCategory() {
		return category;
	}

	public void setCategory(DanhMuc category) {
		this.category = category;
	}

}
