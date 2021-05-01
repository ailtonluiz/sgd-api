package com.ailtonluiz.sgdapi.domain.service;

import com.ailtonluiz.sgdapi.domain.exception.EntityInUseException;
import com.ailtonluiz.sgdapi.domain.model.Company;
import com.ailtonluiz.sgdapi.domain.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class RegisterCompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    public Company save(Company company) {

        return companyRepository.save(company);
    }

    public void delete(Long companyId) {
        try {
            companyRepository.deleteById(companyId);

        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(
                    String.format("There is no company registration with code %d", companyId));

        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(
                    String.format("Code company %d cannot be removed as it is in use", companyId));
        }
    }
}
