package com.ailtonluiz.sgdapi.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "company_name", length = 100)
    private String companyName;

    @Column(name = "company_fantasy", length = 80)
    private String companyFantasy;

    @Column(name = "phone", length = 13)
    private String phone;

    @Column(name = "email", length = 150)
    private String email;

    @Column(name = "accountable", length = 150)
    private String accountable;

    @Column(name = "manager", length = 150)
    private String manager;

    @Embedded
    private Address address;

}
