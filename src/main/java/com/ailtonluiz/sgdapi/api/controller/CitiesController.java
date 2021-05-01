package com.ailtonluiz.sgdapi.api.controller;

import com.ailtonluiz.sgdapi.domain.exception.EntityInUseException;
import com.ailtonluiz.sgdapi.domain.model.Brand;
import com.ailtonluiz.sgdapi.domain.model.City;
import com.ailtonluiz.sgdapi.domain.repository.BrandRepository;
import com.ailtonluiz.sgdapi.domain.repository.CityRepository;
import com.ailtonluiz.sgdapi.domain.service.RegisterBrandService;
import com.ailtonluiz.sgdapi.domain.service.RegisterCityService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

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

    @GetMapping("/{cityId}")
    public ResponseEntity<City> search(@PathVariable Long cityId) {
        Optional<City> city = cityRepository.findById(cityId);

        if (city != null) {
            return ResponseEntity.ok(city.get());
        }
        return ResponseEntity.notFound().build();

    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody City city) {
        try {
            city = registerCityService.save(city);

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(city);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.badRequest()
                    .body(e.getMessage());
        }
    }

    @PutMapping("/{cityId}")
    public ResponseEntity<?> update(@PathVariable Long cityId,
                                    @RequestBody City city) {
        try {

            City currentCity = cityRepository.findById(cityId).orElse(null);

            if (currentCity != null) {
                BeanUtils.copyProperties(city, currentCity, "id");

                currentCity = registerCityService.save(currentCity);
                return ResponseEntity.ok(currentCity);
            }

            return ResponseEntity.notFound().build();

        } catch (EntityNotFoundException e) {
            return ResponseEntity.badRequest()
                    .body(e.getMessage());
        }
    }

    @DeleteMapping("/{cityId}")
    public ResponseEntity<City> remove(@PathVariable Long cityId) {
        try {
            registerCityService.delete(cityId);
            return ResponseEntity.noContent().build();

        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();

        } catch (EntityInUseException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}
