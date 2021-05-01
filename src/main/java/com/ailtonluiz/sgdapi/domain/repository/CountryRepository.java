package com.ailtonluiz.sgdapi.domain.repository;

import com.ailtonluiz.sgdapi.domain.model.Country;
import com.ailtonluiz.sgdapi.domain.model.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {

}

