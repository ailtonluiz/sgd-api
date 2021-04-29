package com.ailtonluiz.sgdapi.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(length = 100)
    private String name;

    @Column(length = 150)
    private String email;

    @Column(length = 13)
    private String phone;

    private String password;

    @Transient
    private String passwordConfirmation;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 10)
    private Status status;

    @ManyToMany(mappedBy = "users")
    private List<Company> companies;
}
