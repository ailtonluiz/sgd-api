package com.ailtonluiz.sgdapi.domain.repository;

import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

@NoRepositoryBean
public interface CustomJpaRepository<T, ID> {

    Optional<T> firstSearch();
}
