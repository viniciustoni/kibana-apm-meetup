package com.vagai.salesproducts.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Data
@Entity
@Table(schema = "product", name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long productId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "unit_price", nullable = false)
    private BigDecimal unitPrice;

    @Column(name = "quantity_total", nullable = false)
    private BigDecimal qtdTotal;

    @Column(name = "quantity_reserved", nullable = false)
    private BigDecimal qtdReserved;

    @Column(name = "created_date", nullable = false, updatable = false)
    @CreationTimestamp
    private ZonedDateTime createdDate;

    @Column(name = "updated_date", insertable = false)
    @UpdateTimestamp
    private ZonedDateTime updatedDate;
}
