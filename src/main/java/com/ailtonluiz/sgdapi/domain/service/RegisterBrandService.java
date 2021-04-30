package com.ailtonluiz.sgdapi.domain.service;

import com.ailtonluiz.sgdapi.domain.exception.EntityInUseException;
import com.ailtonluiz.sgdapi.domain.model.Brand;
import com.ailtonluiz.sgdapi.domain.repository.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class RegisterBrandService {

    @Autowired
    private BrandRepository brandRepository;

    public Brand save(Brand brand) {

        return brandRepository.save(brand);
    }

    public void delete(Long brandId) {
        try {
            brandRepository.deleteById(brandId);

        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(
                    String.format("There is no brand registration with code %d", brandId));

        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(
                    String.format("Code brand %d cannot be removed as it is in use", brandId));
        }
    }
}
