package com.ailtonluiz.sgdapi.domain.repository;

import com.ailtonluiz.sgdapi.domain.model.Brand;
import com.ailtonluiz.sgdapi.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandsRepository extends JpaRepository<Brand, Long> {

    Brand findByName(String name);

}
