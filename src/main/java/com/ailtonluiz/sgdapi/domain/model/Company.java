package com.ailtonluiz.sgdapi.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;

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


    @ManyToMany
    @JoinTable(name = "company_user", joinColumns = @JoinColumn(name = "company_id", foreignKey = @ForeignKey(name = "fk_company_user_company_id")),
            inverseJoinColumns = @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "fk_company_user_user_id")))
    private List<User> users;
}
