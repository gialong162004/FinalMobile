package com.example.shop.controller;

import com.example.shop.entity.SanPham;
import com.example.shop.service.SanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sanphams")
public class SanPhamController {

    @Autowired
    private SanPhamService sanPhamService;

    // API hiển thị tất cả sản phẩm
    @GetMapping
    public List<SanPham> getAllSanPhams() {
        return sanPhamService.getAllSanPhams();
    }
}
