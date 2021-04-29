package com.ailtonluiz.sgdapi.domain.model;


import lombok.Data;

import javax.persistence.*;

@Embeddable
@Data
public class Address {

    @Column(length = 150)
    private String street;

    @Column(length = 5)
    private String number;

    @Column(length = 50)
    private String district;

    @Column(length = 150)
    private String complement;

    @ManyToOne
    @JoinColumn(name = "city_id", foreignKey = @ForeignKey(name = "fk_address_city"))
    private City city;

    @Transient
    private State state;

    @Transient
    private Country country;

}
