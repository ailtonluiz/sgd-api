package com.ailtonluiz.sgdapi.domain.service;

import com.ailtonluiz.sgdapi.domain.exception.EntityInUseException;
import com.ailtonluiz.sgdapi.domain.model.Country;
import com.ailtonluiz.sgdapi.domain.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class RegisterCountryService {

    @Autowired
    private CountryRepository countryRepository;

    public Country save(Country country) {

        return countryRepository.save(country);
    }

    public void delete(Long countryId) {
        try {
            countryRepository.deleteById(countryId);

        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(
                    String.format("There is no country registration with code %d", countryId));

        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(
                    String.format("Code country %d cannot be removed as it is in use", countryId));
        }
    }
}
