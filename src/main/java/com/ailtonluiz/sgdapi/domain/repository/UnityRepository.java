package com.ailtonluiz.sgdapi.domain.repository;

import com.ailtonluiz.sgdapi.domain.model.Unity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnityRepository extends JpaRepository<Unity, Long> {

}

