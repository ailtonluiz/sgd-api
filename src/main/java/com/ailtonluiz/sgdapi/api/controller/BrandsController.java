package com.ailtonluiz.sgdapi.api.controller;

import com.ailtonluiz.sgdapi.domain.exception.EntityInUseException;
import com.ailtonluiz.sgdapi.domain.model.Brand;
import com.ailtonluiz.sgdapi.domain.repository.BrandRepository;
import com.ailtonluiz.sgdapi.domain.service.RegisterBrandService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

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

    @GetMapping("/{brandId}")
    public ResponseEntity<Brand> search(@PathVariable Long brandId) {
        Optional<Brand> brand = brandRepository.findById(brandId);

        if (brand.isPresent()) {
            return ResponseEntity.ok(brand.get());
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Brand add(@RequestBody Brand brand) {
        return registerBrandService.save(brand);
    }

    @PutMapping("/{brandId}")
    public ResponseEntity<Brand> update(@PathVariable Long brandId,
                                        @RequestBody Brand brand) {
        Optional<Brand> currentBrand = brandRepository.findById(brandId);

        if (currentBrand.isPresent()) {
            BeanUtils.copyProperties(brand, currentBrand.get(), "id");

            Brand brandSalva = registerBrandService.save(currentBrand.get());
            return ResponseEntity.ok(brandSalva);
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{brandId}")
    public ResponseEntity<?> remove(@PathVariable Long brandId) {
        try {
            registerBrandService.delete(brandId);
            return ResponseEntity.noContent().build();

        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();

        } catch (EntityInUseException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(e.getMessage());
        }
    }

}
