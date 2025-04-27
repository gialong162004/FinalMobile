package com.example.shop.controller;

import com.example.shop.entity.DanhMuc;
import com.example.shop.service.DanhMucService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/danhmucs")
public class DanhMucController {

    @Autowired
    private DanhMucService danhMucService;

    // API hiển thị tất cả danh mục sản phẩm
    @GetMapping
    public List<DanhMuc> getAllDanhMucs() {
        return danhMucService.getAllDanhMucs();
    }
}
