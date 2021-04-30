package com.ailtonluiz.sgdapi.api.controller;

import com.ailtonluiz.sgdapi.domain.model.Brand;
import com.ailtonluiz.sgdapi.domain.model.City;
import com.ailtonluiz.sgdapi.domain.repository.BrandRepository;
import com.ailtonluiz.sgdapi.domain.repository.CityRepository;
import com.ailtonluiz.sgdapi.domain.service.RegisterBrandService;
import com.ailtonluiz.sgdapi.domain.service.RegisterCityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/cities")
public class CitiesController {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private RegisterCityService registerCityService;

    @GetMapping
    public List<City> list() {

        return cityRepository.findAll();
    }
}
