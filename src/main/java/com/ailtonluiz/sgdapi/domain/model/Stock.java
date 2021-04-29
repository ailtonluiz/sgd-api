package com.ailtonluiz.sgdapi.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    private Long stockQuantity;

    private Long stockPendingQuantity;

    private Long stockMinimum;

    private Integer boxQuantity;

    private Double packingQuantity;

    private BigDecimal salePrice;

    private BigDecimal costPrice;

    @ManyToOne
    @JoinColumn(name = "company_id", foreignKey = @ForeignKey(name = "fk_company_stock"))
    private Company company;

    @ManyToOne
    @JoinColumn(name = "product_id", foreignKey = @ForeignKey(name = "fk_product_stock"))
    private Product product;

    @Enumerated(EnumType.STRING)
    @Column(length = 10)
    private Status status;





}
