package com.vagai.salesorder.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.FetchType.LAZY;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EqualsAndHashCode
@Table(schema = "sales_order", name = "purchase_order")
public class PurchaseOrder {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long purchaseOrderId;

    @Column(name = "client_id", nullable = false)
    private Long clientId;

    @Column(name = "total_amount", nullable = false)
    private BigDecimal totalAmount;

    @Column(name = "created_date", nullable = false, updatable = false)
    @CreationTimestamp
    private ZonedDateTime createdDate;

    @Column(name = "updated_date", insertable = false)
    @EqualsAndHashCode.Exclude
    @UpdateTimestamp
    private ZonedDateTime updatedDate;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(mappedBy = "purchaseOrder", cascade = ALL, orphanRemoval = true)
    private List<PurchaseOrderItem> purchaseOrderItems;

}
