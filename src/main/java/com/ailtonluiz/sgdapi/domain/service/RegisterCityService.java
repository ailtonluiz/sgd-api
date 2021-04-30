package com.ailtonluiz.sgdapi.domain.service;

import com.ailtonluiz.sgdapi.domain.exception.EntityInUseException;
import com.ailtonluiz.sgdapi.domain.model.City;
import com.ailtonluiz.sgdapi.domain.model.City;
import com.ailtonluiz.sgdapi.domain.repository.CityRepository;
import com.ailtonluiz.sgdapi.domain.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class RegisterCityService {

    @Autowired
    private CityRepository cityRepository;

    public City save(City city) {

        return cityRepository.save(city);
    }

    public void delete(Long cityId) {
        try {
            cityRepository.deleteById(cityId);

        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(
                    String.format("There is no city registration with code %d", cityId));

        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(
                    String.format("Code city %d cannot be removed as it is in use", cityId));
        }
    }
}
