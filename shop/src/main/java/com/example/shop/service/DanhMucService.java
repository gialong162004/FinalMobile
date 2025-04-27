package com.example.shop.service;

import com.example.shop.entity.DanhMuc;
import com.example.shop.repository.DanhMucRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DanhMucService {
    
    @Autowired
    private DanhMucRepository danhMucRepository;

    public List<DanhMuc> getAllDanhMucs() {
        return danhMucRepository.findAll();
    }
}
