package com.ailtonluiz.sgdapi.domain.repository;

import com.ailtonluiz.sgdapi.domain.model.ClientGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientGroupRepository extends JpaRepository<ClientGroup, Long> {
}
