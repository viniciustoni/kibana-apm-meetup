package com.vagai.salesorder.repository;

import com.vagai.salesorder.entity.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Long> {

    List<PurchaseOrder> findByClientId(Long clientId);

    @Query("""
                SELECT po
                  FROM PurchaseOrder po
            JOIN FETCH po.purchaseOrderItems
                 WHERE po.clientId = :clientId
                """)
    List<PurchaseOrder> findByClientIdWithItems(@Param("clientId") Long clientId);
}
