package com.ailtonluiz.sgdapi.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class State {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(length = 150)
    private String name;

    @Column(length = 4)
    private String shortName;


    @ManyToOne
    @JoinColumn(name = "country_id", foreignKey = @ForeignKey(name = "fk_country_state"))
    private Country country;
}
