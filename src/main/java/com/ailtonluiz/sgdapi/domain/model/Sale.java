package com.ailtonluiz.sgdapi.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime creationDate;

    private BigDecimal totalValue = BigDecimal.ZERO;

    private String note;

    @ManyToOne
    @JoinColumn(name = "client_id", foreignKey = @ForeignKey(name = "fk_client_sale"))
    private Client client;

    @ManyToOne
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "fk_user_sale"))
    private User user;

    @ManyToOne
    @JoinColumn(name = "company_id", foreignKey = @ForeignKey(name = "fk_company_sale"))
    private Company company;

    @Enumerated(EnumType.STRING)
    @Column(length = 12)
    private MovementSatus status = MovementSatus.BUDGET;

    @OneToMany(mappedBy = "sale")
    private List<ItemSale> itemSales;

    @PrePersist
    @PreUpdate
    private void prePersistUpdate() {
        note = note.toUpperCase();
    }
}
