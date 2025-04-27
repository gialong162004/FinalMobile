package com.example.shop.service;

import com.example.shop.entity.SanPham;
import com.example.shop.repository.SanPhamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SanPhamService {
    
    @Autowired
    private SanPhamRepository sanPhamRepository;

    public List<SanPham> getAllSanPhams() {
        return sanPhamRepository.findAll();
    }
}
