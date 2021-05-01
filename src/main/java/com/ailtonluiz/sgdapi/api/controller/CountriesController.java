package com.ailtonluiz.sgdapi.api.controller;

import com.ailtonluiz.sgdapi.domain.exception.EntityInUseException;
import com.ailtonluiz.sgdapi.domain.model.Country;
import com.ailtonluiz.sgdapi.domain.repository.CountryRepository;
import com.ailtonluiz.sgdapi.domain.service.RegisterCountryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/countries")
public class CountriesController {

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private RegisterCountryService registerCountryService;

    @GetMapping
    public List<Country> list() {
        return countryRepository.findAll();
    }

    @GetMapping("/{countryId}")
    public ResponseEntity<Country> search(@PathVariable Long countryId) {
        Optional<Country> country = countryRepository.findById(countryId);

        if (country.isPresent()) {
            return ResponseEntity.ok(country.get());
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Country add(@RequestBody Country country) {
        return registerCountryService.save(country);
    }

    @PutMapping("/{countryId}")
    public ResponseEntity<Country> update(@PathVariable Long countryId,
                                        @RequestBody Country country) {
        Country currentCountry = countryRepository.findById(countryId).orElse(null);

        if (currentCountry != null) {
            BeanUtils.copyProperties(country, currentCountry, "id");

            currentCountry = registerCountryService.save(currentCountry);
            return ResponseEntity.ok(currentCountry);
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{countryId}")
    public ResponseEntity<?> remove(@PathVariable Long countryId) {
        try {
            registerCountryService.delete(countryId);
            return ResponseEntity.noContent().build();

        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();

        } catch (EntityInUseException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(e.getMessage());
        }
    }


}
