package com.ailtonluiz.sgdapi.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

import org.hibernate.validator.constraints.EAN;

@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    private String description;

    @EAN(type = EAN.Type.EAN13)
    private String barCode;

    private String reference;

    @Transient
    private ProductGroup productGroup;

    @ManyToOne
    @JoinColumn(name = "brand_id", foreignKey = @ForeignKey(name = "fk_brand_product"))
    private Brand brand;

    @ManyToOne
    @JoinColumn(name = "unity_id", foreignKey = @ForeignKey(name = "fk_unity_product"))
    private Unity unity;

    @ManyToOne
    @JoinColumn(name = "product_subgroup_id", foreignKey = @ForeignKey(name = "fk_product_subgroup_product"))
    private ProductSubGroup productSubGroup;


}
