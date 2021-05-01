package com.ailtonluiz.sgdapi.domain.repository;

import com.ailtonluiz.sgdapi.domain.model.City;
import com.ailtonluiz.sgdapi.domain.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

}

