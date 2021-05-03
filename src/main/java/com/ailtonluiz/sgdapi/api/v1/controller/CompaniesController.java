package com.ailtonluiz.sgdapi.api.v1.controller;

import com.ailtonluiz.sgdapi.domain.exception.EntityInUseException;
import com.ailtonluiz.sgdapi.domain.model.Company;
import com.ailtonluiz.sgdapi.domain.repository.CompanyRepository;
import com.ailtonluiz.sgdapi.domain.service.RegisterCompanyService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/companies")
public class CompaniesController {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private RegisterCompanyService registerCompanyService;

    @GetMapping
    public List<Company> list() {
        return companyRepository.findAll();
    }

    @GetMapping("/{companyId}")
    public ResponseEntity<Company> search(@PathVariable Long companyId) {
        Optional<Company> company = companyRepository.findById(companyId);

        if (company.isPresent()) {
            return ResponseEntity.ok(company.get());
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Company add(@RequestBody Company company) {
        return registerCompanyService.save(company);
    }

    @PutMapping("/{companyId}")
    public ResponseEntity<Company> update(@PathVariable Long companyId,
                                        @RequestBody Company company) {
        Company currentCompany = companyRepository.findById(companyId).orElse(null);

        if (currentCompany != null) {
            BeanUtils.copyProperties(company, currentCompany, "id");

            currentCompany = registerCompanyService.save(currentCompany);
            return ResponseEntity.ok(currentCompany);
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{companyId}")
    public ResponseEntity<?> remove(@PathVariable Long companyId) {
        try {
            registerCompanyService.delete(companyId);
            return ResponseEntity.noContent().build();

        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();

        } catch (EntityInUseException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(e.getMessage());
        }
    }


}
