package com.ailtonluiz.sgdapi.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @NotNull
    @Column(length = 100)
    private String name;

    @NotNull
    @Column(length = 150)
    private String email;

    @Column(length = 13)
    private String phone;

    @NotBlank
    private String password;

    @Transient
    private String passwordConfirmation;

    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(name = "status", length = 10)
    private Status status;

    @ManyToMany(mappedBy = "users")
    private List<Company> companies;
}
