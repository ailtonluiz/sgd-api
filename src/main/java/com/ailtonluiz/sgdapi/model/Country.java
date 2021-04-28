package com.ailtonluiz.sgdapi.model;


import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.*;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(length = 150)
    private String name;

    @Column(length = 4)
    private String shortName;
}
