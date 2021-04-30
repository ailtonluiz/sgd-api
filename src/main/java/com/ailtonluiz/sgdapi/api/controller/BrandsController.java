package com.ailtonluiz.sgdapi.api.controller;

import com.ailtonluiz.sgdapi.domain.model.Brand;
import com.ailtonluiz.sgdapi.domain.repository.BrandRepository;
import com.ailtonluiz.sgdapi.domain.service.RegisterBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/brands")
public class BrandsController {

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private RegisterBrandService registerBrandService;

    @GetMapping
    public List<Brand> list() {
        return brandRepository.findAll();
    }
}
