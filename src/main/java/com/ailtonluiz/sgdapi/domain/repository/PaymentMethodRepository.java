package com.ailtonluiz.sgdapi.domain.repository;

import com.ailtonluiz.sgdapi.domain.model.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;

@Repository
public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, Long> {
    @Query("select max(dateUpdate) from PaymentMethod ")
    OffsetDateTime getLastUpdateDate();

    @Query("select dateUpdate from PaymentMethod where id = :paymentMethodId")
    OffsetDateTime getUpdateDateById(Long paymentMethodId);

}

