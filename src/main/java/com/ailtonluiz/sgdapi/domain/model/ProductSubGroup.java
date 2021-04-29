package com.ailtonluiz.sgdapi.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ProductSubGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(length = 60)
    private String name;

    @ManyToOne
    @JoinColumn(name = "product_group_id", foreignKey = @ForeignKey(name = "fk_product_group_product_subgroup"))
    private ProductGroup productGroup;


}
