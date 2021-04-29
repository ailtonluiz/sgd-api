package com.ailtonluiz.sgdapi.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(length = 200)
    private String name;

    @Column(length = 15)
    private String phone;

    private String email;

    private String note;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)
    @Column(length = 10)
    private Status status;
}
