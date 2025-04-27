package com.example.shop.entity;

import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "danhmuc")
public class DanhMuc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int MaDM; // Mã danh mục

    private String TenDM; // Tên danh mục

	public int getMaDM() {
		return MaDM;
	}

	public void setMaDM(int maDM) {
		MaDM = maDM;
	}

	public String getTenDM() {
		return TenDM;
	}

	public void setTenDM(String tenDM) {
		TenDM = tenDM;
	}

    
}
