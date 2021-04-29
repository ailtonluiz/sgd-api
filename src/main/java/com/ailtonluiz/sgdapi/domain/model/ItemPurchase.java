package com.ailtonluiz.sgdapi.domain.model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ItemPurchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    private Integer quantity;

    private BigDecimal unityValue;

    @ManyToOne
    @JoinColumn(name = "product_id", foreignKey = @ForeignKey(name = "fk_product_item_purchase"))
    private Product product;

    @ManyToOne
    @JoinColumn(name = "purchase_id", foreignKey = @ForeignKey(name = "fk_purchase_item_purchase"))
    private Purchase purchase;
}
